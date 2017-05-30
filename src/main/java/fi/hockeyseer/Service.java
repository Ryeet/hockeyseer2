package fi.hockeyseer;

import org.springframework.stereotype.Component;

/**
 * Created by LickiLicki on 30-May-17.
 */

@Component
public class Service {

    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
