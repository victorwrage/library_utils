package org.bitbucket.dollar;

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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RepeatWrapper<T> extends AbstractWrapper<T> {

    protected final ConcatWrapper<T> concatWrapper;

    public RepeatWrapper(ConcatWrapper<T> concatWrapper) {
        this.concatWrapper = concatWrapper;
    }

    public RepeatWrapper(Wrapper<T> wrapper, int n) {
        Preconditions.requireNotNull(wrapper , "n must be positive");
        Preconditions.require(n > 0, "n must be positive");
        concatWrapper = new ConcatWrapper<T>(new LinkedList<T>());
        for (int i = 0; i < n; i++) {
            concatWrapper.concat(wrapper.copy());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return concatWrapper.iterator();
    }

    @Override
    public Wrapper<T> shuffle(Random random) {
        Preconditions.requireNotNull(random , "random must be non-null");
        return concatWrapper.shuffle(random);
    }

    @Override
    public int size() {
        return concatWrapper.size();
    }

    @Override
    public Wrapper<T> copy() {
        return new RepeatWrapper<T>((ConcatWrapper<T>) concatWrapper.copy());
    }

    @Override
    public Wrapper<T> sort() {
        return concatWrapper.sort();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object object) {
        if (object instanceof RepeatWrapper) {
            RepeatWrapper repeatWrapper = (RepeatWrapper) object;
            return concatWrapper.equals(repeatWrapper.concatWrapper); // XXX: deep equals?
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash *= 83 + concatWrapper.hashCode();
        return hash;
    }
}
