package ru.cyanide3d.bot.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageInfo{

	@JsonProperty("totalResults")
	private int totalResults;

	@JsonProperty("resultsPerPage")
	private int resultsPerPage;

	public int getTotalResults(){
		return totalResults;
	}

	public int getResultsPerPage(){
		return resultsPerPage;
	}
}