package org.bitbucket.dollar;

import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;


/**
 * Delegates all methods to another wrapper (sub-classes implement some
 * methods).
 * 
 * @author Adam L. Davis
 * 
 * @param <T>
 *            Generic Type contained by this wrapper.
 */
public abstract class AbstractDelegatingWrapper<T> extends AbstractWrapper<T>
        implements Wrapper<T> {

    protected Wrapper<T> delegate;
    
    // only constructor
    public AbstractDelegatingWrapper(Wrapper<T> d) {
        this.delegate = d;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public Wrapper<T> shuffle(Random random) {
        delegate = delegate.shuffle(random);
        return this;
    }

    @Override
    public Wrapper<T> sort(Comparator<T> comparator) {
        delegate = delegate.sort(comparator);
        return this;
    }

    @Override
    public Wrapper<T> concat(Wrapper<T> wrapper) {
        delegate = delegate.concat(wrapper);
        return this;
    }

    @Override
    public Wrapper<T> concat(T... items) {
        delegate = delegate.concat(items);
        return this;
    }

    @Override
    public Wrapper<T> concat(Collection<T> items) {
        delegate = delegate.concat(items);
        return this;
    }

    @Override
    public Wrapper<T> reverse() {
        delegate = delegate.reverse();
        return this;
    }

    @Override
    public Wrapper<T> fill(T object) {
        delegate = delegate.fill(object);
        return this;
    }

}
