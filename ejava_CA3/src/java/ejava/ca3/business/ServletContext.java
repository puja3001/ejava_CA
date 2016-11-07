/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

/**
 *
 * @author agarwal.puja
 */
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContext implements ServletContextListener{
    
    @Resource(mappedName = "concurrent/schedThreadPool") 
    private ManagedScheduledExecutorService executor;
        
    @Override 
    public void contextInitialized(ServletContextEvent sce) {
        HQBean hqBean = new HQBean();
        executor.scheduleWithFixedDelay(hqBean, 0l, 4l, TimeUnit.SECONDS);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        executor.shutdownNow();
    }
}

