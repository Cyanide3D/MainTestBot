package ru.cyanide3d.bot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cyanide3d.bot.dto.youtube.YouTube;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeApi {

    private final static ObjectMapper objectMapper;
    private final static Logger log = LoggerFactory.getLogger(YouTubeApi.class);

    private static final String SCHEME = "https";
    private static final String HOST = "www.googleapis.com";
    private static final String API_VERSION = "v3";
    private static final String API = "youtube";
    private static final String TYPE = "search";
    private static final String API_KEY = "AIzaSyCrxtwFXAmpY9fd4NAZEaK2lEydS1umNbU";

    private static String url;

    static {
        objectMapper = new ObjectMapper();

        try {
            url = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPathSegments(API, API_VERSION, TYPE)
                    .setParameter("part", "snippet")
                    .setParameter("type", "video")
                    .setParameter("key", API_KEY)
                    .build().toString();
        } catch (Exception e) {
            log.error("Failed to make URL of YouTube API: ", e);
        }
    }

    public static String getVideoUrlFromQuery(String in) {
        try {
            Pattern pattern = Pattern.compile("(https?://).([\\w-]{1,32}\\.[\\w-]{1,32})[^\\s@]*\\b");
            Matcher matcher = pattern.matcher(in);

            return matcher.find() ? matcher.group() : getVideoUrl(in);
        } catch (Exception e) {
            return in;
        }
    }

    private static String getVideoUrl(String in) throws java.io.IOException {
        YouTube youTube = objectMapper.readValue(new URL(url + "&q=" + URLEncoder.encode(in, StandardCharsets.UTF_8)), YouTube.class);

        return "https://www.youtube.com/watch?v=" + youTube.getItems().get(0).getId().getVideoId();
    }

}
