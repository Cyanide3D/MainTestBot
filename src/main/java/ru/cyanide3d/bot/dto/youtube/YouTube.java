package ru.cyanide3d.bot.dto.youtube;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YouTube{

	@JsonProperty("regionCode")
	private String regionCode;

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("nextPageToken")
	private String nextPageToken;

	@JsonProperty("pageInfo")
	private PageInfo pageInfo;

	@JsonProperty("etag")
	private String etag;

	@JsonProperty("items")
	private List<ItemsItem> items;

	public String getRegionCode(){
		return regionCode;
	}

	public String getKind(){
		return kind;
	}

	public String getNextPageToken(){
		return nextPageToken;
	}

	public PageInfo getPageInfo(){
		return pageInfo;
	}

	public String getEtag(){
		return etag;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}