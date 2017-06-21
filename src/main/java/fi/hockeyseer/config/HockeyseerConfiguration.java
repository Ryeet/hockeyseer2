package fi.hockeyseer.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by alekstu on 31.5.2017.
 */

@SpringBootConfiguration
@ComponentScan
@EnableAutoConfiguration
@EntityScan(basePackages = "fi.hockeyseer.domain")
@EnableJpaRepositories(basePackages = "fi.hockeyseer.repository")
public class HockeyseerConfiguration {
}
