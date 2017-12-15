package fi.hockeyseer.utility;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by alekstu on 5.6.2017.
 */
public class ConnUtil {

    private static Logger log = LoggerFactory.getLogger(ConnUtil.class);

    private final static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.92 Safari/537.36";

    public static String getResponseBody(String url) throws IOException {

        return new OkHttpClient().newCall(
                new Request.Builder().url(url).addHeader("USER_AGENT", USER_AGENT)
                        .build())
                .execute().body().string();
    }

}
