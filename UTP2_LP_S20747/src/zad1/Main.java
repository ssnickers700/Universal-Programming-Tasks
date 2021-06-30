/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    Function<String, List<String>> flines = e -> {
      List<String> list = new ArrayList<>();

      try {
        Scanner scanner = new Scanner(new File(e));
        while (scanner.hasNextLine()) {
          list.add(scanner.nextLine());
        }

      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      return list;
    };

    Function<List<String>, String> join = e -> {
      StringBuilder sb = new StringBuilder();

      for (String s : e) {
        sb.append(s);
      }
      return sb.toString();
    };

    Function<String, List<Integer>> collectInts = e -> {
      e = e.replaceAll("[^0-9]+", " ");
      return Arrays.stream(e.trim().split(" ")).map(n -> Integer.parseInt(n)).collect(Collectors.toList());
    };

    Function<List<Integer>, Integer> sum = e -> {
      int sum1 = 0;
      for (int num : e) {
        sum1 += num;
      }
      return sum1;
    };




    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
