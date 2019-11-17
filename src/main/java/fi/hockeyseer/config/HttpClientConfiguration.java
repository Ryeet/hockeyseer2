package fi.hockeyseer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.hockeyseer.dataimport.model.JsonSeason;
import okhttp3.OkHttpClient;

@Configuration
public class HttpClientConfiguration {


    
    @Bean
    public OkHttpClient okHttpClient() {
    	 return new OkHttpClient();
    }
    
    @Bean 
    public ObjectMapper objectMapper() {
       final ObjectMapper mapper = new ObjectMapper();
       mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       return mapper;
    }
    
    
}
