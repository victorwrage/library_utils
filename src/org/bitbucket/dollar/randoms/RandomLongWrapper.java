package org.bitbucket.dollar.randoms;

import java.util.Iterator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RandomLongWrapper extends RandomWrapper<Long> {

	private final long upTo;

	public RandomLongWrapper(Random random, int samples, long upTo) {
		super(random, samples);
		this.upTo = upTo;
	}

	@Override
	public Iterator<Long> iterator() {
		return new RandomIterator<Long>(samples) {

			@Override
			public Long nextRandom() {
				return random.nextLong() % upTo;
			}
		};
	}

	@Override
	public Wrapper<Long> copy() {
		return new RandomLongWrapper(random, samples, upTo);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RandomLongWrapper) {
			RandomLongWrapper randomLongWrapper = (RandomLongWrapper) object;
			return random == randomLongWrapper.random
					&& samples == randomLongWrapper.samples
					&& upTo == randomLongWrapper.upTo;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash *= 79 + random.hashCode();
		hash *= 79 + upTo;
		hash *= 79 + samples;
		return hash;
	}
}
