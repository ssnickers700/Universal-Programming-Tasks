package zad3;

public class Towary {

    Towar towar;
    boolean newTowar = false;

    synchronized void setTowar(Towar towar) {
        while (newTowar == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.towar = towar;
        newTowar = true;
        notifyAll();
    }

    synchronized Towar getTowar() {
        while (newTowar == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        newTowar = false;
        notifyAll();
        return towar;
    }
}
