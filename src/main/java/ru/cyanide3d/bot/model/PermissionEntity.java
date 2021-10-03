package ru.cyanide3d.bot.model;

import ru.cyanide3d.bot.config.Permission;

import javax.persistence.*;

@Entity
@Table(name = "main_permission")
public class PermissionEntity implements DataEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "guild_id")
    private String guildId;
    @Basic
    private int permission;
    @Column(name = "role_id")
    private String roleId;

    public PermissionEntity() {
    }

    public PermissionEntity(String guildId, Permission permission, String roleId) {
        this.guildId = guildId;
        this.permission = permission.getCode();
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public Permission getPermission() {
        return Permission.getPermissionByCode(permission);
    }

    public void setPermission(Permission permission) {
        this.permission = permission.getCode();
    }
}
