package org.exl.test.demo.core.servlets;

import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner; 
import java.io.BufferedReader;

public class DataFilterer1 {
	
	Reader source = null;
	
    public static Collection<?> filterByCountry(Reader source, String country) {
        return Collections.emptyList();
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        return Collections.emptyList();
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        return Collections.emptyList();
    }
    
    public static void main(String args[]){

		System.out.println("Hello World, Maven");
		
        if (args.length > 0) {
        	//System.out.println("List of arguments: {} "+ Arrays.toString(args));
        	// Creates an array of character
            char[] array = new char[100];
        	try {
                // Creates a reader using the FileReader
        		System.out.println("List of arguments: {} "+ Arrays.toString(args));
        		
        		String desktop = System.getProperty ("user.home") + "/Desktop/";
        		File input = new File (desktop + "applicationlogs.txt");
        	    //Reader input = new FileReader(Arrays.toString(args));
        	
        		Reader targetReader = new FileReader(input);
        		
        		// Checks if reader is ready 
                System.out.println("Is there data in the stream?  " + targetReader.ready());

                // Reads characters
                //input.read(array);
                System.out.println("Data in the stream:");
                System.out.println(array);

                // Closes the reader
                //input.close();
            }

            catch(Exception e) {
            	System.out.println("Error == "+ e.getMessage());
            }
        	
        }

	}
}