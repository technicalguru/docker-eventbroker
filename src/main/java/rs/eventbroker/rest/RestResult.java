/**
 * 
 */
package rs.eventbroker.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class that must be used for returning results from REST services.
 * @author ralph
 *
 */
@XmlRootElement
public class RestResult<T> implements Serializable {

	/** Default message when payload is {@code null}. */
	public static final String NOT_FOUND_ERROR = "Object not found";
	/** Default message when an object already exists. */
	public static final String ALREADY_EXISTS_ERROR = "Object already exists";
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean success;
	private String errorMessage;
	private T data;
	
	/**
	 * Constructor.
	 */
	public RestResult() {		
	}

	/**
	 * Constructor.
	 * @param success
	 * @param errorMessage
	 * @param data
	 */
	public RestResult(boolean success, String errorMessage, T data) {
		setSuccess(success);
		setErrorMessage(errorMessage);
		setData(data);
	}

	/**
	 * Constructor.
	 * <p>Creates a {@link #NOT_FOUND_ERROR} when data is {@code null}.</p> 
	 */
	public RestResult(T data) {
		this(data != null, data != null ? null : NOT_FOUND_ERROR, data);
	}

	/**
	 * Constructor.
	 */
	public RestResult(String errorMessage) {
		this(false, errorMessage, null);
	}

	/**
	 * Returns the success.
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Returns the errorMessage.
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the errorMessage.
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Returns the data.
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Creates the correct result object (success).
	 * @param data data to be transmitted
	 * @return the rest result object
	 */
	public static <X extends Serializable> RestResult<X> from(X data) {
		return new RestResult<X>(data);
	}

	/**
	 * Creates the correct rest object (error).
	 * @param error error to be transmitted
	 * @return the rest result object
	 */
	public static <X extends Throwable> RestResult<X> error(X error) {
		return new RestResult<X>(false, error.getMessage(), error);
	}

	/**
	 * Creates the correct rest object (error).
	 * @param error error to be transmitted
	 * @return the rest result object
	 */
	public static <X extends Throwable> RestResult<X> error(String message, X error) {
		return new RestResult<X>(false, message, error);
	}

	/**
	 * Creates the correct rest object (error).
	 * @param error error to be transmitted
	 * @return the rest result object
	 */
	public static RestResult<? extends Serializable> error(String message) {
		return new RestResult<Long>(false, message, null);
	}
}
