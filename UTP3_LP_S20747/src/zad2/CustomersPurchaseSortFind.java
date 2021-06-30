/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {

    List<Purchase> purchases = new ArrayList<>();

    public void readFile(String fname) {

        Scanner sc = null;
        try {
            sc = new Scanner(new File(fname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("[; ]");
            purchases.add(new Purchase(line[0], line[1], line[2], line[3],
                                        Double.parseDouble(line[4]), Double.parseDouble(line[5])));
        }
    }

    public void showSortedBy(String byWhat) {

        System.out.println(byWhat);
        if (byWhat.equals("Nazwiska")) {
            purchases.sort(Comparator.comparing(Purchase::getNazwisko).thenComparing(Purchase::getidKlienta));
            purchases.forEach(System.out::println);
        } else if (byWhat.equals("Koszty")) {
            purchases.sort(Comparator.comparing(Purchase::getKoszty).reversed().thenComparing(Purchase::getidKlienta));
            purchases.forEach(e -> System.out.println(e + " (koszt: " + e.getKoszty() + ")"));
        }
        purchases.sort(Comparator.comparing(Purchase::getKoszty).thenComparing(Purchase::getidKlienta));
        System.out.println();
    }

    public void showPurchaseFor(String id) {

        System.out.println("Klient " + id);
        purchases.stream().filter(e -> e.getidKlienta().equals(id)).forEach(System.out::println);
        System.out.println();
    }
}
