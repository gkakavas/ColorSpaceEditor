package duth.dip.cse.engine.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerConfig {

    public static Logger logger(Class<?> clazz, String outputPath, Level loggingLevel) throws IOException {
        var logger = Logger.getLogger(clazz.getName());
        Arrays.stream(logger.getHandlers()).forEach(logger::removeHandler);
        FileHandler fileHandler = new FileHandler(outputPath,true);
        fileHandler.setFormatter(formatter());
        fileHandler.setLevel(loggingLevel);
        logger.addHandler(fileHandler);
        return logger;
    }


    private static Formatter formatter(){
        return new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                Date date = new java.util.Date(logRecord.getMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
                String timestamp = sdf.format(date);
                return String.format(
                        "[%s] %s %s - [%s]:%s%n",
                        timestamp,
                        logRecord.getLevel().getName(),
                        logRecord.getSourceClassName(),
                        Thread.currentThread().getName(),
                        logRecord.getMessage()

                );
            }
        };
    }

}
