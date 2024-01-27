package cz.muni.fi.pb162.hw02;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A lazily evaluated flow of elements. Implementations must adhere to the following principles
 *
 * <ul>
 *     <li>Don't internally store elements (as there might be infinite number of them)</li>
 *     <li>Each element is visited exactly once</li>
 *     <li>All intermediate operation are applied only when terminal operation is called (</li>
 *     <li>A single terminal operation must be called in order to evaluate everything</li>
 *     <li>Once terminal operation is called the stream is considered exhausted</li>
 * </ul>
 *
 * @param <T> type of elements produced by this flow
 */
public interface Flow<T> {

    /**
     * Filters elements of the flow based on given filter function
     * <br>
     * <strong>type: intermediate</strong>
     *
     * @param filter the filter
     * @return a flow with filters registered
     */
    Flow<T> filter(Predicate<T> filter);


    /**
     * Limits the number of elements produced by the flow
     * <br>
     * <strong>type: intermediate</strong>
     *
     * @param count the maximum number of elements this flow should produce
     * @return a flow with limited number of elements
     */
    Flow<T> limit(long count);

    /**
     * Skips the first given number of elements produced by the flow
     * <br>
     * <strong>type: intermediate</strong>
     *
     * @param count the number of elements this flow should skip
     * @return a flow which skips given number of elements
     */
    Flow<T> skip(long count);

    /**
     * Transforms elements of the flow based on given transformation function
     * <br>
     * <strong>type: intermediate</strong>
     *
     * @param transformation the transformation function
     * @return a flow with transformations registered
     * @param <R> type of elements produced by the resulting flow
     */
    <R> Flow<R> map(Function<T, R> transformation);


    /**
     * Performs given operation on each element of the flow
     * <br>
     * <strong>type: terminal</strong>
     *
     * @param operation the operation to perform
     */
    void forEach(Consumer<T> operation);

    /**
     * Returns the first element
     * <br>
     * <strong>type: terminal</strong>
     *
     * @return  first element of the flow or null if the has no elements
     */
    T first();
}
