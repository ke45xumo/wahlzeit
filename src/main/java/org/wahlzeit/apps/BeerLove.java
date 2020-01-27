package org.wahlzeit.apps;

import org.wahlzeit.main.BeerMain;
import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.LogBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.logging.Logger;

/**
 * A simple ServletContextListener to startup and shutdown the Flowers application.
 */
public class BeerLove implements ServletContextListener {

    private static final Logger log = Logger.getLogger(Wahlzeit.class.getName());

    /**
     *
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();

            // configures logging
            String contextPath = sc.getContextPath();
            System.setProperty("contextPath", contextPath);
            log.config(LogBuilder.createSystemMessage().
                    addParameter("System property context path", contextPath).toString());

            // determines file system root path to resources
            File dummyFile = new File(sc.getRealPath("dummy.txt"));
            String rootDir = dummyFile.getParent();
            log.config(LogBuilder.createSystemMessage().
                    addParameter("Root directory", rootDir).toString());

            BeerMain.getInstance().startUp(true, rootDir);
        } catch (Exception ex) {
            log.warning(LogBuilder.createSystemMessage().
                    addException("Initializing context failed", ex).toString());
            throw new RuntimeException("End of story!", ex);
        }
    }

    /**
     *
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            BeerMain.getInstance().shutDown();
        } catch (Exception ex) {
            log.warning(LogBuilder.createSystemMessage().
                    addException("Shutting instance down failed", ex).toString());
        }
    }

}
