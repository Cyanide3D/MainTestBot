package ru.cyanide3d.bot.model;

import javax.persistence.*;

@Entity
@Table(name = "main_guild_entity")
public class GuildEntity implements DataEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "guild_id")
    private String guildId;
    @Basic
    private String prefix;

    public GuildEntity() {
    }

    public GuildEntity(String guildId, String prefix) {
        this.guildId = guildId;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    @Override
    public String toString() {
        return "GuildEntity{" +
                "id=" + id +
                ", guildId='" + guildId + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
