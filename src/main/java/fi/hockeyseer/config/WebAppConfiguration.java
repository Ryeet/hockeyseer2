package fi.hockeyseer.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by alekstu on 1.6.2017.
 */

@Configuration
@ComponentScan
@EnableTransactionManagement
public class WebAppConfiguration {



    private static final String PROPERTY_NAME_DATABASE_CLASSNAME = "spring.datasource.type";

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "spring.datasource.driver-class-name";

    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "spring.datasource.password";

    private static final String PROPERTY_NAME_DATABASE_URL = "spring.datasource.url";

    private static final String PROPERTY_NAME_DATABASE_USERNAME = "spring.datasource.username";



    @Resource
    private Environment env;


    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        HikariConfig dataSource = new HikariConfig();
     //   dataSource.setDataSourceClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_CLASSNAME));
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        return new HikariDataSource(dataSource);
    }


    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("fi.hockeyseer.domain");

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.jpa.hibernate.dialect"));

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto",
                env.getRequiredProperty("spring.jpa.hibernate.ddl-auto")
        );
        /*
        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy",
                env.getRequiredProperty("hibernate.ejb.naming_strategy")
        );
*/
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",
                env.getRequiredProperty("spring.jpa.show-sql")
        );

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",
                env.getRequiredProperty("spring.jpa.format-sql")
        );

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;


    }



}
