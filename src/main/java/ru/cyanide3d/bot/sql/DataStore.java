package ru.cyanide3d.bot.sql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.cyanide3d.bot.config.Configuration;
import ru.cyanide3d.bot.model.DataEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class DataStore<ID, E extends DataEntity<ID>> {

    public final SessionFactory sessionFactory;
    private final Class<E> entityClass;

    public DataStore() {
        Configuration configuration = Configuration.getInstance();
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.sessionFactory = configuration.getSessionFactory();
    }

    public E saveOrUpdate(E entity) {
        return sessionFactory.fromTransaction(session -> {
            session.saveOrUpdate(entity);

            return (E) session.get(entity.getClass(), entity.getId());
        });
    }

    public Optional<E> loadEntity(SqlRequest request) {
        return sessionFactory.fromSession(session -> getQuery(session, request).uniqueResultOptional());
    }

    public List<E> loadEntityList(SqlRequest request) {
        return sessionFactory.fromSession(session -> getQuery(session, request).list());
    }

    private Query<E> getQuery(Session session, SqlRequest request) {
        String query = request.getSqlQuery(entityClass.getSimpleName());

        return request.setParametersToQuery(session.createQuery(query, entityClass));
    }
}
