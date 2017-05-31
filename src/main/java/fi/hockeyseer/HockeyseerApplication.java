package fi.hockeyseer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class HockeyseerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HockeyseerApplication.class, args);
	}
}
