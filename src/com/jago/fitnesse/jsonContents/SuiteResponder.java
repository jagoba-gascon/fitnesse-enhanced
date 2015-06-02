package com.jago.fitnesse.jsonContents;

import org.json.JSONObject;

import fitnesse.FitNesseContext;
import fitnesse.authentication.SecureOperation;
import fitnesse.authentication.SecureReadOperation;
import fitnesse.authentication.SecureResponder;
import fitnesse.http.Request;
import fitnesse.http.Response;
import fitnesse.http.SimpleResponse;
import fitnesse.wiki.PageCrawler;
import fitnesse.wiki.PageData;
import fitnesse.wiki.PathParser;
import fitnesse.wiki.WikiPage;
import fitnesse.wiki.WikiPagePath;


public class SuiteResponder implements SecureResponder {

	private static final String APPLICATION_TYPE = "application/json";
	private static final String PAGE_CHILDS = "childs";
	private static final String PAGE_TYPE = "type";
	private static final String PAGE_NAME = "name";

	@Override
	public Response makeResponse(FitNesseContext context, Request request) {
		return makeResponseWithJson(context, request);
	}

	private SimpleResponse makeResponseWithJson(FitNesseContext context, Request request) {
		System.out.println(context.getClass());
		PageCrawler pageCrawler = context.root.getPageCrawler();
		String pageName = request.getResource();
		WikiPagePath resourcePath = PathParser.parse(pageName);
		WikiPage page = pageCrawler.getPage(context.root, resourcePath);

		JSONObject directoryStructure = buildJson(page);

		SimpleResponse response = new SimpleResponse();
		response.setContentType(APPLICATION_TYPE);
		response.setContent(directoryStructure.toString(1));
		return response;
	}

	private JSONObject buildJson(WikiPage page) {
		JSONObject json = new JSONObject();
		json.put(PAGE_NAME, page.getName());
		json.put(PAGE_TYPE, getCheckedAttribute(page.getData(), PageData.PAGE_TYPE_ATTRIBUTES));
		for (int i = 0; i < page.getChildren().size(); i++) {
			json.append(PAGE_CHILDS, buildJson(page.getChildren().get(i)));			
		}
		return json;
	}

	private String getCheckedAttribute(PageData pageData, String[] attributes) {
		for (int i = attributes.length - 1; i > 0; i--) {
			if (pageData.hasAttribute(attributes[i]))
				return attributes[i];
		}
		return attributes[0];//return static
	}

	@Override
	public SecureOperation getSecureOperation() {
		return new SecureReadOperation();
	}

}
