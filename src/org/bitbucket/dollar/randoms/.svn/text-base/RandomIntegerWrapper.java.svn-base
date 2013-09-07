package org.bitbucket.dollar.randoms;

import java.util.Iterator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RandomIntegerWrapper extends RandomWrapper<Integer> {

	private final int upTo;

	public RandomIntegerWrapper(Random random, int samples, int upTo) {
		super(random, samples);
		this.upTo = upTo;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RandomIterator<Integer>(samples) {

			@Override
			public Integer nextRandom() {
				return random.nextInt(upTo);
			}
		};
	}

	@Override
	public Wrapper<Integer> copy() {
		return new RandomIntegerWrapper(random, samples, upTo);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RandomIntegerWrapper) {
			RandomIntegerWrapper randomIntegerWrapper = (RandomIntegerWrapper) object;
			return random == randomIntegerWrapper.random
					&& samples == randomIntegerWrapper.samples
					&& upTo == randomIntegerWrapper.upTo;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash *= 79 + random.hashCode();
		hash *= 79 + upTo;
		hash *= 79 + samples;
		return hash;
	}
}
