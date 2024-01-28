package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;

import java.util.List;

public class IteratorFlowTest extends AbstractFlowTest{
    @Override
    public Flow<Integer> createFlow() {
        var list = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        return Flows.of(list.iterator());
    }
}
