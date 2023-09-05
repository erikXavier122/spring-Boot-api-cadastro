package com.api.cadastro.config;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    private static final Logger logger = LogManager.getLogger(LoggerConfig.class);

    public LoggerConfig() {
        // Default constructor.
    }

    public static void setAnalyticsLog(String endpoint,Integer statusCode,String message,String initialTime, String finalTime) {
        ThreadContext.clearMap();
        ThreadContext.put("endpoint",endpoint);
        ThreadContext.put("status", String.valueOf(statusCode));
        ThreadContext.put("message",message);
        ThreadContext.put("datetime.ini", initialTime);
        ThreadContext.put("datetime.fin", finalTime);

        logger.log(Level.forName("ANALYTICS", statusCode), message);
        ThreadContext.clearMap();
    }

    public static void setErrorLog(String endpoint,Integer statusCode,String message,String initialTime, String finalTime) {
        ThreadContext.clearMap();
        ThreadContext.put("endpoint",endpoint);
        ThreadContext.put("status", String.valueOf(statusCode));
        ThreadContext.put("message",message);
        ThreadContext.put("datetime.ini",initialTime);
        ThreadContext.put("datetime.fin",finalTime);

        logger.log(Level.forName("ERROR",statusCode),message);
        ThreadContext.clearMap();
    }

}
