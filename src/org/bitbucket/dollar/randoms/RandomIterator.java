package org.bitbucket.dollar.randoms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class RandomIterator<T> implements Iterator<T> {

	private int n;

	public RandomIterator(int samples) {
		this.n = samples;
	}

	@Override
	public boolean hasNext() {
		return n > 0;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(
				"remove() not supported for RandomWrapper");
	}

	// template
	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		n--;
		return nextRandom();
    }

	public abstract T nextRandom();
}