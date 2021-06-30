package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ListCreator<T> {

    public List<T> srcList;


    public ListCreator(List<T> list) {
        this.srcList = list;
    }

    public static<T> ListCreator<T> collectFrom(List<T> list) {

        ListCreator<T> listCreator = new ListCreator<>(list);
        return listCreator;
    }

    public ListCreator<T> when(Predicate<T> predicate) {

        List<T> dstList = new ArrayList<>();
        for (T element : this.srcList) {
            if (predicate.test(element)) {
                dstList.add(element);
            }
        }
        srcList = dstList;
        return this;
    }

    public <S> List<S> mapEvery(Function<T, S> function) {

        List<S> dstList = new ArrayList<>();
        for (T element : this.srcList) {
            dstList.add(function.apply(element));
        }
        return dstList;
    }
}
