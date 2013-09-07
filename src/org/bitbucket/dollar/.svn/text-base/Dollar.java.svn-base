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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.bitbucket.dollar.functions.BiFunction;
import org.bitbucket.dollar.functions.Block;
import org.bitbucket.dollar.functions.Function;
import org.bitbucket.dollar.functions.Predicate;
import org.bitbucket.dollar.functions.TriFunction;
import org.bitbucket.dollar.randoms.RandomBooleanWrapper;
import org.bitbucket.dollar.randoms.RandomDoubleWrapper;
import org.bitbucket.dollar.randoms.RandomFloatWrapper;
import org.bitbucket.dollar.randoms.RandomIntegerWrapper;
import org.bitbucket.dollar.randoms.RandomLongWrapper;

/**
 * The single entry-point of the whole Dollar API.
 * 
 * Suggested usage: <code>
 * import static org.bitbucket.dollar.Dollar.*;
 * </code>
 */
public final class Dollar {

	private Dollar() {
	}

	/** Core interface for all wrappers. */
	public static interface Wrapper<T> extends Iterable<T> {

		/** Read-only size. */
		int size();

		/** Always returns fresh objects. */
		Wrapper<T> copy();

		/** Produce a String using given separator between each item. */
		String join(String separator);

		/** Same as join(""). */
		String join();

		/**
		 * Repeats this collection n times.
		 * 
		 * @param n
		 *            Must be greater than 0.
		 * @throws IllegalArgumentException
		 *             If n is less than or equal to 0.
		 */
		Wrapper<T> repeat(int n) throws IllegalArgumentException;

		/**
		 * Randomize order (equivalent to shuffle(new Random()).
		 */
		Wrapper<T> shuffle();

		/**
		 * Randomize order using specified random.
		 * 
		 * @param random
		 *            Used to shuffle the elements.
		 */
		Wrapper<T> shuffle(Random random);

		/**
		 * Sorts the elements assuming they implement Comparable with nulls
		 * last.
		 * 
		 * @throws ClassCastException
		 *             If an element does not implement Comparable.
		 */
		Wrapper<T> sort() throws ClassCastException;

		/** Sorts with given (Comparator). */
		Wrapper<T> sort(Comparator<T> comparator);

		/** Append a wrapper to this one. */
		Wrapper<T> concat(Wrapper<T> wrapper);

		/** Append zero or more items to this wrapper. */
		Wrapper<T> concat(T... items);

		/** Handy shortcut for $().concat($(aCollection)). */
		Wrapper<T> concat(Collection<T> items);

		/** Reverse the order of the wrapper. */
		Wrapper<T> reverse();

		/**
		 * Fill the underlying wrapper with object; can be null.
		 */
		Wrapper<T> fill(T object);

		/**
		 * <li>if j > 0 returns the first j elements. <li>if j < 0 returns the
		 * last j elements. <li>if j == 0 does nothing. <li>if |j| > len does
		 * nothing.
		 */
		Wrapper<T> slice(int j);

		/**
		 * Returns all but the first i elements and the last j elements (in
		 * other words: return all elements between i and j).
		 */
		Wrapper<T> slice(int i, int j);

		/**
		 * Consume the wrapper in order to build a list (by default a
		 * LinkedList).
		 */
		List<T> toList();

		/**
		 * Consume the wrapper in a list. the List implementation MUST have a
		 * public, no-args constructor.
		 */
		@SuppressWarnings("rawtypes")
		List<T> toList(Class<? extends List> concreteListClass);

		/** Consume this wrapper into a Set (by default an HashSet). */
		Set<T> toSet();

		/**
		 * Same as for toSet(), but providing the class the Set implementation
		 * MUST have a public, no-args constructor
		 */
		@SuppressWarnings("rawtypes")
		Set<T> toSet(Class<? extends Set> concreteSetClass);

		/** Consume this wrapper into a newly allocated array. */
		T[] toArray();

		/**
		 * Use when all elements are null, type guessing is useless or use when
		 * you want to be explicit.
		 */
		T[] toArray(Class<T> elementClass);

		/** Prepare this wrapper to be converted to array. */
		ArrayWrapper<T> toArrayWrapper();

		/** Converts the Wrapper to a ListWrapper. */
		ListWrapper<T> toListWrapper();

		/**
		 * Converts the Wrapper to a ComparableArrayWrapper. Only use if the
		 * type implements {@link Comparable}.
		 * 
		 * @return a new ComparableArrayWrapper.
		 * @throws ClassCastException
		 *             if underlying type does not implement Comparable.
		 */
		ComparableArrayWrapper<? extends Comparable<T>> toComparableArrayWrapper()
				throws ClassCastException;

