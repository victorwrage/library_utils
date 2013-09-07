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
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.bitbucket.dollar.ArrayWrapper.CharArrayWrapper;
import org.bitbucket.dollar.Dollar.Wrapper;

/**
 * Implements the Wrapper interface for CharSequences such as String or
 * StringBuilder. If object wrapped is a StringBuilder or StringBuffer it will
 * use its methods directly when possible.
 */
public class CharSequenceWrapper extends AbstractWrapper<Character> {

    private final CharSequence charSequence;

    public CharSequenceWrapper(CharSequence charSequence) {
        Preconditions.requireNotNull(charSequence,
                "charSequence must be non-null");
        this.charSequence = charSequence;
    }

    @Override
    public Wrapper<Character> repeat(int n) {
        Preconditions.require(n > 0, "n must be positive");
        return new RepeatCharSequenceWrapper(charSequence, n);
    }

    @Override
    public Wrapper<Character> copy() {
        // so that methods called on the old StringBuilder don't
        // mess with the copy's StringBuilder.
        return new CharSequenceWrapper(new StringBuilder(charSequence));
    }

    @Override
    public Wrapper<Character> reverse() {
        if (charSequence instanceof StringBuilder) {
            StringBuilder sb = (StringBuilder) charSequence;
            sb.reverse();
        } else if (charSequence instanceof StringBuffer) {
            StringBuffer sb = (StringBuffer) charSequence;
            sb.reverse();
        }
        return new CharSequenceWrapper(
                new StringBuilder(charSequence).reverse());
    }

    @Override
    public Wrapper<Character> shuffle(Random random) {
        Preconditions.requireNotNull(random, "random must be non-null");
        final StringBuilder stringBuilder = charSequence instanceof StringBuilder ? ((StringBuilder) charSequence)
                : new StringBuilder(charSequence);

        for (int i = 0; i < stringBuilder.length(); i++) {
            int j = random.nextInt(stringBuilder.length());
            char c = stringBuilder.charAt(i);
            stringBuilder.setCharAt(i, stringBuilder.charAt(j));
            stringBuilder.setCharAt(j, c);
        }
        return new CharSequenceWrapper(stringBuilder.toString());
    }

    @Override
    public int size() {
        return charSequence.length();
    }

    @Override
    public Wrapper<Character> slice(int i, int j) {
        if (i >= 0 && j < charSequence.length()) {
            return new CharSequenceWrapper(charSequence.subSequence(i, j));
        } else {
            return this;
        }
    }

    @Override
    public Iterator<Character> iterator() {
        return new CharSequenceIterator(charSequence);
    }

    public static class CharSequenceIterator implements Iterator<Character> {

        private final CharSequence charSequence;
        private int i;

        public CharSequenceIterator(CharSequence charSequence) {
            this.charSequence = charSequence;
            this.i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < charSequence.length();
        }

        @Override
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return charSequence.charAt(i++);
        }

        @Override
        public void remove() {
            if (charSequence instanceof StringBuilder) {
                ((StringBuilder) charSequence).deleteCharAt(i);
            } else if (charSequence instanceof StringBuffer) {
                ((StringBuffer) charSequence).deleteCharAt(i);
            } else
                throw new UnsupportedOperationException(
                        "remove() not supported for CharSequenceWrapper");
        }
    }

    @Override
    public Wrapper<Character> sort() {
        return new CharArrayWrapper(charSequenceToArray(charSequence)).sort();
    }

    @Override
    public Character[] toArray() {
        return new CharArrayWrapper(charSequenceToArray(charSequence))
                .toArray();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CharSequenceWrapper) {
            CharSequenceWrapper other = (CharSequenceWrapper) object;
            // necessary so the copy() is equal
            return Arrays.equals(charSequenceToArray(charSequence),
                    charSequenceToArray(other.charSequence));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return charSequence.hashCode();
    }

    @Override
    public String toString() {
        return charSequence.toString();
    }

    private static char[] charSequenceToArray(CharSequence charSequence) {
        char[] array = new char[charSequence.length()];

        for (int i = 0; i < charSequence.length(); i++) {
            array[i] = charSequence.charAt(i);
        }

        return array;
    }
}
