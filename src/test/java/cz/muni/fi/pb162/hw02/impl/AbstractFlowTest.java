package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.Flow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractFlowTest {

    protected Flow<Integer> flow;

    public abstract Flow<Integer> createFlow();

    @BeforeEach
    public void setup() {
        flow = createFlow();
    }

    @Test
    public void shouldGetFirstElement() {
        assertThat(flow.first()).isEqualTo(0);
    }

    @Test
    public void shouldExecuteForEach() {
        var actual = new ArrayList<Integer>();
        flow.forEach(actual::add);
        assertThat(actual).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    public void shouldFilterEvenElements() {
        var actual = new ArrayList<Integer>();
        flow
                .filter(x -> x % 2 == 0)
                .forEach(actual::add);
        assertThat(actual).containsExactly(0, 2, 4, 6, 8);
    }

    @Test
    public void shouldLimitElements() {
        var actual = new ArrayList<Integer>();
        flow
                .limit(5)
                .forEach(actual::add);
        assertThat(actual).containsExactly(0, 1, 2, 3, 4);
    }

    @Test
    public void shouldLimitElementsToHigherNumber() {
        var actual = new ArrayList<Integer>();
        flow
                .limit(12)
                .forEach(actual::add);
        assertThat(actual).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    public void shouldLimitElementsMultipleTimes() {
        var actual = new ArrayList<Integer>();
        flow
                .limit(6)
                .limit(5)
                .forEach(actual::add);
        assertThat(actual).containsExactly(0, 1, 2, 3, 4);
    }

    @Test
    public void shouldLimitElementsMultipleTimesWithSecondLimitHigher() {
        var actual = new ArrayList<Integer>();
        flow
                .limit(5)
                .limit(6)
                .forEach(actual::add);
        assertThat(actual).containsExactly(0, 1, 2, 3, 4);
    }

    @Test
    public void shouldSkipElements() {
        var actual = new ArrayList<Integer>();
        flow
                .skip(5)
                .forEach(actual::add);
        assertThat(actual).containsExactly( 5, 6, 7, 8, 9);
    }

    @Test
    public void shouldSkipElementsMultipleTimes() {
        var actual = new ArrayList<Integer>();
        flow
                .skip(2)
                .skip(3)
                .forEach(actual::add);
        assertThat(actual).containsExactly( 5, 6, 7, 8, 9);
    }

    @Test
    public void shouldSkipAllElements() {
        var actual = new ArrayList<Integer>();
        flow
                .skip(10)
                .forEach(actual::add);
        assertThat(actual).isEmpty();
    }

    @Test
    public void shouldSkipAndThenLimitElements() {
        var actual = new ArrayList<Integer>();
        flow
                .skip(5)
                .limit(2)
                .forEach(actual::add);
        assertThat(actual).containsExactly(5, 6);
    }

    @Test
    public void shouldLimitAndThenSkipElements() {
        var actual = new ArrayList<Integer>();
        flow
                .limit(5)
                .skip(2)
                .forEach(actual::add);
        assertThat(actual).containsExactly(2, 3, 4);
    }

    @Test
    public void shouldTransformElements() {
        var actual = new ArrayList<String>();
        flow
                .map(x -> x + "a")
                .forEach(actual::add);
        assertThat(actual).containsExactly("0a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a");
    }
}
