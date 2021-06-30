package zad1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Futil {


    static void processDir(String dirName, String resultFileName) {
        Path destFile = Paths.get(resultFileName);
        //List<String> listOut = new ArrayList<>();

        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    Files.write(destFile, Files.readAllLines(file, Charset.forName("Cp1250")), Charset.forName("UTF-8"),
                                                new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.APPEND});
                    /*List<String> listTmp = new ArrayList<>(Files.readAllLines(file, Charset.forName("Cp1250")));
                    for (String s : listTmp) {
                        listOut.add(s);
                    }
                    System.out.println(Files.readAllLines(file, Charset.forName("Cp1250")));*/
                    return FileVisitResult.CONTINUE;
                }
            });

           /* Files.write(destFile, listOut, Charset.forName("UTF-8"));*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
