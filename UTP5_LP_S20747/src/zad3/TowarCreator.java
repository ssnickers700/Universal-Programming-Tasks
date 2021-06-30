package zad3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TowarCreator extends Thread {

    Towary towary;
    int size;
    List<String> list;

    public TowarCreator(Towary towary) {
        this.towary = towary;

        Path path = Paths.get("../towary.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int size = list.size();
        this.size = size;
        this.list = list;
    }

    @Override
    public void run() {

        for (int i = 1; i < size + 1; i++) {
            String line = list.get(i-1);
            String[] arr = line.split(" ");
            towary.setTowar(new Towar(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
            if (i % 200 == 0) {
                System.out.println("Utworzono " + i + " towarów");
            }
        }
    }

    public int getSize() {
        return size;
    }
}
