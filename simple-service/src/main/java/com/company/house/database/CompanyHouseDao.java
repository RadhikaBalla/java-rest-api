package com.company.house.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.house.controller.ReadQueries;
import com.company.house.dto.Comment;
import com.company.house.dto.CompanyHousReport;
import com.company.house.dto.Game;

public class CompanyHouseDao {

	public Game getGameById(String idParam) {
		Game resultMap = new Game();
		
		String sql = ReadQueries.getQueryByKeyName("getGameById");
		sql = sql.replaceAll(":id", idParam);
		System.out.println("changed sql query:"+sql);
		
		try (PreparedStatement statement = CompanyHouseDBConnection.getConnection().prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println("assuming only one record is retrieved; reading query columns...");
				resultMap.setGid(resultSet.getInt("GID"));
				resultMap.setTitle(resultSet.getString("GNAME"));
				resultMap.setDescription(resultSet.getString("DESCRIPTION"));
				resultMap.setBy(resultSet.getString("PRODUCT_BY"));
				resultMap.setPlatform(resultSet.getString("PLATFORM"));
				resultMap.setAgeRating(resultSet.getInt("AGE_RATING"));
				resultMap.setLikes(resultSet.getInt("NO_OF_LIKES"));
			}
			System.out.println("resulted map: "+resultMap.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultMap.setComments(getCommentsByGameId(idParam));
		
		return resultMap;
	}

	private List<Comment> getCommentsByGameId(String gameId) {
		List<Comment> comments = null;
		String sql = ReadQueries.getQueryByKeyName("getUserCommentsByGameId");
		sql = sql.replaceAll(":gameId", gameId);
		System.out.println("changed sql query:"+sql);
		
		try (PreparedStatement statement = CompanyHouseDBConnection.getConnection().prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery(sql);
			comments = new ArrayList<Comment>();
			while (resultSet.next()) {
				Comment comment = new Comment();
				System.out.println("assuming only one record is retrieved; reading query columns...");
				comment.setGid(resultSet.getInt("GID"));
				comment.setCid(resultSet.getInt("CID"));
				comment.setCreatedDate(resultSet.getTimestamp("DATE_CREATED"));
				comment.setUser(resultSet.getString("USER_NAME"));
				comment.setMessage(resultSet.getString("COMMENT"));
				comment.setLikes(resultSet.getInt("TOTAL_LIKES"));
				comments.add(comment);
			}
			System.out.println("resulted map: "+comments.size());
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return comments;
	}

	public CompanyHousReport getAllGamesReport() {
		CompanyHousReport report = new CompanyHousReport();
		report = getUserWhoAddedMostComments(report);
		report = getHighestRatedGame(report);
		report = getAvergeLikesPerGame(report);
		return report;
	}

	private CompanyHousReport getUserWhoAddedMostComments(CompanyHousReport report) {
		String sql = ReadQueries.getQueryByKeyName("getUserWithMostComments");
		System.out.println("sql query:"+sql);
		try (PreparedStatement statement = CompanyHouseDBConnection.getConnection().prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println("assuming only one record is retrieved; reading query columns...");
				report.setUser(resultSet.getString("UNAME"));
			}
			System.out.println("most comments by user: "+report.getUser());
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return report;
	}

	private CompanyHousReport getHighestRatedGame(CompanyHousReport report) {
		String sql = ReadQueries.getQueryByKeyName("getHighestRatedGame");
		System.out.println("sql query:"+sql);
		try (PreparedStatement statement = CompanyHouseDBConnection.getConnection().prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println("assuming only one record is retrieved; reading query columns...");
				report.setGame(resultSet.getString("GNAME"));
			}
			System.out.println("highest rated game: "+report.getGame());
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	}

	private CompanyHousReport getAvergeLikesPerGame(CompanyHousReport report) {
		List<Map> averageLikesMapList = new ArrayList<Map>();
		String sql = ReadQueries.getQueryByKeyName("getAverageLikesPerGame");
		System.out.println("sql query:"+sql);
		try (PreparedStatement statement = CompanyHouseDBConnection.getConnection().prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", resultSet.getString("GNAME"));
				map.put("average_likes", resultSet.getInt("total_likes"));
				averageLikesMapList.add(map);
			}
			
			report.setAvgGameLikes(averageLikesMapList);
			System.out.println("most comments by user: "+report.getUser());
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	}
	
}
