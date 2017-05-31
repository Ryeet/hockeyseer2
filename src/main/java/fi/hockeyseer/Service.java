package fi.hockeyseer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by LickiLicki on 30-May-17.
 */

@Component
public class Service {

    private final static Logger log = LoggerFactory.getLogger(Service.class);


    public String sayHello(String name)
    {
        log.debug("logging works");
        return "Hello, " + name;
    }
}
