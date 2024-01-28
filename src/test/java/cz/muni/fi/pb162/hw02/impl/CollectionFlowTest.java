package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;

import java.util.List;

public class CollectionFlowTest extends AbstractFlowTest{
    @Override
    public Flow<Integer> createFlow() {
        return Flows.of(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }
}
