package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;
import cz.muni.fi.pb162.hw02.FlowSource;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;


/**
 * Factory class for creation of {@link Flow}s
 *
 * <p>
 *  This is probably the first type you see a generic method signature.
 *  See the information in the {@code README.md}.
 * </p>
 */
public class Flows {

    /**
     * Crates new {@link Flow} backed by given {@link Collection}
     *
     * @param collection the collection used as source of elements
     * @return flow of elements from given collection
     * @param <T> element type parameter
     */
    public static <T> Flow<T> of(Collection<T> collection) {
        return new BasicFlow<>(new IteratorFlowSource<>(collection.iterator()));
    }

    /**
     * Crates new {@link Flow} backed by given {@link Iterable}
     *
     * @param iterable the iterable used as source of elements
     * @return flow of elements from given iterable
     * @param <T> element type parameter
     */
    public static <T> Flow<T> of(Iterable<T> iterable) {
        return new BasicFlow<>(new IteratorFlowSource<>(iterable.iterator()));
    }

    /**
     * Crates new {@link FlowSource} backed by {@link Iterator}
     *
     * @param iterator the iterator used as source of elements
     * @return flow of elements from given iterator
     * @param <T> element type parameter
     */
    public static <T> Flow<T> of(Iterator<T> iterator) {
        return new BasicFlow<>(new IteratorFlowSource<>(iterator));
    }

    /**
     * Crates new {@link FlowSource} backed by given {@link java.util.function.Supplier}
     *
     * @param supplier the supplier used as source of elements
     * @return flow of elements from given supplier
     * @param <T> element type parameter
     */
    public static <T> Flow<T> of(Supplier<T> supplier) {
        return new BasicFlow<>(new SupplierFlowSource<>(supplier));
    }

    /**
     * Crates new {@link FlowSource} backed by given {@link java.util.function.Supplier}
     *
     * @param supplier the supplier used as source of elements
     * @param terminalValue the terminal value (signals that no more valid elements are available)
     * @return flow of elements from given supplier
     * @param <T> element type parameter
     */
    public static <T> Flow<T> of(Supplier<T> supplier,T terminalValue) {
        return new BasicFlow<>(new SupplierFlowSource<>(supplier, terminalValue));
    }

    /**
     * Crates new {@link Flow}
     *
     * @param source source providing elements for the flow
     * @return flow of elements from given supplier
     * @param <T> element type parameter
     */
    public static <T> Flow<T> of(FlowSource<T> source) {
        return new BasicFlow<>(source);
    }
}
