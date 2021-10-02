package ru.cyanide3d.bot.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Configuration {

    private static Configuration INSTANCE;
    private final SessionFactory sessionFactory;
    private final Properties properties;
    private final Logger logger = LoggerFactory.getLogger(Configuration.class);

    private Configuration() {
        final String profile = "hibernate.cfg.xml";
        sessionFactory = new org.hibernate.cfg.Configuration().configure(profile).buildSessionFactory();

        this.properties = new Properties();
        String config = "settings.properties";

        try {
            properties.load(new FileInputStream(new File(config)));
        } catch (Exception e) {
            logger.error("Failed load config file", e);
        }
    }

    public String getToken() {
        return properties.getProperty("TOKEN");
    }

    public String getOwnerId() {
        return properties.getProperty("OWNER_ID");
    }

    public List<Character> getAvailablePrefixes() {
        return Arrays.stream(properties.getProperty("AVAILABLE_PREFIXES").split(" "))
                .map(e -> e.charAt(0))
                .collect(Collectors.toList());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration();
        }

        return INSTANCE;
    }

}
