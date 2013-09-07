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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import org.bitbucket.dollar.Dollar.ListWrapper;
import org.bitbucket.dollar.Dollar.Wrapper;


public class ArrayWrapper<T> extends AbstractWrapper<T> implements Dollar.ArrayWrapper<T> {

    protected T[] array;

    public ArrayWrapper(T[] array) {
        Preconditions.requireNotNull(array , "array must be non-null");
        this.array = array;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(array);
    }

    private static class ArrayIterator<T> implements Iterator<T> {

        private int i = 0;
        private final T[] array;

        public ArrayIterator(T[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return i < array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported for ArrayWrapper");
        }
    }

    @Override
    public Wrapper<T> copy() {
        T[] copy = Arrays.copyOf(array, array.length);
        return new ArrayWrapper<T>(copy);
    }

    @Override
    public Wrapper<T> fill(T object) {
        Arrays.fill(array, object);
        return this;
    }

    @Override
    public Wrapper<T> shuffle(Random random) {
        Preconditions.requireNotNull(random , "random must be non-null");
        for (int i = 0; i < size(); i++) {
            int j = random.nextInt(size());
            T item = array[i];
            array[i] = array[j];
            array[j] = item;
        }

        return this;
    }

    @Override
    public Wrapper<T> slice(int i, int j) {
        if (i >= 0 && j < array.length) {
            array = Arrays.copyOfRange(array, i, j);
        }
        return this;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Wrapper<T> sort() {
        Arrays.sort(array, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1 == null)
                    return 1;
                if (o2 == null)
                    return -1;
                Comparable<T> c1 = (Comparable<T>) o1;
                return c1.compareTo(o2);
            }
        });
        return this;
    }

    @Override
    public Wrapper<T> repeat(int n) {
        Preconditions.require(n > 0, "n must be positive");
        T[] repeated = Arrays.copyOf(array, n * size());
        for (int i = 1; i < n; i++) {
            System.arraycopy(array, 0, repeated, i * n, array.length);
        }
        return new ArrayWrapper<T>(repeated);
    }

    @Override
    public Wrapper<T> reverse() {
        for (int i = 0; i < array.length / 2; i++) {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return this;
    }

    @Override
    public T[] toArray() {
        return array;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object object) {
        if (object instanceof ArrayWrapper) {
            ArrayWrapper arrayWrapper = (ArrayWrapper) object;
            return Arrays.equals(array, arrayWrapper.array);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    // boxing arrays
    @SuppressWarnings("unchecked")
    protected static <T> T[] toBoxedArray(Class<T> boxClass, Object components) {
        int length = Array.getLength(components);
        Object res = Array.newInstance(boxClass, length);
        for (int i = 0; i < length; i++) {
            Array.set(res, i, Array.get(components, i));
        }
        return (T[]) res;
    }
    //
    static final Map<Class<?>, Class<?>> PRIMITIVE = new HashMap<Class<?>, Class<?>>();

    static {
        PRIMITIVE.put(Boolean.class, boolean.class);
        PRIMITIVE.put(Character.class, char.class);
        PRIMITIVE.put(Byte.class, byte.class);
        PRIMITIVE.put(Short.class, short.class);
        PRIMITIVE.put(Integer.class, int.class);
        PRIMITIVE.put(Long.class, long.class);
        PRIMITIVE.put(Float.class, float.class);
        PRIMITIVE.put(Double.class, double.class);
        PRIMITIVE.put(boolean.class, boolean.class);
        PRIMITIVE.put(char.class, char.class);
        PRIMITIVE.put(byte.class, byte.class);
        PRIMITIVE.put(short.class, short.class);
        PRIMITIVE.put(int.class, int.class);
        PRIMITIVE.put(long.class, long.class);
        PRIMITIVE.put(float.class, float.class);
        PRIMITIVE.put(double.class, double.class);
    }
    //
    static final Map<Class<?>, Object> ZERO = new HashMap<Class<?>, Object>();

    static {
        ZERO.put(Boolean.class, false);
        ZERO.put(boolean.class, false);
        ZERO.put(Character.class, (char) 0);
        ZERO.put(char.class, (char) 0);
        ZERO.put(Byte.class, (byte) 0);
        ZERO.put(byte.class, (byte) 0);
        ZERO.put(Short.class, (short) 0);
        ZERO.put(short.class, (short) 0);
        ZERO.put(Integer.class, 0);
        ZERO.put(int.class, 0);
        ZERO.put(Long.class, 0L);
        ZERO.put(long.class, 0L);
        ZERO.put(Float.class, 0.0F);
        ZERO.put(float.class, 0.0F);
        ZERO.put(Double.class, 0.0D);
        ZERO.put(double.class, 0.0D);
    }

    @SuppressWarnings("unchecked")
    protected static <T> Object toUnboxedArray(Class<?> wantedClass, T[] components) {
        Preconditions.requireNotNull(components , "array must be non-null");
        Class<?> primitive = PRIMITIVE.get(wantedClass);
        if (!ZERO.containsKey(components.getClass().getComponentType())) {
            return Array.newInstance(primitive, 0);
        }
        int length = components.length;
        Object unboxed = Array.newInstance(primitive, length);
        T zero = (T) ZERO.get(wantedClass);
        for (int i = 0; i < length; i++) {
            if (components[i] == null) {
                Array.set(unboxed, i, zero);
            } else {
                Array.set(unboxed, i, components[i]);
            }
        }
        return unboxed;
    }

    public static class BooleanArrayWrapper extends
            ComparableArrayWrapper<Boolean>
            implements Dollar.BooleanArrayWrapper {

        public BooleanArrayWrapper(boolean[] booleans) {
            super(toBoxedArray(Boolean.class, booleans));
        }

        public BooleanArrayWrapper(Boolean[] booleans) {
            super(booleans);
        }

        @Override
        public boolean[] toBooleanArray() {
            return (boolean[]) toUnboxedArray(boolean.class, array);
        }
    }

    public static class CharArrayWrapper extends
            ComparableArrayWrapper<Character>
            implements Dollar.CharArrayWrapper {

        public CharArrayWrapper(char[] characters) {
            super(toBoxedArray(Character.class, characters));
        }

        public CharArrayWrapper(Character[] characters) {
            super(characters);
        }

        @Override
        public char[] toCharArray() {
            return (char[]) toUnboxedArray(char.class, array);
        }

        @Override
        public byte[] toByteArray() {
            // toUnboxedArray doesn't work here.
            final byte[] arr = new byte[array.length];

            for (int index = 0; index < arr.length; index++) {
                if (null == array[index])
                    arr[index] = (byte) 0;
                else
                    arr[index] = (byte) ((Character) array[index]).charValue();
            }
            return arr;
        }

        @Override
        public int[] toIntArray() {
            return (int[]) toUnboxedArray(int.class, array);
        }

        @Override
        public long[] toLongArray() {
            return (long[]) toUnboxedArray(long.class, array);
        }

    }

    public static class ByteArrayWrapper extends ComparableArrayWrapper<Byte>
            implements
            Dollar.ByteArrayWrapper {

        public ByteArrayWrapper(byte[] bytes) {
            super(toBoxedArray(Byte.class, bytes));
        }

        public ByteArrayWrapper(Byte[] bytes) {
            super(bytes);
        }

        @Override
        public byte[] toByteArray() {
            return (byte[]) toUnboxedArray(byte.class, array);
        }

        @Override
        public char[] toCharArray() {
            // toUnboxedArray doesn't work here.
            final char[] arr = new char[array.length];

            for (int index = 0; index < arr.length; index++) {
                if (null == array[index])
                    arr[index] = (char) 0;
                else
                    arr[index] = (char) ((Byte) array[index]).byteValue();
            }
            return arr;
        }

        @Override
        public short[] toShortArray() {
            return (short[]) toUnboxedArray(short.class, array);
        }

        @Override
        public int[] toIntArray() {
            return (int[]) toUnboxedArray(int.class, array);
        }

        @Override
        public long[] toLongArray() {
            return (long[]) toUnboxedArray(long.class, array);
        }

        @Override
        public float[] toFloatArray() {
            return (float[]) toUnboxedArray(float.class, array);
        }

        @Override
        public double[] toDoubleArray() {
            return (double[]) toUnboxedArray(double.class, array);
        }
    }

    public static class ShortArrayWrapper extends ComparableArrayWrapper<Short>
            implements
            Dollar.ShortArrayWrapper {

        public ShortArrayWrapper(short[] shorts) {
            super(toBoxedArray(Short.class, shorts));
        }

        public ShortArrayWrapper(Short[] shorts) {
            super(shorts);
        }

        @Override
        public short[] toShortArray() {
            return (short[]) toUnboxedArray(short.class, array);
        }

        @Override
        public int[] toIntArray() {
            return (int[]) toUnboxedArray(int.class, array);
        }

        @Override
        public long[] toLongArray() {
            return (long[]) toUnboxedArray(long.class, array);
        }

        @Override
        public float[] toFloatArray() {
            return (float[]) toUnboxedArray(float.class, array);
        }

        @Override
        public double[] toDoubleArray() {
            return (double[]) toUnboxedArray(double.class, array);
        }
    }

    public static class IntegerArrayWrapper extends
            ComparableArrayWrapper<Integer>
            implements Dollar.IntegerArrayWrapper {

        public IntegerArrayWrapper(int[] ints) {
            super(toBoxedArray(Integer.class, ints));
        }

        public IntegerArrayWrapper(Integer[] ints) {
            super(ints);
        }

        @Override
        public int[] toIntArray() {
            return (int[]) toUnboxedArray(int.class, array);
        }

        @Override
        public long[] toLongArray() {
            return (long[]) toUnboxedArray(long.class, array);
        }

        @Override
        public float[] toFloatArray() {
            return (float[]) toUnboxedArray(float.class, array);
        }

        @Override
        public double[] toDoubleArray() {
            return (double[]) toUnboxedArray(double.class, array);
        }
    }

    public static class LongArrayWrapper extends ComparableArrayWrapper<Long>
            implements
            Dollar.LongArrayWrapper {

        public LongArrayWrapper(long[] longs) {
            super(toBoxedArray(Long.class, longs));
        }

        public LongArrayWrapper(Long[] longs) {
            super(longs);
        }

        @Override
        public long[] toLongArray() {
            return (long[]) toUnboxedArray(long.class, array);
        }

        @Override
        public float[] toFloatArray() {
            return (float[]) toUnboxedArray(float.class, array);
        }

        @Override
        public double[] toDoubleArray() {
            return (double[]) toUnboxedArray(double.class, array);
        }
    }

    public static class FloatArrayWrapper extends ComparableArrayWrapper<Float>
            implements
            Dollar.FloatArrayWrapper {

        public FloatArrayWrapper(float[] floats) {
            super(toBoxedArray(Float.class, floats));
        }

        public FloatArrayWrapper(Float[] floats) {
            super(floats);
        }

        @Override
        public float[] toFloatArray() {
            return (float[]) toUnboxedArray(float.class, array);
        }

        @Override
        public double[] toDoubleArray() {
            return (double[]) toUnboxedArray(double.class, array);
        }
    }

    public static class DoubleArrayWrapper extends
            ComparableArrayWrapper<Double>
            implements Dollar.DoubleArrayWrapper {

        public DoubleArrayWrapper(double[] doubles) {
            super(toBoxedArray(Double.class, doubles));
        }

        public DoubleArrayWrapper(Double[] doubles) {
            super(doubles);
        }

        @Override
        public double[] toDoubleArray() {
            return (double[]) toUnboxedArray(Double.class, array);
        }
    }
}