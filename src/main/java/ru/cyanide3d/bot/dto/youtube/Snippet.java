package ru.cyanide3d.bot.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snippet{

	@JsonProperty("publishTime")
	private String publishTime;

	@JsonProperty("publishedAt")
	private String publishedAt;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("thumbnails")
	private Thumbnails thumbnails;

	@JsonProperty("channelId")
	private String channelId;

	@JsonProperty("channelTitle")
	private String channelTitle;

	@JsonProperty("liveBroadcastContent")
	private String liveBroadcastContent;

	public String getPublishTime(){
		return publishTime;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}

	public Thumbnails getThumbnails(){
		return thumbnails;
	}

	public String getChannelId(){
		return channelId;
	}

	public String getChannelTitle(){
		return channelTitle;
	}

	public String getLiveBroadcastContent(){
		return liveBroadcastContent;
	}
}