# Universal-Programming-Tasks

Universal Programming Techniques - university tasks concerning java 8 features: streams, lambda expressions, functional interfaces as also generics and jdbc

## Tasks

### UTP1

**Zad 1**

Stworzyć sparametryzowane interfejsy:

Selector - z metodą select, zwracającą true jesli argument spełnia warunek zapisany w metodzie i false w przeciwnym razie
Mapper - z metodą map, będącą dowolną funkcją: argument -> wynik
oraz sparametryzowaną klasę ListCreator, zawierającą:
statyczną metodę collectFrom (lista)
metodę when
metodę mapEvery
które działają w taki sposób, że symboliczny zapis:

    collectFrom(list1).when(selektor).mapEvery(mapper)
spowoduje utworzenie listy wynikowej, której elementy stanowią wybrane przez selektor elementy listy list1, przekształacone za pomocą podanego mappera.

Gdy w metodzie test1 selektor wybiera z listy liczby < 10, a mapper zwraca liczbę-argument powiększoną o 10, to na konsoli powinniśmy zobaczyć: [11, 17, 19]

Gdy w metodzie test2 selektor wybiera z listy napisy, których długość jest większa od 3 znaków, a mapper zwraca dlugość przekazanego napisu, powiększoną o 10, to na konsoli zobaczymy: [14, 17]

**Zad 2**

Lista dest zawiera informacje o cenach przelotów w postaci napisów:

port_wylotu port_przylotu cena_w_EUR
Należy utworzyć listę wynikową, której elementy będą opisywać ceny przelotów do poszczególnych miejsc (tylko) z Warszawy w PLN i wypisać na konsoli jej kolejne elementy.

Aby rozwiązać to zadanie, należy utworzyć sparametryzowaną klasę ListCreator, zawierającą: statyczną metodę collectFrom (lista) metodę when metodę mapEvery które działają w taki sposób, że symboliczny zapis:

    collectFrom(list).when(lambda-1).mapEvery(lambda-2)
spowoduje utworzenie listy wynikowej, której elementy stanowią wybrane przez lambda-1 elementy listy list, przekształcone za pomocą podanego lambda-2.

Uwagi: w zadaniu nie wolno korzystać z własnych interfejsów, klasa ListCreator i jej metody powinny działać dla list (źródłowej i docelowej) elementów dowolnego typu.

Program wyprowadzić na konsolę napisy:

- to HAV - price in PLN: 5160
- to DPS - price in PLN: 8600
- to HKT - price in PLN: 4300

**Zad 3**

Lista dest zawiera informacje o cenach przelotów w postaci napisów:

