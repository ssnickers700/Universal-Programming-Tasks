package zad2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class Futil {


    static void processDir(String dirName, String resultFileName) {

        Path destFile = Paths.get(resultFileName);

        try {
            Files.walk(Paths.get(dirName)).forEach(e -> {

                try {
                    Files.write(destFile, Files.readAllLines(e, Charset.forName("Cp1250")), Charset.forName("UTF-8"),
                            new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.APPEND});
                } catch (IOException e1) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
