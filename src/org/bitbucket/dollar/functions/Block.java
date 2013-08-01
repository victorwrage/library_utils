package org.bitbucket.dollar.functions;


/**
 * An operation upon an input object. The operation may modify that object or
 * external state (other objects).
 */
public interface Block<T> {

    void accept(T t);
}
