package org.bitbucket.dollar.randoms;

import java.util.Iterator;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RandomBooleanWrapper extends RandomWrapper<Boolean> {

	public RandomBooleanWrapper(Random random, int samples) {
		super(random, samples);
	}

	@Override
	public Iterator<Boolean> iterator() {
		return new RandomIterator<Boolean>(samples) {

			@Override
			public Boolean nextRandom() {
				return random.nextBoolean();
			}
		};
	}

	@Override
	public Wrapper<Boolean> copy() {
		return new RandomBooleanWrapper(random, samples);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof RandomBooleanWrapper) {
			RandomBooleanWrapper randomBooleanWrapper = (RandomBooleanWrapper) object;
			return random == randomBooleanWrapper.random
					&& samples == randomBooleanWrapper.samples;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 19;
		hash *= 79 + random.hashCode();
		hash *= 79 + samples;
		return hash;
	}
}
