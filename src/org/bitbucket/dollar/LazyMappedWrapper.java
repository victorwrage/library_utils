/*
 * Dollar, http://dollar.bitbucket.org
 * (c) 2013 Adam L. Davis <adam.dav@gmail.com>
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
package org.bitbucket.dollar;

import java.util.Iterator;

import org.bitbucket.dollar.Dollar.Wrapper;
import org.bitbucket.dollar.functions.Function;

/**
 * Lazily converts values from one type to another.
 *
 * @author Adam L. Davis <adam.dav@gmail.com>
 *
 */
public class LazyMappedWrapper<R, T> extends AbstractWrapper<R> {

    private final Wrapper<T> delegate;
    private final Function<T, R> mapper;

    public LazyMappedWrapper(Wrapper<T> delegate, Function<T, R> mapper) {
        Preconditions.requireNotNull(delegate , "delegate must be non-null");
        Preconditions.requireNotNull(mapper , "mapper must be non-null");
        this.delegate = delegate;
        this.mapper = mapper;
    }

    @Override
    public Wrapper<R> copy() {
        return new LazyMappedWrapper<R, T>(delegate.copy(), mapper);
    }

    @Override
    public Iterator<R> iterator() {
        final Iterator<T> unmapped = delegate.iterator();
        return new Iterator<R>() {
            @Override
            public boolean hasNext() {
                return unmapped.hasNext();
            }

            @Override
            public R next() {
                return mapper.apply(unmapped.next());
            }

            @Override
            public void remove() {
                unmapped.remove();
            }
        };
    }

    @Override
    public int size() {
        return delegate.size();
    }
}
