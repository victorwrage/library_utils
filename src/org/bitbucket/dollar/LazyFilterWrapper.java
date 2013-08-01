package org.bitbucket.dollar;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.bitbucket.dollar.Dollar.Wrapper;
import org.bitbucket.dollar.functions.Predicate;

/**
 * Lazily uses predicate to decide what is included.
 *
 * @author Adam L. Davis <adam.dav@gmail.com>
 *
 */
public class LazyFilterWrapper<T> extends AbstractDelegatingWrapper<T> {

    private final Predicate<T> predicate;

    public LazyFilterWrapper(Wrapper<T> d, Predicate<T> predicate) {
        super(d);
        this.predicate = predicate;
    }

    @Override
    public int size() {
        int s = 0;

        for (T item : delegate) {
            if (predicate.test(item)) {
                s++;
            }
        }
        return s;
    }

    @Override
    public Iterator<T> iterator() {
        return new LazyFilteringIterator(delegate.iterator(), predicate);
    }

    @Override
    public Wrapper<T> copy() {
        return new LazyFilterWrapper<T>(delegate.copy(), predicate);
    }

    public static class LazyFilteringIterator<T> implements Iterator<T> {

        private Box<T> box = Box.empty();
        private final Iterator<T> unfiltered;
        private final Predicate<T> predicate;

        public LazyFilteringIterator(Iterator<T> unfiltered, Predicate<T> predicate) {
            this.unfiltered = unfiltered;
            this.predicate = predicate;
        }

        protected void prefetch() {
            if (!box.isEmpty()) {
                return;
            }
            while (unfiltered.hasNext()) {
                T element = unfiltered.next();
                if (predicate.test(element)) {
                    box = Box.of(element);
                    return;
                }
            }
            box = Box.empty();
        }

        @Override
        public boolean hasNext() {
            prefetch();
            return !box.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T next = box.get();
            box = Box.empty();
            return next;
        }

        @Override
        public void remove() {
            unfiltered.remove();
        }
    }
}
