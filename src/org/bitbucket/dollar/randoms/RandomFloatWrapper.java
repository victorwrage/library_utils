package org.bitbucket.dollar.randoms;

import java.util.Iterator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RandomFloatWrapper extends RandomWrapper<Float> {

	private final float upTo;

	public RandomFloatWrapper(Random random, int samples, float upTo) {
		super(random, samples);
		this.upTo = upTo;
	}

	@Override
	public Iterator<Float> iterator() {
		return new RandomIterator<Float>(samples) {

			@Override
			public Float nextRandom() {
				return random.nextFloat() * upTo;
			}
		};
	}

	@Override
	public Wrapper<Float> copy() {
		return new RandomFloatWrapper(random, samples, upTo);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RandomFloatWrapper) {
			RandomFloatWrapper randomFloatWrapper = (RandomFloatWrapper) object;
			return random == randomFloatWrapper.random
					&& samples == randomFloatWrapper.samples
					&& upTo == randomFloatWrapper.upTo;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 23;
		hash *= 79 + random.hashCode();
		hash *= 79 + upTo;
		hash *= 79 + samples;
		return hash;
	}
}
