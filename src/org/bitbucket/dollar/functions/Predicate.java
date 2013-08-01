package org.bitbucket.dollar.functions;


/**
 * Determines if the input object matches some criteria.
 * 
 * @param <T>
 *            type of object to test.
 */
@Functional
public interface Predicate<T> {

    boolean test(T t);

}
