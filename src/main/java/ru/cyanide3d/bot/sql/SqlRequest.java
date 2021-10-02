package ru.cyanide3d.bot.sql;

import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SqlRequest {

    private final StringBuilder query;
    private final List<Pair<String, Object>> values;


    public SqlRequest(String guildId) {
        this.query = new StringBuilder();
        this.values = new ArrayList<>();

        query.append("from CLASSNAME C where C.guildId=:guildId");
        values.add(Pair.of("guildId", guildId));
    }

    public SqlRequest addField(String field, String value) {
        query.append(" and C.").append(field).append("=:").append(field);
        values.add(Pair.of(field, value));
        return this;
    }

    public SqlRequest addField(String field, List values) {
        query.append(" and C.").append(field).append(" in (:").append(field).append(")");
        values.add(Pair.of(field, values));
        return this;
    }

    public SqlRequest lessThan(String field, String value) {
        query.append(" and C.").append(field).append("<:").append(field);
        values.add(Pair.of(field, value));
        return this;
    }

    public SqlRequest greaterThan(String field, String value) {
        query.append(" and C.").append(field).append(">:").append(field);
        values.add(Pair.of(field, value));
        return this;
    }

    public String getSqlQuery(String className) {
        return query.toString().replace("CLASSNAME", className);
//        return "from PermissionEntity C where C.guildId=:guildId and C.roleId in :roleId";
    }

    public <E> Query<E> setParametersToQuery(Query<E> query) {
        if (values.isEmpty())
            throw new UnsupportedOperationException("First take sql query use corresponding method.");

        for (Pair<String, Object> pair : values) {
            query.setParameter(pair.getLeft(), pair.getValue());
        }

        return query;
    }
}
