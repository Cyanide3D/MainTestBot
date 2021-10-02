package ru.cyanide3d.bot.dto.cossout;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item{

	@JsonProperty("rarityName")
	private String rarityName;

	@JsonProperty("imagePath")
	private String imagePath;

	@JsonProperty("typeName")
	private String typeName;

	@JsonProperty("description")
	private Object description;

	@JsonProperty("sellPrice")
	private double sellPrice;

	@JsonProperty("roi")
	private double roi;

	@JsonProperty("categoryName")
	private String categoryName;

	@JsonProperty("recipeId")
	private int recipeId;

	@JsonProperty("workbenchRarity")
	private int workbenchRarity;

	@JsonProperty("formatCraftingSellSum")
	private String formatCraftingSellSum;

	@JsonProperty("demandSupplyRatio")
	private double demandSupplyRatio;

	@JsonProperty("availableName")
	private String availableName;

	@JsonProperty("craftingBuySum")
	private double craftingBuySum;

	@JsonProperty("popularity")
	private int popularity;

	@JsonProperty("craftingSellSum")
	private double craftingSellSum;

	@JsonProperty("craftVsBuy")
	private String craftVsBuy;

	@JsonProperty("formatMargin")
	private String formatMargin;

	@JsonProperty("id")
	private int id;

	@JsonProperty("factionNumber")
	private int factionNumber;

	@JsonProperty("timestamp")
	private String timestamp;

	@JsonProperty("buyPrice")
	private double buyPrice;

	@JsonProperty("image")
	private String image;

	@JsonProperty("localizedName")
	private String localizedName;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("margin")
	private double margin;

	@JsonProperty("formatDemandSupplyRatio")
	private String formatDemandSupplyRatio;

	@JsonProperty("formatBuyPrice")
	private String formatBuyPrice;

	@JsonProperty("craftingMargin")
	private double craftingMargin;

	@JsonProperty("craftingResultAmount")
	private int craftingResultAmount;

	@JsonProperty("craftable")
	private int craftable;

	@JsonProperty("formatRoi")
	private String formatRoi;

	@JsonProperty("rarityId")
	private int rarityId;

	@JsonProperty("sellOffers")
	private int sellOffers;

	@JsonProperty("formatSellPrice")
	private String formatSellPrice;

	@JsonProperty("formatCraftingBuySum")
	private String formatCraftingBuySum;

	@JsonProperty("removed")
	private int removed;

	@JsonProperty("sortedStats")
	private Object sortedStats;

	@JsonProperty("meta")
	private int meta;

	@JsonProperty("faction")
	private Object faction;

	@JsonProperty("name")
	private String name;

	@JsonProperty("buyOrders")
	private int buyOrders;

	@JsonProperty("typeId")
	private int typeId;

	@JsonProperty("categoryId")
	private int categoryId;

	@JsonProperty("formatCraftingMargin")
	private String formatCraftingMargin;

	@JsonProperty("lastUpdateTime")
	private String lastUpdateTime;

	public String getRarityName(){
		return rarityName;
	}

	public String getImagePath(){
		return imagePath;
	}

	public String getTypeName(){
		return typeName;
	}

	public Object getDescription(){
		return description;
	}

	public double getSellPrice(){
		return sellPrice;
	}

	public double getRoi(){
		return roi;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public int getRecipeId(){
		return recipeId;
	}

	public int getWorkbenchRarity(){
		return workbenchRarity;
	}

	public String getFormatCraftingSellSum(){
		return formatCraftingSellSum;
	}

	public double getDemandSupplyRatio(){
		return demandSupplyRatio;
	}

	public String getAvailableName(){
		return availableName;
	}

	public double getCraftingBuySum(){
		return craftingBuySum;
	}

	public int getPopularity(){
		return popularity;
	}

	public double getCraftingSellSum(){
		return craftingSellSum;
	}

	public String getCraftVsBuy(){
		return craftVsBuy;
	}

	public String getFormatMargin(){
		return formatMargin;
	}

	public int getId(){
		return id;
	}

	public int getFactionNumber(){
		return factionNumber;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public double getBuyPrice(){
		return buyPrice;
	}

	public String getImage(){
		return image;
	}

	public String getLocalizedName(){
		return localizedName;
	}

	public int getAmount(){
		return amount;
	}

	public double getMargin(){
		return margin;
	}

	public String getFormatDemandSupplyRatio(){
		return formatDemandSupplyRatio;
	}

	public String getFormatBuyPrice(){
		return formatBuyPrice;
	}

	public double getCraftingMargin(){
		return craftingMargin;
	}

	public int getCraftingResultAmount(){
		return craftingResultAmount;
	}

	public int getCraftable(){
		return craftable;
	}

	public String getFormatRoi(){
		return formatRoi;
	}

	public int getRarityId(){
		return rarityId;
	}

	public int getSellOffers(){
		return sellOffers;
	}

	public String getFormatSellPrice(){
		return formatSellPrice;
	}

	public String getFormatCraftingBuySum(){
		return formatCraftingBuySum;
	}

	public int getRemoved(){
		return removed;
	}

	public Object getSortedStats(){
		return sortedStats;
	}

	public int getMeta(){
		return meta;
	}

	public Object getFaction(){
		return faction;
	}

	public String getName(){
		return name;
	}

	public int getBuyOrders(){
		return buyOrders;
	}

	public int getTypeId(){
		return typeId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public String getFormatCraftingMargin(){
		return formatCraftingMargin;
	}

	public String getLastUpdateTime(){
		return lastUpdateTime;
	}
}