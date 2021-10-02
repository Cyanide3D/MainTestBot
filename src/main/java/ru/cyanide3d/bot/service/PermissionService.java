package ru.cyanide3d.bot.service;

import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import ru.cyanide3d.bot.config.Permission;
import ru.cyanide3d.bot.model.PermissionEntity;
import ru.cyanide3d.bot.sql.DataStore;
import ru.cyanide3d.bot.sql.SqlRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PermissionService extends DataStore<Long, PermissionEntity> {

    private static final PermissionService INSTANCE = new PermissionService();



    public boolean isAvailable(Member user, Permission permission, String guildId) {

//        if (user.isOwner() || user.getId().equals("534894366448156682"))
//            return true;

        final List<String> roles = user.getRoles().stream()
                .map(ISnowflake::getId)
                .collect(Collectors.toList());

        return isHavePermission(permission, roles, guildId);
    }

    public void setPermission(String guildId, Permission permission, String roleId) {
        getOneByRoleId(guildId, roleId).ifPresentOrElse(entity -> {
            entity.setPermission(permission);
            saveOrUpdate(entity);
        }, () -> saveOrUpdate(new PermissionEntity(guildId, permission, roleId)));
    }

    private boolean isHavePermission(Permission permission, List<String> roles, String guildId) {

        if (roles.isEmpty())
            roles.add("1");

//        return sessionFactory.fromSession(session -> {
//            String q = "from PermissionEntity E where E.permission<=:permission and E.roleId in (:roles) and E.guildId=:guildId";
//            final Query<PermissionEntity> query = session.createQuery(q, PermissionEntity.class)
//                    .setParameter("guildId", guildId)
//                    .setParameter("roles", roles)
//                    .setParameter("permission", permission.getCode());
//            return !query.getResultList().isEmpty();
//        });

        return !loadEntityList(new SqlRequest(guildId).addField("roleId", roles).lessThan("permission", String.valueOf(permission.getCode()))).isEmpty();
    }

    private Optional<PermissionEntity> getOneByRoleId(String guildId, String roleId) {
        return loadEntity(new SqlRequest(guildId).addField("roleId", roleId));
    }

    public static PermissionService getInstance() {
        return INSTANCE;
    }
}
