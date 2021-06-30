/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana

    public List<T> srcList;


    public ListCreator(List<T> list) {
        this.srcList = list;
    }

    public static<T> ListCreator<T> collectFrom(List<T> list) {

        ListCreator<T> listCreator = new ListCreator<>(list);
        return listCreator;
    }

    public ListCreator<T> when(Selector<T> selector) {

        List<T> dstList = new ArrayList<>();
        for (T element : this.srcList) {
            if (selector.select(element)) {
                dstList.add(element);
            }
        }
        srcList = dstList;
        return this;
    }

    public <S> List<S> mapEvery(Mapper<T, S> mapper) {

        List<S> dstList = new ArrayList<>();
        for (T element : this.srcList) {
            dstList.add(mapper.map(element));
        }
        return dstList;
    }
}
