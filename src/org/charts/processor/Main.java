/**
 * 
 */
package org.charts.processor;

import com.google.gdata.util.*;

import java.io.IOException;
import java.io.FileInputStream;
import java.net.*;
import java.util.*;

/**
 * @author Adam Skowronek <adamer.sk@gmail.com>
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	

	
	public static void main(String[] args) throws AuthenticationException,
			MalformedURLException, IOException, ServiceException {

		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("config.properties"));
			String USERNAME = prop.getProperty("username");
			String PASSWORD = prop.getProperty("password");
			String SPREADSHEET_NAME = prop.getProperty("spreadsheet_name");
			new Spreadsheet(USERNAME, PASSWORD, SPREADSHEET_NAME);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}



