package ru.cyanide3d.bot.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnails{

	@JsonProperty("default")
	private JsonMemberDefault jsonMemberDefault;

	@JsonProperty("high")
	private High high;

	@JsonProperty("medium")
	private Medium medium;

	public JsonMemberDefault getJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public High getHigh(){
		return high;
	}

	public Medium getMedium(){
		return medium;
	}
}