package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FlowSource;

import java.util.Iterator;

/**
 * A {@link FlowSource} implementation that acts like an {@code iterator} of the {@code flow}.
 *
 * @param <T> the type of elements in the source {@link FlowSource}.
 * @author Azizbek Toshpulatov.
 */
public class IteratorFlowSource<T> implements FlowSource<T> {

    private final Iterator<T> iterator;
    private T current;

    /**
     * Constructs a {@link FlowSource} instance with the given iterator.
     *
     * @param iterator the iterator.
     */
    public IteratorFlowSource(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean advance() {
        if (iterator.hasNext()) {
            current = iterator.next();
            return true;
        }
        return false;
    }

    @Override
    public T get() {
        return current;
    }
}
