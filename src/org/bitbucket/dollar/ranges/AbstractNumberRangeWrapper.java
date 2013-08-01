package org.bitbucket.dollar.ranges;

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
import static org.bitbucket.dollar.Preconditions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.bitbucket.dollar.AbstractWrapper;
import org.bitbucket.dollar.ArrayWrapper;
import org.bitbucket.dollar.Dollar.RangeWrapper;
import org.bitbucket.dollar.Dollar.Wrapper;

public abstract class AbstractNumberRangeWrapper<T extends Number & Comparable<T>>
		extends AbstractWrapper<T> implements RangeWrapper<T> {

	protected final T startFrom;
	protected final T stopAt;
	protected final T stepBy;

	public AbstractNumberRangeWrapper(T startFrom, T stopAt, T stepBy) {
		final int stepComp = stepBy.compareTo(fromInt(0));
		requireNotNull(startFrom);
		requireNotNull(stopAt);
		requireNotNull(stepBy);

		require(stepComp != 0, "stepBy must not be zero");
		if (stepComp < 0) {
			require(startFrom.compareTo(stopAt) >= 0,
					"startFrom must be greater than or equal stopAt if step is negative");
		} else {
			require(startFrom.compareTo(stopAt) <= 0,
					"startFrom must be less than or equal stopAt if step is positive");
		}
		this.startFrom = startFrom;
		this.stopAt = stopAt;
		this.stepBy = stepBy;
	}

	@Override
	public Wrapper<T> copy() {
		return create(startFrom, stopAt, stepBy);
	}

	protected abstract AbstractNumberRangeWrapper<T> create(T startFrom,
			T stopAt, T stepBy);

	protected abstract T add(T x, T y);

	/**
	 * Divide x by y and then take the floor of the result.
	 * @param x dividee
	 * @param y divisor
	 * @return Equivalent of Math.floor(x/y).
	 */
	protected abstract T floorDivide(T x, T y);

	protected abstract T multiply(T x, T y);

	protected abstract T abs(T x);

	protected abstract T fromInt(int x);

	protected T negate(T x) {
		return multiply(x, fromInt(-1));
	}

	protected boolean greaterThan(T x, int y) {
		return x.compareTo(fromInt(y)) > 0;
	}

	protected T subtract(T x, T y) {
		return add(x, negate(y));
	}

	protected boolean isZero(T x) {
		return x.compareTo(fromInt(0)) == 0;
	}

	@Override
	public boolean contains(T it) {
		return test(it);
	}

	@Override
	public boolean test(T t) {
		if (startFrom.compareTo(stopAt) < 0) {
			return startFrom.compareTo(t) <= 0 && t.compareTo(stopAt) < 0
					&& isZero(mod(subtract(t, startFrom), stepBy));
		}
		return startFrom.compareTo(t) >= 0 && t.compareTo(stopAt) > 0
				&& isZero(mod(subtract(t, startFrom), stepBy));
	}

	/**
	 * Equivalent of (number % modder). Should be overridden by sub-classes for
	 * efficiency.
	 */
	protected T mod(T number, T modder) {
		return subtract(number,
				multiply(floorDivide(number, modder), modder));
	}

	@Override
	public Wrapper<T> reverse() {
		return create(subtract(stopAt, stepBy), subtract(startFrom, stepBy),
				negate(stepBy));
	}

	@Override
	public Wrapper<T> shuffle(Random random) {
		return new ArrayWrapper<T>(copy().toArray()).shuffle(random);
	}

	@Override
	public int size() {
		return floorDivide(abs(subtract(startFrom, stopAt)), abs(stepBy)).intValue();
	}

	@Override
	public Wrapper<T> sort() {
		if (greaterThan(stepBy, 0)) {
			return this;
		} else {
			return reverse();
		}
	}

	@Override
	public Iterator<T> iterator() {
		if (greaterThan(stepBy, 0)) {
			return new RangeIterator();
		} else {
			return new ReversedRangeIterator();
		}
	}

	class RangeIterator implements Iterator<T> {

		protected T i;

		public RangeIterator() {
			i = startFrom;
		}

		@Override
		public boolean hasNext() {
			return i.compareTo(stopAt) < 0;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T tmp = i;
			i = add(i, stepBy);
			return tmp;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"remove() not supported for RangeWrapper");
		}
	}

	class ReversedRangeIterator extends RangeIterator {

		@Override
		public boolean hasNext() {
			return i.compareTo(stopAt) > 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (null == object)
			return false;
		if (this == object)
			return true;
		if (this.getClass() != object.getClass())
			return false;

		@SuppressWarnings("unchecked")
		AbstractNumberRangeWrapper<T> rangeWrapper = (AbstractNumberRangeWrapper<T>) object;
		return startFrom.equals(rangeWrapper.startFrom)
				&& stopAt.equals(rangeWrapper.stopAt)
				&& stepBy.equals(rangeWrapper.stepBy);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(new Object[] { startFrom, stopAt, stepBy });
	}

}
