package zad3;

public class TowarCounter extends Thread{

    Towary towary;
    int size;
    int sum;

    public TowarCounter(Towary towary, int size) {
        this.towary = towary;
        this.size = size;
    }

    @Override
    public void run() {

        for (int i = 1; i < size + 1; i++) {
            Towar towar = towary.getTowar();
            sum += towar.getWaga();

            if (i % 100 == 0) {
                System.out.println("Policzono " + i + " towarów");
            }
        }
        System.out.println("Sumaryczna waga towarów wynosi: " + sum);
    }
}
