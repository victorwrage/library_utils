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
import java.util.Random;

import org.bitbucket.dollar.Dollar.Wrapper;

public class RepeatCharSequenceWrapper extends AbstractWrapper<Character> {

    private final CharSequence charSequence;
    private final int count;

    public RepeatCharSequenceWrapper(CharSequence charSequence, int count) {
        this.charSequence = charSequence;
        this.count = count;
    }

    // the "raison d'être" of this class
    @Override
    public String join(String separator) {
        Separator sep = new Separator(separator);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(sep.toString());
            sb.append(charSequence);
        }

        return sb.toString();
    }

    private StringBuilder internalRepeat() {
        // TODO: use a LazyRepeatIterator
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(charSequence);
        }

        return sb;
    }

    @Override
    public Iterator<Character> iterator() {
        return new CharSequenceWrapper.CharSequenceIterator(internalRepeat());
    }

    @Override
    public Wrapper<Character> copy() {
        return new RepeatCharSequenceWrapper(charSequence, count);
    }

    @Override
    public int size() {
        return charSequence.length() * count;
    }

    @Override
    public Wrapper<Character> reverse() {
        return new CharSequenceWrapper(internalRepeat()).reverse();
    }

    @Override
    public Wrapper<Character> sort() {
        return new CharSequenceWrapper(internalRepeat()).sort();
    }

    @Override
    public Wrapper<Character> shuffle(Random random) {
        Preconditions.requireNotNull(random , "random must be non-null");
        return new CharSequenceWrapper(internalRepeat()).shuffle(random);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof RepeatCharSequenceWrapper) {
            RepeatCharSequenceWrapper repeatCharSequenceWrapper = (RepeatCharSequenceWrapper) object;
            return charSequence.equals(repeatCharSequenceWrapper.charSequence)
                    && count == repeatCharSequenceWrapper.count;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash *= 19 + charSequence.hashCode();
        hash *= 19 + count;
        return hash;
    }
}
