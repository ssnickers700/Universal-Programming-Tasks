/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad2;


public class Purchase {

    String idKlienta;
    String nazwisko;
    String imie;
    String nazwaTowaru;
    double cena;
    double zakupionaIlosc;

    public Purchase(String idKlienta, String nazwisko, String imie,
                    String nazwaTowaru, double cena, double zakupionaIlosc) {
        this.idKlienta = idKlienta;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.nazwaTowaru = nazwaTowaru;
        this.cena = cena;
        this.zakupionaIlosc = zakupionaIlosc;
    }

    @Override
    public String toString() {
        return idKlienta + ';' + nazwisko + ' ' + imie + ';' + nazwaTowaru + ';' + cena + ';' + zakupionaIlosc;
    }

    public double getKoszty() {
        return cena * zakupionaIlosc;
    }

    public String getidKlienta() {
        return idKlienta;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
