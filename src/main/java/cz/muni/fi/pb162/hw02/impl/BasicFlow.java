package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;
import cz.muni.fi.pb162.hw02.FlowSource;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * An implementation of the {@link Flow} interface that provides various flow operations
 * such as filtering, mapping, limiting, skipping, and iterating over elements.
 *
 * @param <T> the type of elements in the flow
 * @author Azizbek Toshpulatov
 */
public class BasicFlow<T> implements Flow<T> {
    private final FlowSource<T> source;

    /**
     * Constructs a {@link Flow} instance with the provided source.
     *
     * @param source the source of elements for the flow.
     */
    public BasicFlow(FlowSource<T> source) {
        this.source = source;
    }

    @Override
    public Flow<T> filter(Predicate<T> filter) {
        return new BasicFlow<>(new FilterFlowSource<>(source, filter));
    }

    @Override
    public Flow<T> limit(long count) {
        return new BasicFlow<>(new LimitFlowSource<>(source, count));
    }

    @Override
    public Flow<T> skip(long count) {
        return new BasicFlow<>(new SkipFlowSource<>(source, count));
    }

    @Override
    public <R> Flow<R> map(Function<T, R> transformation) {
        return new BasicFlow<>(new MapFlowSource<>(source, transformation));
    }

    @Override
    public void forEach(Consumer<T> operation) {
        while (source.advance()) {
            T current = source.get();
            operation.accept(current);
        }
    }

    @Override
    public T first() {
        source.advance();
        return source.get();
    }
}
