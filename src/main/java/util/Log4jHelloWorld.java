package util;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
 
public class Log4jHelloWorld {
 
    static final Logger logger = Logger.getLogger(Log4jHelloWorld.class);
 
    public static void main(String[] args)
    {
        //Configure logger
        BasicConfigurator.configure();
        logger.debug("Hello World!");
    }
}
