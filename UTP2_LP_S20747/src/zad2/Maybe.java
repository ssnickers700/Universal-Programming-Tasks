package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    public T val;

    public Maybe(T val) { this.val = val; }

    static public<T> Maybe<T> of(T val) {
        return new Maybe<>(val);
    }

    public void ifPresent(Consumer cons) {
        if (isPresent())
            cons.accept(val);
    }

    public <S> Maybe<S> map(Function<T, S> func) {
        if (isPresent()) {
            S result = func.apply(this.val);
            return Maybe.of(result);
        } else {
            return Maybe.of(null);
        }
    }

    public T get() {
        if(isPresent())
            return this.val;
        else {
            throw new NoSuchElementException(" maybe is empty");
        }
    }

    public boolean isPresent() {
        if(this.val != null)
            return true;
        else
            return false;
    }

    public T orElse(T defVal) {
        if(isPresent())
            return this.val;
        else
            return defVal;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if(!isPresent())
            return this;
        else if (pred.test(this.val))
            return this;
        else
            return Maybe.of(null);
    }

    @Override
    public String toString() {
        if (isPresent())
            return "Maybe has value " + this.val;
        else
            return "Maybe is empty";
    }
}
