package org.exl.test.demo.core.servlets;

import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner; 
import java.io.BufferedReader;
import java.util.*; 
import java.io.*;

public class DataFilterer2 {

private static double average;

// Method to set the average value of RESPONSETIME
    public static void setAverage(double avg) {

        average = avg;
    }

    // Method to get the average value of RESPONSETIME
    public static double getAverage() {

        return average;
    }

public static Collection<String> filterByCountry(Reader source, String country) {
	System.out.println("Inside filterByCountry");
    BufferedReader br = new BufferedReader(source);
    String line = null;
    Collection<String> additionalList = new ArrayList<String>();
    int iteration = 0;
    try {
    	 line = br.readLine();
    	 // File completely empty, not even a header
        if (line == null) {
            return Collections.emptyList();
        }
        while ((line = br.readLine()) != null) {
            // Logic to remove header from the input data.
            if (iteration == 0) {
                iteration++;
                continue;
            }
            String[] myArray = line.split(",");

            List<String> myList = new ArrayList<String>(Arrays.asList(myArray));
            if (myList.contains(country)) {
                additionalList.addAll(myList);
                System.out.println("Test = " + Arrays.deepToString(additionalList.toArray()));
            }
        }


    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    return additionalList;
}



public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {

        BufferedReader br = new BufferedReader(source);
        String line = null;
        Collection<String> additionalList = new ArrayList<String>();
        int iteration = 0;
        long count = 0;
        long responseTime = 0;
        try {
            while ((line = br.readLine()) != null) {
                // Logic to remove header from the input data
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                String[] myArray = line.split(",");

                List<String> myList = new ArrayList<String>(Arrays.asList(myArray));
                for (String eachval : myArray) {
                    // Finding the RESPONSE TIME from the input line
                    boolean isNumeric = eachval.chars().allMatch(x -> Character.isDigit(x));
                    if (isNumeric) {
                        count = eachval.chars().count();
                        // Identifying between RESPONSETIME and
                        // REQUEST_TIMESTAMP.Unix Timestamp will be always 10
                        // digits or 13 digits
                        if (count < 10) {
                            responseTime = Integer.parseInt(eachval);
                            if (myList.contains(country)) {
                                if (responseTime > limit) {
                                    additionalList.addAll(myList);
                                }
                            }
                            else
                            {
                                return Collections.emptyList();
                            }

                        }
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return additionalList;

}

public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
    BufferedReader br = new BufferedReader(source);
    String line = null;
    double average = 0.0;
    Collection<String> additionalList = new ArrayList<String>();
    average = getAverage();

    long responseTime = 0;
    int iteration = 0;
    long count = 0;
    String[] myArray = null;
    try {
        while ((line = br.readLine()) != null) {
            // Logic to remove header from the input data.
            if (iteration == 0) {
                iteration++;
                continue;
            }

            myArray = line.split(",");
            List<String> myList = new ArrayList<String>(Arrays.asList(myArray));
            for (String eachval : myArray) {
                // Finding the RESPONSE TIME from the input line
                boolean isNumeric = eachval.chars().allMatch(x -> Character.isDigit(x));
                if (isNumeric) {
                    count = eachval.chars().count();
                    // Identifying between RESPONSETIME and
                    // REQUEST_TIMESTAMP.Unix Timestamp will be always 10
                    // digits or 13 digits
                    if (count < 10) {
                        responseTime = Integer.parseInt(eachval);
                        if (responseTime > average) {

                            additionalList.addAll(myList);

                        }
                        else
                        {
                            return Collections.emptyList();
                        }

                    }
                }
            }

        }


    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    return additionalList;
	}


public static void main(String args[]){
	
    if (args.length > 0) {
    	//System.out.println("List of arguments: {} "+ Arrays.toString(args));
    	// Creates an array of character
        char[] array = new char[100];
    	try {
            // Creates a reader using the FileReader
    		String desktop = System.getProperty ("user.home") + "/Desktop/";
    		File input = new File (desktop + "applicationlogs.txt");
    		Reader targetReader = new FileReader(input);
    		
    		// Checks if reader is ready and there is data in the stream
            System.out.println("Is there data in the stream?  " + targetReader.ready());

            if(targetReader.ready()) {
            Collection<String> additionalListprint = filterByCountry(targetReader, "US");
            System.out.println("Print logs for filterByCountry = "+ Arrays.deepToString(additionalListprint.toArray()));
//            for(String innerLs : additionalListprint) {
//            	System.out.println("Is there data in the stream?  " + innerLs);
//            }        }

            }
    	}
        catch(Exception e) {
        	System.out.println("Exception == "+ e.getMessage());
        }
    	
    
}
}
}