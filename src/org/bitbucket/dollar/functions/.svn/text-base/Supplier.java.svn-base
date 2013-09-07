package org.bitbucket.dollar.functions;

/**
 * Extremely simple interface that just has one method, get(), which supplies
 * objects of a single type. Semantically, this could be a factory, generator,
 * builder, closure, or something else entirely.
 * 
 * @author Adam L. Davis
 * 
 * @param <T>
 *            The type of object being supplied.
 */
@Functional
public interface Supplier<T> {

	/**
	 * Retrieves an instance of the appropriate type. The returned object may or
	 * may not be a new instance, depending on the implementation.
	 * 
	 * @return an instance of the appropriate type.
	 */
	public T get();

}