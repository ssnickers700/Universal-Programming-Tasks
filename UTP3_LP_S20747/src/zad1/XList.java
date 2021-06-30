package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T> {

    //List<T> xlist;

    public XList(T ... elements) {
        //xlist = new ArrayList<>();
        //Collections.addAll(xlist, elements);
        super(Arrays.asList(elements));
        //Collections.addAll(this, elements);
    }

    public XList(Collection<T> c) {
        //xlist.addAll(c);
        //xlist = new ArrayList<>(c);
        super(c);
    }

    public static <T> XList<T> of(T ... elements) {
        return new XList<>(elements);
    }

    public static <T> XList<T> of(Collection<T> c) {
        return new XList<>(c);
    }

    public static XList<String> charsOf(String s) {
        List<String> chars = new ArrayList<>();
        for(char c : s.toCharArray()){
            chars.add(String.valueOf(c));
        }
        return new XList<>(chars);
    }

    public static XList<String> tokensOf(String s) {
        return new XList<>(s.split("\\s"));
    }

    public static XList<String> tokensOf(String s, String regex) {
        return new XList<>(s.split(regex));
    }

    public XList<T> union(Collection<T> c) {
        List<T> tmp = new ArrayList<>(this);
        tmp.addAll(c);
        return new XList<>(tmp);
    }

    public XList<T> union(T ... elements) {
        List<T> tmp = new ArrayList<>(this);
        tmp.addAll(Arrays.asList(elements));
        return new XList<>(tmp);
    }

    public XList<T> diff(Collection<T> c) {
        List<T> tmp = new ArrayList<>(this);
        tmp.removeAll(c);
        return new XList<>(tmp);
    }

    public XList<T> unique() {
        List<T> tmp = new ArrayList<>();
        this.forEach( e -> {
            if(!tmp.contains(e)) {
                tmp.add(e);
            }});
        return new XList<>(tmp);
    }

    public XList<List<T>> combine() {
        XList<List<T>> cartesian_list = new XList<>();
        XList<List<T>> tmp = (XList<List<T>>) this;

        int total_size = 1;
        for (int i = 0; i < tmp.size(); i++) {
            total_size *= tmp.get(i).size();
        }
        for (int i = 0; i < total_size; i++) {
            cartesian_list.add(new XList<>());
        }

        int size_of_previous_batch = 1;
        for (int i = 0; i < tmp.size(); i++) {
            int index = 0;
            int inbatch_counter = -1;
            for (int j = 0; j < total_size; j++) {
                inbatch_counter++;
                if (inbatch_counter >= size_of_previous_batch) {
                    index++;
                    inbatch_counter = 0;
                }
                if (index >= tmp.get(i).size()) {
                    index = 0;
                }

                cartesian_list.get(j).add(tmp.get(i).get(index));
            }
            size_of_previous_batch *= tmp.get(i).size();
        }
        return cartesian_list;
    }


    public <S> XList<S> collect(Function<T, S> function) {
        XList<S> tmp = new XList<>();
        this.forEach(e -> tmp.add(function.apply(e)));
        return tmp;
    }

    public String join(String spilt) {
        StringBuilder sb = new StringBuilder();
        this.forEach(e -> { if(this.indexOf(e) != this.size()-1) sb.append(e).append(spilt);
                                else sb.append(e);});
        return sb.toString();
    }

    public String join() {
        StringBuilder sb = new StringBuilder();
        this.forEach(sb::append);
        return sb.toString();
    }

    public void forEachWithIndex(BiConsumer<T, Integer> biConsumer) {
        //this.forEach(e -> biConsumer.accept(e, this.indexOf(e)));
        for (int i = 0; i < this.size(); i++) {
            biConsumer.accept(this.get(i), i);
        }
    }
}
