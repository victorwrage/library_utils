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
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class ListWrapper<T> extends AbstractWrapper<T> implements Dollar.ListWrapper<T> {

    protected List<T> list;

    public ListWrapper(List<T> list) {
        Preconditions.requireNotNull(list , "list must be non-null");
        this.list = list;
    }

    public ListWrapper(Iterable<T> iterable) {
        this.list = new LinkedList<T>();
        addToCollection(list, iterable);
    }

    @Override
    public ListWrapper<T> copy() {
        List<T> copy = createList(list.getClass());
        addToCollection(copy, list);
        return new ListWrapper<T>(list);
    }

    @Override
    public Wrapper<T> shuffle(Random random) {
        Preconditions.requireNotNull(random , "random must be non-null");
        Collections.shuffle(list, random);
        return this;
    }

    @Override
    public Wrapper<T> reverse() {
        Collections.reverse(list);
        return this;
    }

    @Override
    public Wrapper<T> fill(T object) {
        Collections.fill(list, object);
        return this;
    }

    @Override
    public Wrapper<T> slice(int i, int j) {
        if (i >= 0 && j < size()) {
            list = list.subList(i, j);
        }

        return this;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Wrapper<T> sort() { 
        // just assume they're comparable
        return sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 == null)
                    return 1;
                if (o2 == null)
                    return -1;
                Comparable<T> c1 = (Comparable<T>) o1;
                return c1.compareTo(o2);
            }
        });
    }

    @Override
    public Wrapper<T> sort(Comparator<T> comparator) {
        Collections.sort(list, comparator);
        return this;
    }

    @Override
    public ListWrapper<T> add(T item) {
        list.add(item);
        return this;
    }

    @Override
    public ListWrapper<T> addAll(Iterable<T> items) {
        addToCollection(list, items);
        return this;
    }

    @Override
    public ListWrapper<T> threadSafe() {
        list = Collections.synchronizedList(list);
        return this;
    }

    @Override
    public ListWrapper<T> immutable() {
        final List<T> copy = createList(list.getClass());
        copy.addAll(list);
        list = Collections.unmodifiableList(copy);
        return this;
    }

    @Override
    public Dollar.ListWrapper<T> checked(Class<T> requiredClass) {
        list = Collections.checkedList(list, requiredClass);
        return this;
    }

    @Override
    public T[] toArray() {
        return iterableToArray(list, list.size());
    }

    @Override
    public List<T> toList() {
        return list;
    }

    @Override
    public ListWrapper<T> toListWrapper() {
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object object) {
        if (object instanceof ListWrapper) {
            ListWrapper listWrapper = (ListWrapper) object;
            return list.equals(listWrapper.list);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
