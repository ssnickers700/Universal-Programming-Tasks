package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {

    public T source;

    public InputConverter(T source) {
        this.source = source;
    }

    public <S> S convertBy(Function... funs){

        List results = new ArrayList<>();

        results.add(funs[0].apply(source));

        for (int i = 1; i < funs.length; i++) {
            results.add(funs[i].apply(results.get(i - 1)));
        }

        return (S) results.get(results.size()-1);
    }

}
