package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FlowSource;

/**
 * Basic decorator of {@link FlowSource}, that has basic advance and get methods.
 *
 * @param <T> the type of elements in the source {@link FlowSource}.
 * @author Azizbek Toshpulatov
 */
public abstract class FlowSourceDecorator<T> implements FlowSource<T> {
    private final FlowSource<T> source;

    /**
     * Basic constructor that sets the source.
     * @param source source of elements.
     */
    public FlowSourceDecorator(FlowSource<T> source) {
        this.source = source;
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
