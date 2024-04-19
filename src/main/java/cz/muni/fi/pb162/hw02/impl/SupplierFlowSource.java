package cz.muni.fi.pb162.hw02.impl;

import java.util.function.Supplier;

import cz.muni.fi.pb162.hw02.FlowSource;

/**
 * A {@link FlowSource} implementation that acts like {@code supplier} of the {@code flow}.
 *
 * @param <T> the type of elements in the source {@link FlowSource}.
 * @author Azizbek Toshpulatov.
 */
public class SupplierFlowSource<T> implements FlowSource<T> {

    private final Supplier<T> supplier;
    private T current;
    private boolean hasNext;
    private T terminalValue = null;

    /**
     * Constructs a {@link FlowSource} instance with the given supplier.
     *
     * @param supplier the supplier.
     */
    public SupplierFlowSource(Supplier<T> supplier) {
        this.supplier = supplier;
        this.hasNext = true;
    }

    /**
     * Constructs a {@link FlowSource} instance with the given iterator and a terminal value.
     *
     * @param supplier      the supplier.
     * @param terminalValue the terminal value.
     */
    public SupplierFlowSource(Supplier<T> supplier, T terminalValue) {
        this(supplier);
        this.terminalValue = terminalValue;
    }

    @Override
    public boolean advance() {
        if (hasNext) {
            current = supplier.get();
            if (current == terminalValue) {
                hasNext = false;
                return false;
            }
            hasNext = current != null;
            return hasNext;
        }
        return false;
    }

    @Override
    public T get() {
        return current;
    }

    public T getTerminalValue() {
        return terminalValue;
    }
}