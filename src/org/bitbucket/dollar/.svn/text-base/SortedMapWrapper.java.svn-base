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

import java.util.Map;
import java.util.TreeMap;

import org.bitbucket.dollar.Dollar.Wrapper;

public class SortedMapWrapper<K, V> extends MapWrapper<K, V> {

    public SortedMapWrapper(Map<K, V> map) {
        super(new TreeMap<K, V>(map));
    }

    @Override
    public Wrapper<K> sort() {
        return this;
    }
}
