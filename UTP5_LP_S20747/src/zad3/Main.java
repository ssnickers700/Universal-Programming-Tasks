/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad3;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    /*Path path = Paths.get("towary.txt");
    List<String> list = new ArrayList<>();

    for (int i = 1; i < 11001; i++) {
      list.add(i + " " + (int) (Math.random() * 10 + 1));
    }
    Files.write(path, list, StandardCharsets.UTF_8);*/
    Towary towary = new Towary();
    TowarCreator towarCreator = new TowarCreator(towary);
    TowarCounter towarCounter = new TowarCounter(towary, towarCreator.getSize());
    towarCreator.start();
    towarCounter.start();

  }
}
