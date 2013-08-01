package org.bitbucket.dollar.functions;


/**
 * Represents a Function with two arguments.
 * 
 * @param <T>
 *            the type of the 1st input object to the apply operation.
 * @param <U>
 *            the type of the 2nd input object to the apply operation.
 * @param <R>
 *            the return type
 */
@Functional
public interface BiFunction<T, U, R> {

    /** Yield an appropriate result object for the input objects. */
    R apply(T t, U u);

}
