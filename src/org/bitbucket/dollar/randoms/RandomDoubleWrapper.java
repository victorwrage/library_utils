package org.bitbucket.dollar.randoms;

import java.util.Iterator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RandomDoubleWrapper extends RandomWrapper<Double> {

	private final double upTo;

	public RandomDoubleWrapper(Random random, int samples, double upTo) {
		super(random, samples);
		this.upTo = upTo;
	}

	@Override
	public Iterator<Double> iterator() {
		return new RandomIterator<Double>(samples) {

			@Override
			public Double nextRandom() {
				return random.nextDouble() * upTo;
			}
		};
	}

	@Override
	public Wrapper<Double> copy() {
		return new RandomDoubleWrapper(random, samples, upTo);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RandomDoubleWrapper) {
			RandomDoubleWrapper randomDoubleWrapper = (RandomDoubleWrapper) object;
			return random == randomDoubleWrapper.random
					&& samples == randomDoubleWrapper.samples
					&& upTo == randomDoubleWrapper.upTo;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 29;
		hash *= 79 + random.hashCode();
		hash *= 79 + upTo;
		hash *= 79 + samples;
		return hash;
	}
}
