package ru.cyanide3d.bot.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonMemberDefault{

	@JsonProperty("width")
	private int width;

	@JsonProperty("url")
	private String url;

	@JsonProperty("height")
	private int height;

	public int getWidth(){
		return width;
	}

	public String getUrl(){
		return url;
	}

	public int getHeight(){
		return height;
	}
}