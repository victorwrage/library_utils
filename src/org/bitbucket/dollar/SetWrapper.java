package org.bitbucket.dollar;

/*
 * Dollar, http://bitbucket.org/dfa/dollar
 * (c) 2010, 2011 Davide Angelocola <davide.angelocola@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.bitbucket.dollar.Dollar.Wrapper;

public class SetWrapper<T> extends AbstractWrapper<T> implements Dollar.SetWrapper<T> {

    private Set<T> set;

    public SetWrapper(Set<T> set) {
        Preconditions.requireNotNull(set , "set must be non-null");
        this.set = set;
    }

    @Override
    public SetWrapper<T> copy() {
        Set<T> copy = createSet(set.getClass());
        addToCollection(copy, this);
        return new SetWrapper<T>(copy);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public SetWrapper<T> add(T item) {
        set.add(item);
        return this;
    }

    @Override
    public SetWrapper<T> addAll(Iterable<T> items) {
        addToCollection(set, items);
        return this;
    }

    @Override
    public SetWrapper<T> threadSafe() {
        set = Collections.synchronizedSet(set);
        return this;
    }

    @Override
    public SetWrapper<T> immutable() {
        set = Collections.unmodifiableSet(set);
        return this;
    }

    @Override
    public Dollar.SetWrapper<T> checked(Class<T> requiredClass) {
        set = Collections.checkedSet(set, requiredClass);
        return this;
    }

    @Override
    public Set<T> toSet() {
        return set;
    }

    @Override
    public T[] toArray() {
        return iterableToArray(set, set.size());
    }

    // reverse() doesn't make sense for a Set
    @Override
    public Wrapper<T> reverse() {
        return this;
    }

    // as well as shuffle()
    @Override
    public Wrapper<T> shuffle(Random random) {
        return this;
    }

    @Override
    public Wrapper<T> sort() {
        return new SortedSetWrapper<T>(set);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object object) {
        if (object instanceof SetWrapper) {
            SetWrapper setWrapper = (SetWrapper) object;
            return set.equals(setWrapper.set);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        return set.toString();
    }
}
