package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.FlowSource;

import java.util.function.Predicate;

/**
 * A {@link FlowSource} implementation that filters the flow with given {@code filter}.
 *
 * @param <T> the type of elements in the source {@link FlowSource}.
 * @author Azizbek Toshpulatov.
 */
public class FilterFlowSource<T> extends FlowSourceDecorator<T> implements FlowSource<T> {
    private final Predicate<T> filter;

    /**
     * Constructs a {@link FlowSource} instance with the provided source and a filter.
     *
     * @param source the source FlowSource whose elements are to be transformed.
     * @param filter the filter.
     */
    public FilterFlowSource(FlowSource<T> source, Predicate<T> filter) {
        super(source);
        this.filter = filter;
    }

    @Override
    public boolean advance() {
        if (super.advance()) {
            if (!filter.test(super.get())) {
                return advance();
            }
            return true;
        }
        return false;
    }
}
