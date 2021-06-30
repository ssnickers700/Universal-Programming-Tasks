package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang<K, V> {

    Map<K, V> langsMap;
    Map<String, List<String>> progsMap;

    public ProgLang(String fname) {

        langsMap = new LinkedHashMap<>();
        progsMap = new LinkedHashMap<>();
        Scanner sc = null;

        try {
            sc = new Scanner(new File(fname));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split("\t");
            List<String> nameList = new ArrayList<>();

            for (int i = 1; i < data.length; i++) {
                nameList.add(data[i]);
            }
            nameList = nameList.stream().distinct().collect(Collectors.toList());
            langsMap.put((K) data[0], (V) nameList);


            for (String name : nameList) {
                if (progsMap.containsKey(name)) {
                    progsMap.get(name).add(data[0]);
                } else {
                    progsMap.put(name, new ArrayList<>(Arrays.asList(data[0])));
                }
            }
        }
    }

    public Map<K, V> getLangsMap() {
        return langsMap;
    }

    public Map<K, V> getProgsMap() {
        return (LinkedHashMap<K, V>) progsMap;
    }

    public Map<K, V> sorted(Map<K, V> srcMap, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> tmpList = new ArrayList<>(srcMap.entrySet());
        tmpList.sort(comparator);
        Map<K, V> tmpMap = new LinkedHashMap<>();
        tmpList.forEach( e -> tmpMap.put(e.getKey(), e.getValue()));
        return tmpMap;
    }

    public Map<K, V> filtered(Map<K, V> srcMap, Predicate<Map.Entry<K, V>> predicate) {
        Map<K, V> tmpMap = srcMap.entrySet().stream().filter(predicate).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return tmpMap;
    }

    public Map<K, V> getLangsMapSortedByNumOfProgs() {
        Map<K, V> tmpMap = new LinkedHashMap<>(

                sorted(langsMap, ((e1, e2) -> {
                List<String> list1 = (List<String>) e1.getValue();
                List<String> list2 = (List<String>) e2.getValue();
                Integer size1 = list1.size();
                Integer size2 = list2.size();
                return size2.compareTo(size1);
        })));

        return tmpMap;
    }

    public Map<K, V> getProgsMapSortedByNumOfLangs() {
        Map<K, V> tmpMap = new LinkedHashMap<>(

                sorted((LinkedHashMap<K, V>) progsMap, ((e1, e2) -> {
                List<String> list1 = (List<String>) e1.getValue();
                List<String> list2 = (List<String>) e2.getValue();
                Integer size1 = list1.size();
                Integer size2 = list2.size();
                return size2.compareTo(size1);
        })));

        return tmpMap;
    }

    public Map<K, V> getProgsMapForNumOfLangsGreaterThan(int i) {
        Map<K, V> tmpMap = new LinkedHashMap<>(

                filtered((LinkedHashMap<K, V>) progsMap, (e1 -> {
                List<String> list = (List<String>) e1.getValue();
                int size = list.size();
                return size > i;
        })));

        return tmpMap;
    }
}
