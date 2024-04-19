package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FlowSource;

/**
 * A {@link FlowSource} implementation that limits the count of elements produced by the {@code flow}.
 *
 * @param <T> the type of elements in the source {@link FlowSource}.
 * @author Azizbek Toshpulatov.
 */
public class LimitFlowSource<T> implements FlowSource<T> {
    private final FlowSource<T> source;
    private long count;

    /**
     * Constructs a {@link FlowSource} instance with the provided source and the size of limit.
     *
     * @param source the source FlowSource whose elements are to be transformed.
     * @param count  the size of limit.
     */
    public LimitFlowSource(FlowSource<T> source, long count) {
        this.source = source;
        this.count = count;
    }

    @Override
    public boolean advance() {
        if (count == 0) {
            return false;
        }
        if (source.advance()) {
            count--;
            return true;
        }
        return false;
    }

    @Override
    public T get() {
        return source.get();
    }
}
