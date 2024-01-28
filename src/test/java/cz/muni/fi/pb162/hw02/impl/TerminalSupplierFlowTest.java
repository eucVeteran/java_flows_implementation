package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class TerminalSupplierFlowTest extends AbstractFlowTest{

    @Override
    public Flow<Integer> createFlow() {
        var counter = new AtomicInteger(0);
        return Flows.of(counter::getAndIncrement, 10);
    }
}
