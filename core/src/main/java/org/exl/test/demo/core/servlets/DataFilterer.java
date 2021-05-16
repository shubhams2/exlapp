package org.exl.test.demo.core.servlets;

import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.util.*; 
import java.io.*;
//import bean.LogEntry;

public class DataFilterer {
	
	/*

	//Considering constant values for the solution, however these values can be accepted at runtime also from users.
	private static long average = 200;
	private static long responseTimeLimit = 300;
	Reader source = null;

	public static Collection<?> filterByCountry(Reader source, String country) throws IOException {

		System.out.println("Inside filterByCountry");
		BufferedReader br = new BufferedReader(source);
		int timestampCol = -1;
		int countryCol = -1;
		int responseTimeCol = -1;
		String line = br.readLine();
		// File completely empty, not even a header
		if (line == null) {
			return Collections.emptyList();
		}
		String[] headers = line.split(",");

		for (int i = 0; i < headers.length; i++) {
			String h = headers[i];
			if (h.equals("REQUEST_TIMESTAMP")) {
				timestampCol = i; continue;
			}
			if (h.equals("COUNTRY_CODE")) {
				countryCol = i; continue;
			}
			if (h.equals("RESPONSE_TIME")) {
				responseTimeCol = i; continue;
			}
		}

		Collection<LogEntry> result = new ArrayList<LogEntry>();

		while ((line = br.readLine()) != null) {
			String[] logEntry = line.split(",");            
			LogEntry entry = new LogEntry(Long.parseLong(logEntry[timestampCol])
					, logEntry[countryCol]
							, Integer.parseInt(logEntry[responseTimeCol]));
			if (entry.getCountryCode().equals(country)) {
				result.add(entry);
			}
		}
		return result;
	}



	public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) throws IOException {

		System.out.println("Inside filterByCountryWithResponseTimeAboveLimit");
		BufferedReader br = new BufferedReader(source);
		int timestampCol = -1;
		int countryCol = -1;
		int responseTimeCol = -1;
		String line = br.readLine();
		// File completely empty, not even a header
		if (line == null) {
			return Collections.emptyList();
		}
		String[] headers = line.split(",");

		for (int i = 0; i < headers.length; i++) {
			String h = headers[i];
			if (h.equals("REQUEST_TIMESTAMP")) {
				timestampCol = i; continue;
			}
			if (h.equals("COUNTRY_CODE")) {
				countryCol = i; continue;
			}
			if (h.equals("RESPONSE_TIME")) {
				responseTimeCol = i; continue;
			}
		}

		Collection<LogEntry> result = new ArrayList<LogEntry>();

		while ((line = br.readLine()) != null) {
			String[] logEntry = line.split(",");
			LogEntry entry = new LogEntry(Long.parseLong(logEntry[timestampCol])
					, logEntry[countryCol]
							, Integer.parseInt(logEntry[responseTimeCol]));
			if (entry.getCountryCode().equals(country) && entry.getResponseTime() > limit) {
				result.add(entry);
			}
		}
		return result;

	}

	public static Collection<?> filterByResponseTimeAboveAverage(Reader source) throws IOException {

		System.out.println("Inside filterByResponseTimeAboveAverage");
		BufferedReader br = new BufferedReader(source);
		int timestampCol = -1;
		int countryCol = -1;
		int responseTimeCol = -1;
		String line = br.readLine();
		// File completely empty, not even a header
		if (line == null) {
			return Collections.emptyList();
		}
		String[] headers = line.split(",");

		for (int i = 0; i < headers.length; i++) {
			String h = headers[i];
			if (h.equals("REQUEST_TIMESTAMP")) {
				timestampCol = i; continue;
			}
			if (h.equals("COUNTRY_CODE")) {
				countryCol = i; continue;
			}
			if (h.equals("RESPONSE_TIME")) {
				responseTimeCol = i; continue;
			}
		}

		Collection<LogEntry> result = new ArrayList<LogEntry>();

		while ((line = br.readLine()) != null) {
			String[] logEntry = line.split(",");
			LogEntry entry = new LogEntry(Long.parseLong(logEntry[timestampCol])
					, logEntry[countryCol]
							, Integer.parseInt(logEntry[responseTimeCol]));
			if (entry.getResponseTime() > average) {
				result.add(entry);
			}
		}
		return result;

	}


	public static void main(String args[]){

		try {
			// Creates a reader using the FileReader
			String desktop = System.getProperty ("user.home") + "/Desktop/";
			File input = new File (desktop + "applicationlogs.txt");
			Reader source = new FileReader(input);

			// Checks if reader is ready and there is data in the stream
			if(source.ready()) {
				
			// Using Scanner for Getting Input from User 
	        Scanner in = new Scanner(System.in);

	        System.out.println("Please enter number : 1 for filterByCountry, 2 for filterByCountryWithResponseTimeAboveLimit and 3 for filterByResponseTimeAboveAverage");
	        int flag = in.nextInt(); 

	        if(flag==1) {
	        	//considering hard-coded value for country or can be taken as user input as well.
	        	Collection<?> resultList1 = filterByCountry(source, "US");
				System.out.println("Print logs for filterByCountry = "+ resultList1.toString());
				System.out.println("Checks if reader is ready and there is data in the stream = "+ source.ready());	
	        }else if(flag==2) {
	        	//considering hard-coded value for country and constant for responseTimeLimit or can be taken as user input as well.
	        	Collection<?> resultList2 = filterByCountryWithResponseTimeAboveLimit(source, "US" , responseTimeLimit);
				System.out.println("Print logs for filterByCountryWithResponseTimeAboveLimit = "+ resultList2.toString());
	        }else if(flag==3) {
	        	Collection<?> resultList3 = filterByResponseTimeAboveAverage(source);
				System.out.println("Print logs for filterByResponseTimeAboveAverage = "+ resultList3.toString());
	        }else {
	        	System.out.println("Please provide input as required.");
	        }
	  				
			}

		}
		catch(Exception e) {
			System.out.println("Exception == "+ e.getMessage());
		}

	}
	*/
}