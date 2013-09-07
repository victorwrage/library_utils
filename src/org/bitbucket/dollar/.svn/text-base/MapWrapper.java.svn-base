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
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bitbucket.dollar.Dollar.Wrapper;

public class MapWrapper<K, V> extends AbstractWrapper<K> implements Dollar.MapWrapper<K, V> {

    private Map<K, V> map;

    public MapWrapper(Map<K, V> map) {
        Preconditions.requireNotNull(map , "map must be non-null");
        this.map = map;
    }

    @Override
    public MapWrapper<K, V> add(K key, V value) {
        map.put(key, value);
        return this;
    }

    @Override
    public MapWrapper<K, V> threadSafe() {
        map = Collections.synchronizedMap(map);
        return this;
    }

    @Override
    public MapWrapper<K, V> immutable() {
        map = Collections.unmodifiableMap(map);
        return this;
    }

    @Override
    public Dollar.MapWrapper<K, V> checked(Class<K> requiredKeyClass, Class<V> requiredValueClass) {
        map = Collections.checkedMap(map, requiredKeyClass, requiredValueClass);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MapWrapper<K, V> copy() {
        Map<K, V> copy = (Map<K, V>) newInstanceOf(map.getClass());
        copy.putAll(map);
        return new MapWrapper<K, V>(copy);
    }

    @Override
    public Map<K, V> toMap() {
        return map;
    }

    @Override
    public Iterator<K> iterator() {
        return map.keySet().iterator(); // TODO: entry set iterator 
    }

    @Override
    public K[] toArray() {
        Set<K> keys = map.keySet();
        return iterableToArray(keys, keys.size());
    }

    @Override
    public int size() {
        return map.size();
    }

    // reverse() doesn't make sense for a Map
    @Override
    public Wrapper<K> reverse() {
        return this;
    }

    // as well as shuffle()
    @Override
    public Wrapper<K> shuffle(Random random) {
        return this;
    }

    @Override
    public Wrapper<K> sort() {
        return new SortedMapWrapper<K, V>(map);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object object) {
        if (object instanceof MapWrapper) {
            MapWrapper mapWrapper = (MapWrapper) object;
            return map.equals(mapWrapper.map);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
