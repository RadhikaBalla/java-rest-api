package com.company.house.dto;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyHousReport {

	//user who added highest comments; an object is best suited
	
	@XmlElement(name = "user_with_most_comments", namespace = "user_with_most_comments")
	public String user;
	
	//game with highest rate; an object is best suited
	@XmlElement(name = "highest_rated_game", namespace = "highest_rated_game")
	public String game;
	
	//an individual map holds game title and avg likes
	@XmlElement(name = "average_likes_per_game", namespace = "average_likes_per_game")
	public List<Map> avgGameLikes;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		if(this.user != null) {
			System.out.println("user alraedy set, one more user existed====");
			this.user= this.user + ", " + user;
		} else {
			System.out.println("local user is null initially ==== ");
		}
		this.user = user;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public List<Map> getAvgGameLikes() {
		return avgGameLikes;
	}

	public void setAvgGameLikes(List<Map> avgGameLikes) {
		this.avgGameLikes = avgGameLikes;
	}
	
}
