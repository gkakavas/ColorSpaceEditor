package duth.dip.cse.engine.utils;

import java.util.Random;

public class FileNameGenerator {

    public static String generateFilename(){
        int i = 5;
        var random = new Random();
        var charBuffer = new char[i];

        while(i>0){
            charBuffer[i-1] = (char) ('a' + random.nextInt(26));
            i--;
        }

        return new StringBuilder()
                .append(System.currentTimeMillis())
                .append("_")
                .append(charBuffer)
                .toString();
    }
}
