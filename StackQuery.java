/*
This is a class that is used to query the Stackexchange API.
The api calls will have specific input parameters that can be changed by the user.
The api then builds this URL by using the URLBuild method.
Afterwhich, 2 functions are available for querying the data.
1. ExecuteJson
2.ExecuteString
The Executestring method returns the data as string
The ExecuteJson method returns the data and map them into java bean objects. (requires the use of
JacksonJsonParser, StackItems, QueryResultBean, OwnerData.
*** remember to close all filestreams

Please include the following libraries before using:
Jackson jr library
Jackson core full (Not needed but include for learning only)
 */

package com.example.webjson;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class StackQuery {
	//The static parameters for building the api directory.
	private static final String BASE_URL = "https://api.stackexchange.com/2.2/search?";
	private static final String PARAM_SITE = "site=";
	private static final String PARAM_PAGE_SIZE = "&pagesize=";
	private static final String PARAM_ORDER = "&order=";
	private static final String PARAM_SORT = "&sort";
	private static final String PARAM_TAG = "&tagged=";
	private static final String PARAM_FILTER = "&filter=";
	private static final String PARAM_SEARCH_TERM = "&intitle=";

	//constructor
	public StackQuery(String site, int pageSize, SortOrder sortOrder, SortBy sortBy, String tag, String searchTerm) {
		this.site = site;
		this.pageSize = pageSize;
		this.sortOrder = sortOrder;
		this.sortBy = sortBy;
		this.tag = tag;
		this.searchTerm = searchTerm;
	}

	//The parameters of the API that will be entered by the user.
	//do not have to set a default value as this is done in the
	//builder class.
	private String site;
	private int pageSize;
	private SortOrder sortOrder;
	private SortBy sortBy;
	private String tag;
	private String searchTerm;
	
	public enum SortOrder {
		ASCENDING   ("asc"), 
		DESCENDING  ("desc");
		
		private final String key;
		SortOrder(String key) { this.key = key; }
		
		public String toString() { return key; }
	}
	
	public enum SortBy {
		RELEVANCE	("relevance"), 
		VOTES		("votes"), 
		CREATION	("creation"), 
		ACTIVITY	("activity");
		
		private final String key;
		SortBy(String key) { this.key = key; }
		
		public String toString() { return key; }
	}

	public List<QueryResultBean> executeJson() throws IOException {

		//Create url
		URL url = buildURL();
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		if("gzip".equals(connection.getContentEncoding()))
		{
			in = new GZIPInputStream(in);
		}

		//read a json as input stream for testing purposes.
//		FileInputStream in = new FileInputStream("C:\\Users\\xpuan\\Desktop\\search.json");

		List<QueryResultBean> results;
		JacksonJsonParser parser = new JacksonJsonParser();
		results = parser.parseJason(in);
		in.close();

		return results;
	}

	public String executeString() throws IOException {
		//create buffered reader to read data as string
		StringBuilder sb = new StringBuilder();
		URL url = buildURL();
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		if("gzip".equals(connection.getContentEncoding()))
		{
			in = new GZIPInputStream(in);
		}
		 try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))
		 {
		 	char[] buffer = new char[256];
		 	int size =0;
		 	while((size = br.read(buffer))>0)
			{
				sb.append(buffer,0,size);
			}
		 }
		 return sb.toString();
	}

	private URL buildURL() throws MalformedURLException {
		StringBuilder sb = new StringBuilder();
		sb.append(BASE_URL);
		sb.append(PARAM_SITE).append(this.site);
		sb.append(PARAM_PAGE_SIZE).append(this.pageSize);
		sb.append(PARAM_ORDER).append(this.sortOrder);
		sb.append(PARAM_SORT).append(this.sortBy);
		sb.append(PARAM_TAG).append(this.tag);
		sb.append(PARAM_SEARCH_TERM).append(this.searchTerm);


		URL queryURL = new URL(sb.toString());
		return queryURL;
	}


	// These are the setter methods to set the values of the properties within this class
	//however this is no longer required in the code because I have implemented a builder class
//	public void setSite(String site) {
//		this.site = site;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public void setSortOrder(SortOrder sortOrder) {
//		this.sortOrder = sortOrder;
//	}
//
//	public void setSortBy(SortBy sortBy) {
//		this.sortBy = sortBy;
//	}
//
//	public void setTag(String tag) {
//		this.tag = tag;
//	}
//
//	public void setSearchTerm(String searchTerm) {
//		this.searchTerm = searchTerm;
//	}
}


