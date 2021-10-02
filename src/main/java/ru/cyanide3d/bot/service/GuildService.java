package ru.cyanide3d.bot.service;

import ru.cyanide3d.bot.dto.GuildProperty;
import ru.cyanide3d.bot.sql.DataStore;
import ru.cyanide3d.bot.model.GuildEntity;
import ru.cyanide3d.bot.sql.SqlRequest;

import java.util.Optional;

public class GuildService extends DataStore<Long, GuildEntity> {

    private static final GuildService INSTANCE = new GuildService();

    public void setPrefix(String prefix, String guildId) {
        getOneGuildId(guildId).ifPresentOrElse(entity -> {
                    entity.setPrefix(prefix);
                    saveOrUpdate(entity);
                }, () -> saveOrUpdate(new GuildEntity(guildId, prefix))
        );
    }

    public GuildProperty getGuildProperty(String guildId) {
        return getOneGuildId(guildId)
                .map(GuildProperty::new)
                .orElse(new GuildProperty(guildId, "$"));
    }

    private Optional<GuildEntity> getOneGuildId(String guildId) {
        return loadEntity(new SqlRequest(guildId));
    }

    public static GuildService getInstance() {
        return INSTANCE;
    }

}
