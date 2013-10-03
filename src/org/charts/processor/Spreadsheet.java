/**
 * 
 */
package org.charts.processor;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import com.google.gdata.data.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author root
 * 
 */
public class Spreadsheet {

	private SpreadsheetService service = null;
	private SpreadsheetFeed feed = null;
	private String spreadsheetsUrl = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";

	public Spreadsheet(String username, String password, String spreadsheetName)
			throws AuthenticationException, MalformedURLException, IOException,
			ServiceException {
		service = new SpreadsheetService("Chart");
		service.setUserCredentials(username, password);
		service.setProtocolVersion(SpreadsheetService.Versions.V3);

		URL url = new URL(spreadsheetsUrl);
		feed = service.getFeed(url, SpreadsheetFeed.class);
		SpreadsheetEntry s = getSpreadsheetByName(spreadsheetName);
		addWorksheet(s, "testowy", 10,4);
		
	}

	public SpreadsheetEntry getSpreadsheetByName(String name) {
		List<SpreadsheetEntry> spreads = feed.getEntries();
		SpreadsheetEntry findedSpread = null;

		for (SpreadsheetEntry spread : spreads) {
			if (name.equals(spread.getTitle().getPlainText()))
				findedSpread = spread;
				System.out.println(spread.getTitle().getPlainText());
		}

		System.out.println(findedSpread.getTitle().getPlainText());
		return findedSpread;
	}

	public WorksheetEntry getWorksheetByName(SpreadsheetEntry spreadsheet,
			String name) throws IOException, ServiceException {
		WorksheetEntry finded_work = null;
		List<WorksheetEntry> works = spreadsheet.getWorksheets();

		for (WorksheetEntry work : works) {
			if (work.getTitle().getPlainText() == name)
				finded_work = work;
		}
		return finded_work;
	}

	public void addWorksheet(SpreadsheetEntry spreadsheet, String name,
			int rows, int cells) throws IOException, ServiceException {
		rows = 10;
		cells = 100;
		WorksheetEntry worksheet = new WorksheetEntry();
		worksheet.setTitle(new PlainTextConstruct(name));
		worksheet.setColCount(cells);
		worksheet.setRowCount(rows);
		URL worksheetFeedUrl = spreadsheet.getWorksheetFeedUrl();
		service.insert(worksheetFeedUrl, worksheet);
	}
}