		/**
		 * Filter the elements of this wrapper using the given predicate. Lazy
		 * implementation.
		 */
		Wrapper<T> filter(Predicate<T> predicate);

		/**
		 * Returns the values in list without the elements that the truth test
		 * (iterator) passes. The opposite of filter.
		 */
		Wrapper<T> reject(Predicate<T> predicate);

		/**
		 * Do all the elements satisfy the predicate? Lazy: if it finds an
		 * element failing the predicate, doesn't iterate further.
		 */
		boolean all(Predicate<T> predicate);

		/**
		 * Returns true if any of the values in the list pass the iterator truth
		 * test. Short-circuits and stops traversing the list if a true element
		 * is found.
		 */
		boolean any(Predicate<T> predicate);

		/**
		 * Looks through each value, returning the first one that passes a truth
		 * test. Finds and returns an element satisfying the predicate, or
		 * throws a NoSuchElementException.
		 * 
		 * @throws NoSuchElementException if not found.
		 */
		T find(Predicate<T> predicate) throws NoSuchElementException;

		/**
		 * Returns the index of the first element satisfying the predicate, or
		 * -1 if no such element could be found.
		 */
		int indexOf(Predicate<T> predicate);

		/**
		 * Apply the transforming function to every element of this wrapper
		 * lazily (function not called until necessary).
		 */
		<R> Wrapper<R> map(Function<T, R> function);
		
		/**
		 * Apply the transforming function to every element of this wrapper
		 * lazily and joins the resulting Iterables.
		 */
		<R> Wrapper<R> flatMap(Function<T, Iterable<R>> function);

		/** Calls the function on every element of this wrapper. */
		Wrapper<T> each(Block<T> function);

		/** Reduce boils down a list of values into a single value. */
		<R> R reduce(R initial, BiFunction<T, R, R> function);

		/** Reduce boils down a list of values into a single value. */
		<R, V> R reduce(R initial, TriFunction<T, R, V, R> function, V context);
	}

	/**
	 * Core interface for wrapping Iterables of Comparable objects.
	 * 
	 * @author Adam L. Davis
	 * 
	 * @param <T>
	 *            Type that implements the Comparable interface.
	 */
	public static interface ComparableWrapper<T extends Comparable<T>> extends
			Wrapper<T> {

		/**
		 * Maximum value in this collection.
		 * 
		 * @return Maximum or null if empty.
		 */
		public T max();

		/**
		 * Minimum value in this collection.
		 * 
		 * @return Minimum or null if empty.
		 */
		public T min();
	}

	public static interface CharArrayWrapper extends
			ComparableWrapper<Character> {

		byte[] toByteArray();

		char[] toCharArray();

		int[] toIntArray();

		long[] toLongArray();
	}

	public static interface ByteArrayWrapper extends ComparableWrapper<Byte> {

		byte[] toByteArray();

		short[] toShortArray();

		int[] toIntArray();

		long[] toLongArray();

		char[] toCharArray();

		float[] toFloatArray();

		double[] toDoubleArray();
	}

	public static interface ShortArrayWrapper extends ComparableWrapper<Short> {

		short[] toShortArray();

		int[] toIntArray();

		long[] toLongArray();

		float[] toFloatArray();

		double[] toDoubleArray();

	}

	public static interface IntegerArrayWrapper extends
			ComparableWrapper<Integer> {

		int[] toIntArray();

		long[] toLongArray();

		float[] toFloatArray();

		double[] toDoubleArray();
	}

	public static interface LongArrayWrapper extends ComparableWrapper<Long> {

		long[] toLongArray();

		float[] toFloatArray();

		double[] toDoubleArray();
	}

	public static interface BooleanArrayWrapper extends
			ComparableWrapper<Boolean> {

		boolean[] toBooleanArray();
	}

	public static interface FloatArrayWrapper extends ComparableWrapper<Float> {

		float[] toFloatArray();

		double[] toDoubleArray();
	}

	public static interface DoubleArrayWrapper extends
			ComparableWrapper<Double> {

		double[] toDoubleArray();
	}

	public static interface ArrayWrapper<T> extends Wrapper<T> {

	}

	public static <T> ArrayWrapper<T> $(T[] array) {
		return new org.bitbucket.dollar.ArrayWrapper<T>(array);
	}

