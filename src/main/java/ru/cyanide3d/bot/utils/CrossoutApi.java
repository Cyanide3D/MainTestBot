package ru.cyanide3d.bot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cyanide3d.bot.dto.cossout.Item;

import java.net.URL;
import java.util.Optional;

public class CrossoutApi {
    private static final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(CrossoutApi.class);

    private static final String SCHEME = "https";
    private static final String HOST = "crossoutdb.com";
    private static final String API_VERSION = "v1";
    private static final String API = "api";

    private static String url;

    static {
        objectMapper = new ObjectMapper();
        try {
            url = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPathSegments(API, API_VERSION)
                    .build().toString();
        } catch (Exception e) {
            log.error("Failed to make URL of Crossout API: ", e);
        }
    }

    public static Optional<Item> getFuelItem() {
        return getItemById(106);
    }

    public static Optional<Item> getElectricItem() {
        return getItemById(201);
    }

    private static Optional<Item> getItemById(int id) {
        try {
            return Optional.ofNullable(objectMapper.readValue(new URL(url + "/item/" + id), Item[].class)[0]);
        } catch (Exception e) {
            log.error("Failed to load item from Crossout DB: ", e);
            return Optional.empty();
        }
    }

}
