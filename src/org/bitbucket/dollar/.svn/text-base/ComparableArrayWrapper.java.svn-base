package org.bitbucket.dollar;

import java.util.Arrays;
import java.util.Collections;

import org.bitbucket.dollar.Dollar.ComparableWrapper;


/**
 * Implements functions possible on comparables, like min and max.
 * 
 * @author Adam L. Davis
 * 
 */
public class ComparableArrayWrapper<T extends Comparable<T>> extends
        ArrayWrapper<T> implements ComparableWrapper<T> {

    public ComparableArrayWrapper(T[] array) {
        super(array);
    }

    @Override
    public T max() {
        if (size() == 0) {
            return null;
        }
        return Collections.max(Arrays.asList(array));
    }

    @Override
    public T min() {
        if (size() == 0) {
            return null;
        }
        return Collections.min(Arrays.asList(array));
    }

}
