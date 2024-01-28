package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class TerminalSupplierWithNullFlowTest extends AbstractFlowTest{

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Supplier<Integer> supplier = () -> {
        var x = counter.getAndIncrement();
        return x < 10 ? x : null;
    };

    @Override
    public Flow<Integer> createFlow() {
        return Flows.of(supplier, null);
    }
}