port_wylotu port_przylotu cena_w_EUR
Należy utworzyć listę wynikową, której elementy będą opisywać ceny przelotów do poszczególnych miejsc (tylko) z Warszawy w PLN i wypisać na konsoli jej kolejne elementy, używając następującego programu:

    public class Main {

    public static void main(String[] args) {
        // Lista destynacji: port_wylotu port_przylotu cena_EUR 
        List<String> dest = Arrays.asList(
        "bleble bleble 2000",
        "WAW HAV 1200",
        "xxx yyy 789",
        "WAW DPS 2000",
        "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = 
        /*<-- tu należy dopisać fragment
        * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
        * ani też żadnych własnych interfejsów
        */

        for (String r : result) System.out.println(r);
        }
    }
Program ma wyprowadzić na konsolę:

    to HAV - price in PLN: 5160
    to DPS - price in PLN: 8600
    to HKT - price in PLN: 4300
    
### UTP2

**Zad 2**

Zdefiniować klasę Maybe o następujących właściwościach.

Obiekty Maybe reprezentują kontenery, które mogą zawierać lub nie pojedynczą wartość. Motywacją do wprowadzenia takiej konstrukcji jest ułatwienie programowania w sytuacji, gdy zmienna może mieć wartość null, szczególnie kiedy wymagane jest jej dalsze bezpieczne przetwarzanie (na przykład za pomocą lambda-wyrażeń, oznaczających jakieś funkcje). Bezpieczne - to znaczy takie, które nie powoduje wyjątku NullPointerException.

Obiekty typu Maybe zawierają jakąś wartość lub są puste (nigdy nie powinny mieć wartości null). W klasie Maybe zdefiniować następujące metody:

- Maybe.of(x) - ta metoda statyczna zwraca obiekt Maybe, „opakowujący” wartość x, dowolnego typu referencyjnego.
- void ifPresent(Consumer cons) - jeżeli w obiekcie Maybe znajduje się wartość, wykonywana jest operacja cons z tą wartością jako argumentem, w przeciwnym razie - gdy obiekt Maybe jest pusty - nic się nie dzieje.
- Maybe map(Function func) - jeżeli w obiekcie jest wartość, wykonywana jest funkcja func z tą wartością jako argumentem i zwracany jest jej wynik „zapakowany” w nowy obiekt klasy Maybe (to opakowanie jest niezbędne, bo wynik mógłby być null, a tego chcemy uniknąć w ewentualnym dalszym przetwarzaniu; jeśli wynikiem funkcji jest null, zwracany jest pusty obiekt klasy Maybe).
- T get() zwraca zawartość obiektu Maybe, ale jeśli jest on pusty, powinna zgłosić wyjątek NoSuchElementException. boolean isPresent() - zwraca true jeśli w obiekcie Maybe zawarta jest wartośc, a false - gdy jest on pusty
- T orElse(T defVal) - zwraca zawartość obiektu Maybe lub domyślną wartosć defVal, jeśli obiekt Maybe jest pusty.
- Maybe filter(Predicate pred) - zwraca to Maybe, jeśli spełniony jest warunek pred lub to Maybe jest puste; zwraca puste Maybe, jeśli warunek pred jest niespełniony.

### UTP3

**Zad 1**

Stworzyć klasę XList, dostarczającą dodatkowych możliwości tworzenia list i operowania na nich. W klasie powinny znaleźć się odpowiednie konstruktory oraz statyczne metody of, umożliwiające tworzenie obiektów XList z innych kolekcji, tablic oraz argumentów podawanych przez przecinki.

Dodatkowo pomocnicze metody do tworzenia xlist z napisów:

- ofChars(napis) - zwraca x-listę znaków napisu,
- ofTokens(napis, [ sep ]) - zwraca x-listę symboli napisu, rozdzielonych separatorami z sep (jeśli brak - to białymi znakami).

Oprócz tego dostarczyć metod:

- union(dowolna_kolekcja) - zwraca nową x-list z dołączaną do tej x-list zawartością kolekcji,
- diff(dowolna_kolekcja) - zwraca x-list zawierającą te elementy tej x-list, które nie występują w kolekcji,
- unique() - zwraca nową x-list, która zawiera wszystkie niepowtarzające się elementy tej x-list
- combine() - zwraca x-listę list-kombinacji elementów z poszczególnych kolekcji, będących elementami tej x-listy
- collect(Function) - zwraca nową x-listę, której elementami są wyniki funkcji stosowanej wobec elementów tej x-listy,
- join([sep]) - zwraca napis, będący połączeniem elementów tej x-listy, z ewentualnie wstawionym pomiędzy nie separatorem,
- forEachWithIndex(konsumer_z_dwoma argumentami: element, index) - do iterowania po liście z dostępem i do elementów i do ich indeksów.

**Zad 3**

Firma software’owa prowadzi projekty w różnych językach programowania. Plik Prpgrammers.tsv z katalogu {user.home} zawiera informacje o programistach w postaci:

    język1<TAB>nazwisko(1)<TAB>nazwisko(2)<TAB> itd
    język2<TAB>nazwisko(1)<TAB>nazwisko(2)<TAB> itd
   Stworzyć klasę ProgLang, mającą:

- konstruktor ProgLang(String nazwaPliku), w którym następuje wczytanie pliku o podanej nazwie,
- metodę getLangsMap() – zwracająca mapę, w której pod kluczem nazwa języka znajduje się kolekcja programistów tego języka,
- metodę getProgsMap() – zwracającą mapę, w której pod kluczem nazwisko programisty znajduje się kolekcja języków, w których programuje,
- metodę getLangsMapSortedByNumOfProgs() – zwracającą mapę z wejściami język -> kolekcja programistów. uporządkowaną malejąco według liczby osób znających poszczególne języki, w przypadku równej liczbu porządek jest alfabetyczny wg nazw języków,
- metodę getProgsMapSortedByNumOfLangs() – zwracającą mapę z wejścimi programista -> kolekcja językow, uporządkowaną malejąco wg liczby języków znanych programiści; w przypadku równej liczby porządek jest alfabetyczny wg nazwisk,
- metodę getProgsMapForNumOfLangsGreaterThan(int n) – zwracającą mapę z wejściami programista -> kolekcja języków, dla ktorych liczba języków jest większa od podanego n.
- metodę sorted(…), wołaną z argumentami mapa i lambda-wyrażenie. Metoda zwraca posortowaną wersję dowolnej mapy przekazanej jako piewrszy argument, a porządek sortowania jest określony przez lambda wyrażenia, podane jako drugi argument,
- metodę filtered(…) z argumentami: dowolna mapa i lambda. Metoda zwraca mapę, która zawiera tylko te wejścia z przekazanej jako pierwszy argument mapy, które spelniają warunek podany jako drugi argument (lambda z wynikiem typu boolean).
- Metod sorted(…) lub filtered(…) użyć w oprogramowaniu innych, odpowiednich, metod klasy. Mają one jednak ogólniejsze znaczenia, bo mogą być używane dla dowolnych innych map z warunkami sortowania czy filtrowania, zadawanymi przez własściwe w danych przypadkach lambdy.
Programmers.tsv:

    Groovy	Z	Y	X	D
    
    Java	V	B	C	D	A	Z
    
    C++	G	J	H
    
    C#	P	S	Q	V	D
    
    Scala	A	D	A
    
Uwagi:

zgodność informacji wyjściowej z oczekiwanym wynikiem (w tym kolejność pokazywania danych) jest istotna – wynika z zastosowania odpowiednich map i innych klas kolekcyjnych. Uniwersalność metod sorted i filtered (możliwość ich zastosowania dla innych niż w zadaniu map).W klasie ProgLang nie wolno używac surowych typów.

### UTP4

**Zad 1/Zad 2**

Zadanie 'FileVisitor':

Katalog {user.home}/UTP6dir zawiera pliki tekstowe (z rozszerzeniem .txt) umieszczone w różnych podkatalogach. Kodowanie plików to Cp1250. Przeglądając rekursywnie drzewo katalogowe, zaczynające się od {user.home}/UTP6dir, wczytać wszystkie te pliki. i połączoną ich zawartość zapisać do pliku o nazwie UTP6res.txt, znadującym się w katalogu projektu. Kodowanie pliku UTP6res.txt winno być UTF-8.

Uwagi:

- trzeba dostarczyć definicji klasy Futil,
- należy zastosować FileVisitor do przeglądania katalogu,
- nalezy zalożyć, że na starcie programu wynikowego pliku nie ma.

**Zad 3**

Zadanie:

Na liście słów z Wiki link znaleźć wszystkie anagramy. Wypisać słowa z maksymalną liczbą anagramów oraz wszystkie ich anagramy w postaci:

    slowo anagram1 anagram2 ....
Program ma być bardzo krótki , dzięki zastosowaniu przetwarzania strumieniowego (java.util.stream).

### UTP5

**Zad 1**

Uruchamianie i zatrzymywanie równoległego działania kodów

Zbudować klasę StringTask, symulująca długotrwałe obliczenia, tu polegające na konkatenacji napisow.
Konstruktor klasy otrzymuje jako argument napis do powielenia oraz liczbę oznaczającą ile razy ten napis ma być powielony.
Klasa winna implementować interfejs Runnable, a w jej metodzie run() wykonywane jest powielenia napisu. Użycie '+' jest warunkiem obowiązkowym.

Obiekt klasy StringTask traktujemy jako zadanie, które może się wykonywać równolegle z innymi.
Możliwe stany zadania to:
- CREATED  - zadanie utworzone, ale nie zaczęło się jeszcze wykonywać,
- RUNNING - zadanie się wykonuje w odrebnym wątku
- ABORTED - wykonanie zadania zostało przerwane
- READY - zadanie zakończyło się pomyślnie i sa gotowe wyniki.

W klasie StringTask zdefiniować metody:
  - public String getResult()  - zwracającą wynik konkatenacji
  - public TaskState getState()  - zwracającą stan zadania
  - public void start() - uruchamiającą zadanie w odrębnym watku
  - public void abort() - przerywającą wykonanie kodzu zadania i działanie watku
  - public boolean isDone()  - zwracająca true, jeśli wykonanie zadania się zakończyło normalnie lub przez przerwanie, false w przeciwnym razie

**Zad 2**

Napisać program, w którym uruchamiane zadania pokazywane są na liście javax.swing.JList. Zadania z listy możemy odpytywac o ich stan, anulować, pokazywac ich wyniki, gdy są gotowe itp.

**Zad 3**

Kod, działający w wątku A czyta z pliku ../Towary.txt informacje o towarach w postaci:

id_towaru waga 

tworzy obiekty klasy Towar, zawierające przeczytane informacje oraz wyprowadza na konsolę informacje o liczbie utworzonych  obiektów. Informacja ma być wyprowadzana co 200 obiektów w postaci:

    utworzono 200 obiektów
    utworzono 400 obiektów
    utworzono 600 obiektów
    itd.

Kod działający równolegle w innym wątku (B) sięga po te obiekty, sumuje wagę  towarów i  wyprowadza na konsolę informację o przeprowadzonym sumowaniu co 100 obiektów np.:

    policzono wage 100 towarów
    policzono wage 200 towarów
    policzono wage 300 towarów
    itd.

Na końcu podaje sumaryczną wagę wszystkich towarów.

### UTP6

**Zad 1**

Biuro podróży otrzymuje od róznych kontrahentów (polskich, angielskich, niemieckich... ) pliki z ofertami wyjazdów-wycieczek. Każda oferta jest w jednym wierszu pliku i zawiera, rozdzielone znakami tabulacji:

lokalizacje_kontrahenta kraj  date_wyjazdu  date_powrotu miejsce cene symbol_waluty

gdzie:
- lokalizacja - napis,  oznaczający język_kraj (np. pl_PL, en_US; tak jak zwraca to metoda toString() z klasy Locale)
- kraj - nazwa kraju w języku kontrahenta,
- daty - (wyjazdu, powrotu) daty w formacie RRRR-MM-DD (np. 2015-12-31),
- miejsce - jedno z: [morze, jezioro, góry] - w języku kontrahenta,
- cena - liczba w formacie liczb, używanym w kraji kontrahenta,
- symbol_waluty = PLN, USD itp.


Napisać aplikację, która:
- dodaje zawartość plików ofert do bazy danych (dowolny silnik bazodanowy)
- jest zinternacjonalizowana - prezentuje klientowi w tabeli JTable  pełny zestaw ofert w wybranym przez niego języku  i wg wybranych ustawień regionalnych.

W ramach projektu Stworzyć klasy TravelData i Database.
W klasie TravelData zdefiniowac metodę:

 `List<String> travelData.getOffersDescriptionsList(String loc, String dateFormat)`
 
która zwraca listę napisów, każdy z których jest opisem jednej oferty z plików katalogu data, przedstawionym zgodnie z regułami i w języku lokalizacji loc i przy podanym formacie daty (możliwe formaty określa klasa SimpleDateFormat).

W klasie Database zapewnić utworzenie bazy danych i wpisanie do niej wszystkich ofert, wczytanych z plików (metoda createDb()) oraz otwarcie GUI z tabelą, pokazującą wczytane oferty.

Wszelkie operacje bazodanowe mogą być przeprowadzane tylko w klasie Database.










