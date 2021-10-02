package ru.cyanide3d.bot.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsItem{

	@JsonProperty("snippet")
	private Snippet snippet;

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("etag")
	private String etag;

	@JsonProperty("id")
	private Id id;

	public Snippet getSnippet(){
		return snippet;
	}

	public String getKind(){
		return kind;
	}

	public String getEtag(){
		return etag;
	}

	public Id getId(){
		return id;
	}
}