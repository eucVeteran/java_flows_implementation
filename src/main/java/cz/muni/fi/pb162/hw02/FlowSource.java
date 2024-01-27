package cz.muni.fi.pb162.hw02;

/**
 * An interface representing a source of data for {@link Flow}.
 * <p>
 * The behavior of the declared methods is undefined when called in contrast with their described contracts.
 * Such method calls may result in arbitrary behaviour, including the author of this assignment
 * coming over to your place and performing a very thorough code review of your implementation.
 * </p>
 *
 * @param <T> type of elements produced by this {@link FlowSource}
 */
public interface FlowSource<T> {

    /**
     * Advance to next item if available.
     * Once this method returns {@code false} it should not be called again.
     * The behaviour of any such consecutive calls is undefined!
     *
     * @return true if next item is available
     */
    boolean advance();

    /**
     * Get the current item. Calling this method is only safe when
     * call to {@link FlowSource#advance()} returned with {@code true}.
     * Otherwise, the method behaviour is undefined!
     *
     * @return current item if previous call of {@link FlowSource#advance()} succeeded
     */
    T get();

}
