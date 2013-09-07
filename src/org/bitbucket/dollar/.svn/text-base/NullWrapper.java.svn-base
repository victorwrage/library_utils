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
import java.util.Iterator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

/**
 * A special wrapper that does nothing, efficiently.
 */
public class NullWrapper<T> extends AbstractWrapper<T> {

    @Override
    public Wrapper<T> copy() {
        return new NullWrapper<T>();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove() not supported for NullWrapper");
            }
        };
    }

    @Override
    public Wrapper<T> reverse() {
        return this;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Wrapper<T> sort() {
        return this;
    }

    @Override
    public Wrapper<T> shuffle(Random random) {
        return this;
    }

    @Override
    public Wrapper<T> slice(int i, int j) {
        return this;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof NullWrapper;
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
