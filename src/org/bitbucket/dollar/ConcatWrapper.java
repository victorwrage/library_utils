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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bitbucket.dollar.Dollar.ListWrapper;
import org.bitbucket.dollar.Dollar.Wrapper;

// inspired by:
//   - my friend Mauro Codella
//   - http://stackoverflow.com/questions/2494031
public class ConcatWrapper<T> extends AbstractWrapper<T> {

    // TODO: must be lazy
    private final List<T> list;

    public ConcatWrapper() {
        this(new LinkedList<T>());
    }

    public ConcatWrapper(List<T> list) {
        Preconditions.requireNotNull(list , "list must be non-null");
        this.list = list;
    }

    @Override
    public Wrapper<T> concat(Collection<T> items) {
        Preconditions.requireNotNull(items , "items must be non-null");
        addToCollection(list, items);
        return this;
    }

    @Override
    public Wrapper<T> concat(T... items) {
        Preconditions.requireNotNull(items , "items must be non-null");
        addToCollection(list, items);
        return this;
    }

    @Override
    public Wrapper<T> concat(Wrapper<T> wrapper) {
        if (wrapper != null) {
            addToCollection(list, wrapper);
        }

        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Wrapper<T> copy() {
        List<T> copy = new LinkedList<T>();
        addToCollection(copy, this);
        return new ConcatWrapper<T>(copy);
    }

    @Override
    public Wrapper<T> reverse() {
        Collections.reverse(list);
        return this;
    }

    @Override
    public Wrapper<T> shuffle(Random random) {
        Preconditions.requireNotNull(random , "random must be non-null");
        Collections.shuffle(list, random);
        return this;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<T> toList() {
        return list;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object object) {
        if (object instanceof ConcatWrapper) {
            ConcatWrapper concatWrapper = (ConcatWrapper) object;
            return list.equals(concatWrapper.list);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash *= 59 + list.hashCode();
        return hash;
    }
}
