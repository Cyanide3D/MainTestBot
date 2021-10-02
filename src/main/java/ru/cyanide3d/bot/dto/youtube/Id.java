package ru.cyanide3d.bot.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Id{

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("videoId")
	private String videoId;

	public String getKind(){
		return kind;
	}

	public String getVideoId(){
		return videoId;
	}
}