package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FlowSource;

/**
 * A {@link FlowSource} implementation that skips first {@code count} elements in a source {@link FlowSource}.
 *
 * @param <T> the type of elements in the source {@link FlowSource}.
 * @author Azizbek Toshpulatov.
 */
public class SkipFlowSource<T> implements FlowSource<T> {
    private final FlowSource<T> source;

    /**
     * Constructs a {@link FlowSource} instance with the first {@code count} elements skipped.
     *
     * @param source the source FlowSource whose elements are to be transformed.
     * @param count  the count of elements to be skipped.
     */
    public SkipFlowSource(FlowSource<T> source, long count) {
        this.source = source;
        while (count != 0 && source.advance()) {
            count--;
        }
    }

    @Override
    public boolean advance() {
        return source.advance();
    }

    @Override
    public T get() {
        return source.get();
    }
}
