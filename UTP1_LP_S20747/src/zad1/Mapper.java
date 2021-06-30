/**
 *
 *  @author Leszczyński Patryk S20747
 *
 */

package zad1;


public interface Mapper<T, S> { // Uwaga: interfejs musi być sparametrtyzowany
    S map(T t);
}  
