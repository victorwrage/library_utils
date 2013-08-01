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
 * Implements the flatMap algorithm lazily, meaning that mapper is not called
 * until necessary. Calling {@link #size()} is not recommended as it will force
 * the entire list to be traversed. Also, do not call {@link Iterator#remove()}
 * on the iterator as it will throw an {@link UnsupportedOperationException}.
 * 
 * @author Adam L. Davis
 * 
 * @param <R>
 *            Type of elements provided by this Wrapper.
 * @param <T>
 *            Type of elements contained in the wrapped Wrapper.
 */
public class LazyFlatMappedWrapper<R, T> extends AbstractWrapper<R> {

	private final Wrapper<T> delegate;
	private final Function<T, Iterable<R>> mapper;

	public LazyFlatMappedWrapper(Wrapper<T> delegate,
			Function<T, Iterable<R>> mapper) {
		Preconditions.requireNotNull(delegate, "delegate must be non-null");
		Preconditions.requireNotNull(mapper, "mapper must be non-null");
		this.delegate = delegate;
		this.mapper = mapper;
	}

	@Override
	public Wrapper<R> copy() {
		return new LazyFlatMappedWrapper<R, T>(delegate.copy(), mapper);
	}

	@Override
	public Iterator<R> iterator() {
		final Iterator<T> unmapped = delegate.iterator();
		return new Iterator<R>() {
			Box<Iterator<R>> boxedIterator = Box.empty();

			@Override
			public boolean hasNext() {
				if (!boxedIterator.isEmpty() && boxedIterator.get().hasNext()) {
					return true;
				} else if (unmapped.hasNext()) {
					final T next = unmapped.next();
					boxedIterator = boxedIterator.put(mapper.apply(next)
							.iterator());
					return boxedIterator.get().hasNext();
				}
				return false;
			}

			@Override
			public R next() {
				if (hasNext())
					return boxedIterator.get().next();
				else
					return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/** Not recommended. */
	@Override
	public int size() {
		return new ListWrapper<R>(this).size();
	}

}
