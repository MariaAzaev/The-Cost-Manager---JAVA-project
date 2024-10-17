// Created By: Maria Azaev (315082529) & Sivan Cohen (209111590)

package il.ac.shenkar.model;


/**
 * Custom exception class for the model package.
 * This exception should be used for handling exceptions specific to the model component.
 */
public class ModelException extends Exception {

    /**
     * Constructs a new ModelException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public ModelException(String message) {
        super(message);
    }

    /**
     * Constructs a new ModelException with the specified detail message and cause.
     *
     * @param message The detail message describing the exception.
     * @param cause   The cause of the exception.
     */
    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }
}


