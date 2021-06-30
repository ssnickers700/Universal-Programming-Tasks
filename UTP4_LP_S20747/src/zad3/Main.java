/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) throws IOException {


    Map<String, List<String>> map = new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream())).lines()
                                    .collect(Collectors.groupingBy(e -> {
                                      char[] arr = e.toCharArray();
                                      Arrays.sort(arr);
                                      return String.valueOf(arr);
                                    }));

    int max = map.entrySet().stream().map(e1 -> e1.getValue().size()).max(Comparator.comparingInt(i -> i)).get();
    map.values().stream().filter(e -> e.size() == max).sorted(Comparator.comparing(e -> e.get(0)))
            .forEach(e2 -> System.out.println(e2.stream().collect(Collectors.joining(" "))));
  }
}
