package com.jbbwebsolutions.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HolidayResultSet extends ResultSetHelper {

	List<String> list = new ArrayList<>();
	private int currentPosition = 0;
	
	public HolidayResultSet() {		
		
		String fileName = "c://data//Holidays.txt";

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(list::add);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public String getString(String columnLabel) throws SQLException {
		String data = list.get(currentPosition - 1);
		return data;
	}

	@Override
	public boolean next() throws SQLException {		
		currentPosition ++;
		
		if (currentPosition >= list.size()) {
			return true;
		}
		
		return false;
	}
	
	

}
