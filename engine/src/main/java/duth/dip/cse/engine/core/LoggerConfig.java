package duth.dip.cse.engine.core;

import java.io.IOException;
import java.util.Arrays;
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
        fileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                java.util.Date date = new java.util.Date(logRecord.getMillis());
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
                String timestamp = sdf.format(date);
                return String.format("[%s] %s %s-%s%n",
                        timestamp,
                        logRecord.getLevel().getName(),
                        logRecord.getSourceClassName(),
                        logRecord.getMessage());
            }
        });
        fileHandler.setLevel(loggingLevel);
        logger.addHandler(fileHandler);
        return logger;
    }

}
