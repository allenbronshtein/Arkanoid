/**
 * The type Selection.
 *
 * @param <T> the type parameter
 */
//Allen Bronshtein
//206228751
public class Selection<T> {
    private String message;
    private T data;

    /**
     * Instantiates a new Selection.
     *
     * @param message the message
     * @param data    the data
     */
    public Selection(String message, T data) {
        this.message = message;
        this.data = data;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get data t.
     *
     * @return the t
     */
    public T getData() {
        return data;
    }
}
