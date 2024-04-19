package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FlowSource;

import java.util.function.Function;

/**
 * A {@link FlowSource} implementation that maps elements from a source {@link FlowSource}
 * to elements of a different type using a provided transformation function.
 *
 * @param <T> the type of elements in the source {@link FlowSource}
 * @param <R> the type of elements produced after transformation
 * @author Azizbek Toshpulatov
 */
public class MapFlowSource<T, R> implements FlowSource<R> {
    private final FlowSource<T> source;
    private final Function<T, R> function;

    /**
     * Constructs a {@link FlowSource} instance with the provided source and transformation function.
     *
     * @param source         the source FlowSource whose elements are to be transformed.
     * @param transformation the transformation function to apply to elements from the source.
     */
    public MapFlowSource(FlowSource<T> source, Function<T, R> transformation) {
        this.source = source;
        this.function = transformation;
    }

    @Override
    public boolean advance() {
        return source.advance();
    }

    @Override
    public R get() {
        return function.apply(source.get());
    }
}
