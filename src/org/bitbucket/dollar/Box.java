package org.bitbucket.dollar;

public class Box<T> {

    private final T value;
    private final boolean empty;

    public static <T> Box<T> of(T value) {
        return new Box<T>(value, false);
    }

    public static <T> Box<T> empty() {
        return new Box<T>(null, true);
    }

    private Box(T value, boolean empty) {
        this.value = value;
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Box<T> put(T newValue) {
        return new Box<T>(newValue, false);
    }
    
    public T get() {
        if (empty) {
            throw new IllegalArgumentException("box is empty");
        }
        return value;
    }
}
