package ge.edu.freeuni.sdp.service.scheduler.temperature;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by vato on 6/25/16.
 */
@WebListener()
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Start listening");
        TemperatureScheduler temperatureScheduler = new TemperatureScheduler();
        temperatureScheduler.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
