package fi.hockeyseer;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.hockeyseer.config.ProfileUtil;
import fi.hockeyseer.repository.TeamRepository;
import fi.hockeyseer.service.json.JsonSeason;
import fi.hockeyseer.utility.ConnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class HockeyseerApplication {


	private static Logger log = LoggerFactory.getLogger(HockeyseerApplication.class);

	private final Environment env;

	public HockeyseerApplication(Environment env) {
	    this.env = env;
    }

    @PostConstruct
    public void initApplication() {
        Collection<String> profiles = Arrays.asList(env.getActiveProfiles());
        log.debug("profiles" + profiles.toString());

    }

    @Bean
    public CommandLineRunner testing(TeamRepository teamRepository) {
        return (args) -> {
            log.debug("------------------------");
            log.debug("tadaaa");
            log.debug("team 1 = " + teamRepository.findOne(1L));



            String json = ConnUtil.getResponseBody("https://statsapi.web.nhl.com/api/v1/schedule?"
                    +         "startDate=2016-10-12&endDate=2017-04-11" +
                            "&expand=schedule.linescore");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonSeason season = mapper.readValue(json, JsonSeason.class);

           log.debug("team home = " + season.toString());
            //     log.debug("json :" + ));

        };
    }

	public static void main(String[] args) throws UnknownHostException {
        SpringApplication application =  new SpringApplication(HockeyseerApplication.class);
        ProfileUtil.addDefaultProfile(application);
        Environment env = application.run(args).getEnvironment();
            String protocol = "http";
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());

	}
}
