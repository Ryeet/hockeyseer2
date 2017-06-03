package fi.hockeyseer;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LickiLicki on 30-May-17.
 */
@SpringUI
@Theme("valo")
public class testUI extends UI {

    @Autowired
    private Service service;

    @Override
    protected void init(VaadinRequest request)
    {

        TextField name = new TextField("Enter your name:");
        Button button = new Button("Send");


        FormLayout form = new FormLayout();
        form.addComponent(name);
        form.addComponent(button);


        VerticalLayout layout = new VerticalLayout(form);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);



        button.addClickListener(e -> Notification.show(service.sayHello(name.getValue())));
    }
}
