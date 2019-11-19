package com.company.house.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadQueries {

	public static String getQueryByKeyName(String key) {
		try (InputStream input = ReadQueries.class.getClassLoader().getResourceAsStream("dbQueries.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			String query = (String) prop.get(key);
			System.out.println(query);
			return query;
		} catch (IOException io) {
			io.printStackTrace();
		}

		return null;
	}

}
