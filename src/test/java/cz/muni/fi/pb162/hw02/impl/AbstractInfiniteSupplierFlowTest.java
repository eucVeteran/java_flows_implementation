package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractInfiniteSupplierFlowTest {

    protected Flow<Long> flow;

    public abstract long number();

    @BeforeEach
    public void setup() {
        var counter = new AtomicLong();
        flow = Flows.of(counter::getAndIncrement);
    }

    @Test
    public void shouldSkipAndGetFirstElement() {
        var actual = flow
                .skip(number())
                .first();

        assertThat(actual).isEqualTo(number());
    }

    @Test
    public void shouldDoThingsAndGetFourElements() {
        var actual = new ArrayList<String>();
        flow
                .skip(number())
                .skip(number())
                .filter(x -> x % 2 == 0)
                .skip(number())
                .map(x -> "A" + x)
                .limit(4)
                .forEach(actual::add);

        var expected = List.of(
                asString("A", 4L * number()),
                asString("A", 4L * number() + 2),
                asString("A", 4L * number() + 4),
                asString("A", 4L * number() + 6)
        );
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    private String asString(String prefix, long value) {
        return prefix + value;
    }
}