	public static <T extends Comparable<T>> ComparableArrayWrapper<T> $(
			T[] array) {
		return new ComparableArrayWrapper<T>(array);
	}

	public static CharArrayWrapper $(Character[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.CharArrayWrapper(
				components);
	}

	public static BooleanArrayWrapper $(Boolean[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.BooleanArrayWrapper(
				components);
	}

	public static ByteArrayWrapper $(Byte[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.ByteArrayWrapper(
				components);
	}

	public static ShortArrayWrapper $(Short[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.ShortArrayWrapper(
				components);
	}

	public static IntegerArrayWrapper $(Integer[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.IntegerArrayWrapper(
				components);
	}

	public static LongArrayWrapper $(Long[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.LongArrayWrapper(
				components);
	}

	public static FloatArrayWrapper $(Float[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.FloatArrayWrapper(
				components);
	}

	public static DoubleArrayWrapper $(Double[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.DoubleArrayWrapper(
				components);
	}

	public static CharArrayWrapper $(char[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.CharArrayWrapper(
				components);
	}

	public static BooleanArrayWrapper $(boolean[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.BooleanArrayWrapper(
				components);
	}

	public static ByteArrayWrapper $(byte[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.ByteArrayWrapper(
				components);
	}

	public static ShortArrayWrapper $(short[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.ShortArrayWrapper(
				components);
	}

	public static IntegerArrayWrapper $(int[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.IntegerArrayWrapper(
				components);
	}

	public static LongArrayWrapper $(long[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.LongArrayWrapper(
				components);
	}

	public static FloatArrayWrapper $(float[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.FloatArrayWrapper(
				components);
	}

	public static DoubleArrayWrapper $(double[] components) {
		return new org.bitbucket.dollar.ArrayWrapper.DoubleArrayWrapper(
				components);
	}

	/**
	 * Wrapper for Lists.
	 * 
	 * @param <T>
	 *            Type contained by the list.
	 */
	public static interface ListWrapper<T> extends Wrapper<T> {

		/**
		 * Makes a copy of the list and wraps it in a new ListWrapper.
		 */
		@Override
		ListWrapper<T> copy();

		/**
		 * Adds the given item to the list.
		 * 
		 * @param item
		 *            Item to add.
		 * @return this ListWrapper.
		 */
		ListWrapper<T> add(T item);

		/**
		 * Adds all given items to the list.
		 * 
		 * @param items
		 *            Items to add.
		 * @return this ListWrapper.
		 */
		ListWrapper<T> addAll(Iterable<T> items);

		/**
		 * Makes the list a synchronized list backed by the wrapped list.
		 */
		ListWrapper<T> threadSafe();

		/**
		 * Makes the list an unmodifiable copy of the wrapped list.
		 */
		ListWrapper<T> immutable();

		/**
		 * Makes the list a type-checked list backed by the wrapped list.
		 * 
		 * @param requiredClass
		 *            Type of element the list is permitted to hold.
		 * @return This list wrapper.
		 */
		ListWrapper<T> checked(Class<T> requiredClass);
	}

	public static <T> ListWrapper<T> $(List<T> list) {
		return new org.bitbucket.dollar.ListWrapper<T>(list);
	}

	/**
	 * Consumes the given enumeration and wraps it.
	 * 
	 * @param enumer
	 *            Enumeration to wrap.
	 * @return A new object with elements of the given enumeration.
	 */
	public static <E> ListWrapper<E> $(Enumeration<E> enumer) {
		return new EnumerationWrapper<E>(enumer);
	}

	/**
	 * Wraps an Iterable of Comparable objects.
	 * 
	 * @param iterable
	 *            Iterable to wrap.
	 * @param ignored
	 *            Required to unambiguously get a ComparableListWrapper.
	 * @return Wrapper with min/max methods.
	 * @deprecated Use {@link #$(Iterable, Class)} instead.
	 */
	@Deprecated
	public static <T extends Comparable<T>> ComparableListWrapper<T> $(
			Iterable<T> iterable, boolean ignored) {
		return new ComparableListWrapper<T>(iterable);
	}

	/**
	 * Wraps an Iterable of Comparable objects.
	 * 
	 * @param iterable
	 *            Iterable to wrap.
	 * @param clazz
	 *            Required to unambiguously get a ComparableListWrapper with
	 *            this type.
	 * @return Wrapper with min/max methods.
	 */
	public static <T extends Comparable<T>> ComparableListWrapper<T> $(
			Iterable<T> iterable, Class<T> clazz) {
		return new ComparableListWrapper<T>(iterable);
	}

	/**
	 * Wrapper with methods specific to {@link Set}.
	 * 
	 * @param <T>
	 *            Type contained by the set.
	 */
	public static interface SetWrapper<T> extends Wrapper<T> {

		/** Creates a copy of the set and wrapper. */
		@Override
		SetWrapper<T> copy();

		/**
		 * Adds an item to the wrapped Set.
		 * 
		 * @param item
		 *            Item to add.
		 * @return this wrapper.
		 */
		SetWrapper<T> add(T item);

		/**
		 * Adds multiple items to the wrapped Set.
		 * 
		 * @param items
		 *            Items to add.
		 * @return this wrapper.
		 */
		SetWrapper<T> addAll(Iterable<T> items);

		/**
		 * Calls {@link Collections#synchronizedSet(Set)} on the wrapped Set.
		 * 
		 * @return this wrapper.
		 */
		SetWrapper<T> threadSafe();

		/**
		 * Calls {@link Collections#unmodifiableSet(Set)} on the wrapped Set.
		 * 
		 * @return this wrapper.
		 */
		SetWrapper<T> immutable();

		/**
		 * Calls {@link Collections#checkedSet(Set, Class)} on the wrapped Set.
		 * 
		 * @param requiredClass
		 *            The Type that elements of the Set must have.
		 * @return this wrapper.
		 */
		SetWrapper<T> checked(Class<T> requiredClass);
	}

	public static <T> SetWrapper<T> $(Set<T> set) {
		return new org.bitbucket.dollar.SetWrapper<T>(set);
	}

	/**
	 * Wrapper for Maps (with iterator of the key-set).
	 * 
	 * @param <K>
	 *            Key's type.
	 * @param <V>
	 *            Value's type.
	 */
	public static interface MapWrapper<K, V> extends Wrapper<K> {

		/**
		 * Adds a key/value pair to the wrapped Map.
		 * 
		 * @return this wrapper.
		 */
		MapWrapper<K, V> add(K key, V value);

		/**
		 * Calls {@link Collections#synchronizedMap(Map)} on the wrapped Map.
		 * 
		 * @return this wrapper.
		 */
		MapWrapper<K, V> threadSafe();

		/**
		 * Calls {@link Collections#unmodifiableMap(Map)} on the wrapped Map.
		 * 
		 * @return this wrapper.
		 */
		MapWrapper<K, V> immutable();

		/**
		 * Calls {@link Collections#checkedMap(Map, Class, Class)} on the
		 * wrapped Map.
		 * 
		 * @param requiredKeyClass
		 *            The Type required of keys.
		 * @param requiredValueClass
		 *            The Type required of values.
		 * @return this wrapper.
		 */
		MapWrapper<K, V> checked(Class<K> requiredKeyClass,
				Class<V> requiredValueClass);

		/**
		 * Gives the wrapped Map.
		 * 
		 * @return the wrapped Map instance.
		 */
		Map<K, V> toMap();
	}

	public static <K, V> MapWrapper<K, V> $(Map<K, V> map) {
		return new org.bitbucket.dollar.MapWrapper<K, V>(map);
	}

	/**
	 * Ranges have a beginning and end (and sometimes a step). Implements
	 * Predicate so you can do things like:
	 * 
	 * <pre>
	 * $(list).filter($(0, 256))
	 * </pre>
	 * 
	 * @author Adam L. Davis
	 * 
	 * @param <T>
	 *            Type contained in this range.
	 */
	public interface RangeWrapper<T> extends Wrapper<T>, Predicate<T> {

		/**
		 * Delegates to predicate's {@link #test(Object)} method.
		 * 
		 * @param it
		 *            Object to test.
		 * @return True if is within the range and step.
		 */
		boolean contains(T it);
	}

	/**
	 * Range of integers, from 0, exclusive of stopAt. Calls
	 * {@link Ranges#upto(int)}.
	 */
	@Deprecated
	public static RangeWrapper<Integer> $(int stopAt) {
		return Ranges.upto(stopAt);
	}

	/**
	 * Range of integers, from startFrom, exclusive of stopAt. Calls
	 * {@link Ranges#range(int, int)}.
	 */
	public static RangeWrapper<Integer> $(int startFrom, int stopAt) {
		return Ranges.range(startFrom, stopAt);
	}

	/**
	 * Range of integers using step. Calls
	 * {@link Ranges#rangeWithStep(int, int, int)}.
	 */
	public static RangeWrapper<Integer> $(int startFrom, int stopAt, int stepBy) {
		return Ranges.rangeWithStep(startFrom, stopAt, stepBy);
	}

	/**
	 * Range of shorts, from startFrom, exclusive of stopAt. Calls
	 * {@link Ranges#range(short, short)}.
	 */
	public static RangeWrapper<Short> $(short startFrom, short stopAt) {
		return Ranges.range(startFrom, stopAt);
	}

	/**
	 * Range of shorts using step. Calls
	 * {@link Ranges#rangeWithStep(short, short, short)}.
	 */
	public static RangeWrapper<Short> $(short startFrom, short stopAt,
			short stepBy) {
		return Ranges.rangeWithStep(startFrom, stopAt, stepBy);
	}

	/**
	 * Range of longs, from startFrom, exclusive of stopAt. Calls
	 * {@link Ranges#range(long, long)}.
	 */
	public static RangeWrapper<Long> $(long startFrom, long stopAt) {
		return Ranges.range(startFrom, stopAt);
	}

	/**
	 * Range of longs using step. Calls
	 * {@link Ranges#rangeWithStep(long, long, long)}.
	 */
	public static RangeWrapper<Long> $(long startFrom, long stopAt, long stepBy) {
		return Ranges.rangeWithStep(startFrom, stopAt, stepBy);
	}

	/**
	 * Range of bytes, from startFrom, exclusive of stopAt. Calls
	 * {@link Ranges#range(byte, byte)}.
	 */
	public static RangeWrapper<Byte> $(byte startFrom, byte stopAt) {
		return Ranges.range(startFrom, stopAt);
	}

	/**
	 * Range of bytes using step. Calls
	 * {@link Ranges#rangeWithStep(byte, byte, byte)}.
	 */
	public static RangeWrapper<Byte> $(byte startFrom, byte stopAt, byte stepBy) {
		return Ranges.rangeWithStep(startFrom, stopAt, stepBy);
	}

	/**
	 * Range of BigDecimals, from startFrom, exclusive of stopAt. Calls
	 * {@link Ranges#range(BigDecimal, BigDecimal)}.
	 */
	public static RangeWrapper<BigDecimal> $(BigDecimal startFrom,
			BigDecimal stopAt) {
		return Ranges.range(startFrom, stopAt);
	}

	/**
	 * Range of BigDecimals using step. Calls
	 * {@link Ranges#rangeWithStep(BigDecimal, BigDecimal, BigDecimal)}.
	 */
	public static RangeWrapper<BigDecimal> $(BigDecimal startFrom,
			BigDecimal stopAt, BigDecimal stepBy) {
		return Ranges.rangeWithStep(startFrom, stopAt, stepBy);
	}

	/**
	 * Range of BigIntegers, from startFrom, exclusive of stopAt. Calls
	 * {@link Ranges#range(BigInteger, BigInteger)}.
	 */
	public static RangeWrapper<BigInteger> $(BigInteger startFrom,
			BigInteger stopAt) {
		return Ranges.range(startFrom, stopAt);
	}

	/**
	 * Range of BigIntegers using step. Calls
	 * {@link Ranges#rangeWithStep(BigInteger, BigInteger, BigInteger)}.
	 */
	public static RangeWrapper<BigInteger> $(BigInteger startFrom,
			BigInteger stopAt, BigInteger stepBy) {
		return Ranges.rangeWithStep(startFrom, stopAt, stepBy);
	}

	/** Range of characters, like A-Z. */
	public static RangeWrapper<Character> $(char from, char to) {
		return Ranges.range(from, to);
	}

	/** String, StringBuilder, etc as an Iterable. */
	public static Wrapper<Character> $(CharSequence charSequence) {
		return new CharSequenceWrapper(charSequence);
	}

	/** Date Range with default of step=1 and field=day. */
	public static RangeWrapper<Date> $(Date begin, Date end) {
		return Ranges.range(begin, end);
	}

	/** Date Range with default of field=day. */
	public static RangeWrapper<Date> $(Date begin, Date end, int step) {
		return Ranges.rangeWithStep(begin, end, step);
	}

	/**
	 * Please use
	 * {@link Ranges#rangeWithStepAndField(Date, Date, int, DateField)} or
	 * {@link #$(Date, Date, int, DateField)}.
	 */
	@Deprecated
	public static RangeWrapper<Date> $(Date begin, Date end, int step, int field) {
		return Ranges.rangeWithStepAndField(begin, end, step, field);
	}

	/** Calls {@link Ranges#rangeWithStepAndField(Date, Date, int, DateField)}. */
	public static RangeWrapper<Date> $(Date begin, Date end, int step,
			DateField field) {
		return Ranges.rangeWithStepAndField(begin, end, step, field);
	}

	/** make an iterator iterable. */
	public static <T> Iterable<T> $(final Iterator<T> anIterator) {
		return new Iterable<T>() {

			@Override
			public Iterator<T> iterator() {
				return anIterator;
			}
		};
	}

	/** special case: NullWrapper. */
	public static <T> Wrapper<T> $() {
		return new NullWrapper<T>();
	}

	/** Random data generators. */
	public static interface RandomWrapper<T> extends Wrapper<T> {

		/**
		 * Sets the number of samples.
		 * 
		 * @param samples
		 *            Number of samples.
		 * @return this wrapper.
		 */
		RandomWrapper<T> samples(int samples);

		/**
		 * Sets the generator.
		 * 
		 * @param random
		 *            Instance of Random object.
		 * @return this wrapper.
		 */
		RandomWrapper<T> generator(Random random);
	}

	/**
	 * For creating a random iterable of size 1.
	 * 
	 * @param random
	 *            Instance of Random.
	 * @param upTo
	 *            Maximum float value exclusive.
	 * @return An iterable of size 1 that returns a random float.
	 */
	public static RandomWrapper<Float> $(Random random, float upTo) {
		return new RandomFloatWrapper(random, 1, upTo);
	}

	public static RandomWrapper<Double> $(Random random, double upTo) {
		return new RandomDoubleWrapper(random, 1, upTo);
	}

	public static RandomWrapper<Integer> $(Random random, int upTo) {
		return new RandomIntegerWrapper(random, 1, upTo);
	}

	public static RandomWrapper<Long> $(Random random, long upTo) {
		return new RandomLongWrapper(random, 1, upTo);
	}

	public static RandomWrapper<Boolean> $(Random random, boolean b) {
		return new RandomBooleanWrapper(random, 1);
	}

	/**
	 * For creating a random iterable of given size.
	 * 
	 * @param random
	 *            Instance of Random.
	 * @param size
	 *            Will not iterate beyond this many values.
	 * @param upTo
	 *            Maximum float value exclusive.
	 * @return An iterable of given size limit that returns random floats.
	 */
	public static RandomWrapper<Float> $(Random random, int size, float upTo) {
		return new RandomFloatWrapper(random, size, upTo);
	}

	public static RandomWrapper<Double> $(Random random, int size, double upTo) {
		return new RandomDoubleWrapper(random, size, upTo);
	}

	public static RandomWrapper<Integer> $(Random random, int size, int upTo) {
		return new RandomIntegerWrapper(random, size, upTo);
	}

	public static RandomWrapper<Long> $(Random random, int size, long upTo) {
		return new RandomLongWrapper(random, size, upTo);
	}

	public static RandomWrapper<Boolean> $(Random random, int size, boolean b) {
		return new RandomBooleanWrapper(random, size);
	}

	/**
	 * Returns a predicate that evaluates to true if each of its components
	 * evaluates to true.
	 */
	public static <T> Predicate<T> and(final Predicate<T>... predicates) {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				for (Predicate<T> pred : predicates) {
					if (!pred.test(object))
						return false;
				}
				return true;
			}
		};
	}

	/**
	 * Returns a predicate that evaluates to true if any one of its components
	 * evaluates to true.
	 */
	public static <T> Predicate<T> or(final Predicate<T>... predicates) {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				for (Predicate<T> pred : predicates) {
					if (pred.test(object))
						return true;
				}
				return false;
			}
		};
	}

	/**
	 * Returns a predicate that evaluates to true if the given predicate
	 * evaluates to false.
	 */
	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				return !predicate.test(object);
			}
		};
	}

	public static <T> Predicate<T> alwaysFalse() {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				return false;
			}
		};
	}

	public static <T> Predicate<T> alwaysTrue() {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				return true;
			}
		};
	}

	/**
	 * Returns a predicate that evaluates to true if the given object is not
	 * null.
	 */
	public static <T> Predicate<T> notNull() {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				return object != null;
			}
		};
	}

	/**
	 * Returns a predicate that evaluates to true if the given object is null.
	 */
	public static <T> Predicate<T> isNull() {
		return new Predicate<T>() {
			@Override
			public boolean test(T object) {
				return null == object;
			}
		};
	}

}
