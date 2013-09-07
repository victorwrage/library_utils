package org.bitbucket.dollar;

import java.util.Collections;
import java.util.List;

import org.bitbucket.dollar.Dollar.ComparableWrapper;


/**
 * Implements functions possible on comparables, like min and max.
 * 
 * @author Adam L. Davis
 * 
 */
public class ComparableListWrapper<T extends Comparable<T>> extends
		ListWrapper<T> implements ComparableWrapper<T> {

    public ComparableListWrapper(Iterable<T> iterable) {
        super(iterable);
    }

    public ComparableListWrapper(List<T> list) {
        super(list);
    }

    @Override
    public T max() {
        if (size() == 0) {
            return null;
        }
        return Collections.max(list);
    }

    @Override
    public T min() {
        if (size() == 0) {
            return null;
        }
        return Collections.min(list);
    }

}
