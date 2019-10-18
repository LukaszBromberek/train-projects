package input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public final class DataLoader{

	//Load data from https://krew.info/zapasy/
	public static String loadData() {
		// TODO Auto-generated method stub
		return null;
	}


	public static String loadData(String fileName) {

		// If given file name matches dddd_dd_dd then add HTMLFiles path and .html extension
		// Else provide to function full file path
		String filePath;
		if (Pattern.compile("\\d\\d\\d\\d_\\d\\d_\\d\\d").matcher(fileName).matches()){
			filePath="src/HTMLFiles/" + fileName + ".html";
		}
		else {
			filePath=fileName;
		}

		String ret="";
		BufferedReader fileReader = null;
		

		try {
		    fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
		    
		    //While loop is broken by NullPointerException
		    while(true) {
		    	ret=ret.concat(fileReader.readLine());   
		    }
		}catch (FileNotFoundException e) {
			System.out.println("File was not found / Pliku nie odnaleziono.");
			e.printStackTrace();
		}catch (NullPointerException e) {
			System.out.println("File fully loaded");
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
		    if (fileReader != null) {
		    	try {
		    		fileReader.close();
		    	}
		    	catch (IOException e) {
		    		e.printStackTrace();
		    	}

		    }
		}
		
		return ret;
	}

	
	/*
	 * //Test public static void main (String[]args) {
	 * 
	 * String text = loadData("2019_10_16"); //String text =
	 * loadData("src/HTMLFiles/2019_10_16.html"); System.out.println(text); }
	 */
	 
}
