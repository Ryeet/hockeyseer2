package fi.hockeyseer.dataimport;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.hockeyseer.dataimport.model.JsonSeason;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by alekstu on 5.6.2017.
 */
@Component
public class NhlApiClient {
	
	
	private static Logger log = LoggerFactory.getLogger(NhlApiClient.class);
	private final static String USER_AGENT = "USER_AGENT";
    private final static String USER_AGENT_VALUE = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.92 Safari/537.36";
    private final static String API_URL = "https://statsapi.web.nhl.com/api/v1/";
    private final static String SCHEDULE = "schedule?";
    private final static String SCHEDULE_PARAMS = "&expand=schedule.linescore";
    
	private final OkHttpClient okHttpClient;
	private final ObjectMapper objectMapper;

	@Autowired
    public NhlApiClient(final OkHttpClient okHttpClient, final ObjectMapper objectMapper) {
		this.okHttpClient = okHttpClient;
		this.objectMapper = objectMapper;
	}

    
	
	public JsonSeason getSeason(final String seasonUrl) throws IOException {
		final String requestUrl = API_URL + SCHEDULE + seasonUrl + SCHEDULE_PARAMS;
		final ResponseBody responseBody = getResponseBody(requestUrl);
		final JsonSeason season = objectMapper.readValue(responseBody.string(), JsonSeason.class);
		return season;
	}


    private ResponseBody getResponseBody(String url) throws IOException {
    	final Request request =
    			new Request.Builder()
    			.url(url)
    			.addHeader(USER_AGENT, USER_AGENT_VALUE)
    			.build();
    	
    	final ResponseBody body = okHttpClient.newCall(request).execute().body();
    	return body;
    	
    }

}
