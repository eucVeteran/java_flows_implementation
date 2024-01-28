package cz.muni.fi.pb162.hw02.impl;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

/**
 * These tests run rather longer than the rest
 */
@EnabledIfSystemProperty(named = "runMemoryTests", matches = "true")
public class InfiniteSupplierFlowMemoryTest extends AbstractInfiniteSupplierFlowTest {

    public static final long BILLION = 1_000_000_000;

    @Override
    public long number() {
        return BILLION;
    }
}
