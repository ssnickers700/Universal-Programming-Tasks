# Universal-Programming-Tasks

Universal Programming Techniques - university tasks concerning java 8 features: streams, lambda expressions, functional interfaces as also generics, jdbc

### Tasks

##### UTP1

**Zad1**

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

**Zad2**

