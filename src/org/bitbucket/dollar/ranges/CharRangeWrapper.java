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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.bitbucket.dollar.AbstractWrapper;
import org.bitbucket.dollar.CharSequenceWrapper;
import org.bitbucket.dollar.Dollar.RangeWrapper;
import org.bitbucket.dollar.Dollar.Wrapper;

public class CharRangeWrapper extends AbstractWrapper<Character> implements
        RangeWrapper<Character> {

    private final char from;
    private final char to;

    public CharRangeWrapper(char from, char to) {
        this.from = from;
        this.to = to;
    }

    class ForwardIterator implements Iterator<Character> {

        private char i = from;

        @Override
        public boolean hasNext() {
            return i <= to;
        }

        @Override
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return i++;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove() not supported for CharRangeWrapper");
        }
    }

    class ReverseIterator implements Iterator<Character> {

        private char i = from;

        @Override
        public boolean hasNext() {
            return i >= to;
        }

        @Override
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return i--;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove() not supported for CharRangeWrapper");
        }
    }

    @Override
    public Iterator<Character> iterator() {
        if (from < to) {
            return new ForwardIterator();
        } else {
            return new ReverseIterator();
        }
    }

    @Override
    public Wrapper<Character> copy() {
        return new CharRangeWrapper(from, to);
    }

    @Override
    public int size() {
        return Math.abs(to - from) + 1;
    }

    @Override
    public Wrapper<Character> shuffle(Random random) {
        return new CharSequenceWrapper(join()).shuffle(random);
    }

    @Override
    public Wrapper<Character> reverse() {
        return new CharRangeWrapper(to, from);
    }

    @Override
    public Wrapper<Character> sort() {
        if (from < to) {
            return this;
        } else {
            return this.reverse();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CharRangeWrapper) {
            CharRangeWrapper charRangeWrapper = (CharRangeWrapper) object;
            return charRangeWrapper.from == from && charRangeWrapper.to == to;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash *= 53 + from;
        hash *= 53 + to;
        return hash;
    }

    @Override
    public boolean test(Character t) {
        if (to < from) {
            return to <= t && t <= from;
        }
        return from <= t && t <= to;
    }

    @Override
    public boolean contains(Character it) {
        return test(it);
    }
}
