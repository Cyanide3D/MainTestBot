package ru.cyanide3d.bot.dto;

import ru.cyanide3d.bot.model.GuildEntity;

public class GuildProperty {

    private String guildId;
    private String prefix;

    public GuildProperty() {
    }

    public GuildProperty(GuildEntity entity) {
        this.guildId = entity.getGuildId();
        this.prefix = entity.getPrefix();
    }

    public GuildProperty(String guildId, String prefix) {
        this.guildId = guildId;
        this.prefix = prefix;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
