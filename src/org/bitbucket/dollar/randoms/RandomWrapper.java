package org.bitbucket.dollar.randoms;

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
import java.util.Random;

import org.bitbucket.dollar.AbstractWrapper;
import org.bitbucket.dollar.Dollar;
import org.bitbucket.dollar.Dollar.Wrapper;
import org.bitbucket.dollar.Preconditions;

/**
 * Implements the most basic common functionality of Random wrappers.
 * 
 * @param <T>
 *            Type of which to make random values.
 */
public abstract class RandomWrapper<T> extends AbstractWrapper<T> implements
		Dollar.RandomWrapper<T> {

	protected Random random;
	protected int samples;

	public RandomWrapper(Random random, int samples) {
		generator(random);
		samples(samples);
	}

	@Override
	public final RandomWrapper<T> samples(int newSamples) {
		Preconditions.require(newSamples > 0, "samples must be positive");
		samples = newSamples;
		return this;
	}

	@Override
	public final Dollar.RandomWrapper<T> generator(Random random) {
		Preconditions.requireNotNull(random , "random must be non-null");
		this.random = random;
		return this;
	}

	@Override
	public Wrapper<T> shuffle(Random random) {
		return this;
	}

	@Override
	public Wrapper<T> reverse() {
		return this;
	}

	@Override
	public Wrapper<T> slice(int i, int j) {
		if (i >= 0 && j < samples) {
			samples = j - i;
		}

		return this;
	}

	@Override
	public int size() {
		return samples;
	}

}
