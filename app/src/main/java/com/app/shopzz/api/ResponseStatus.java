package com.app.shopzz.api;

/**
 * This class handle response status and message
 * <pre>{@code
 * status = 1  // response status success
 * status = 0  // response status fail
 * }
 * </pre>
 */
public class ResponseStatus {

    public static int STATUS_FAIL = 0;
    public static int STATUS_SUCCESS = 1;

    private int status;
    private String message;

    /**
     * @return (String) message : to get status message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message (String)  : to set status message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return (String) status  : to get status success or fail
     * <pre>{@code
     * status = 1  // response status success
     * status = 0  // response status fail
     * }
     * </pre>
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status (String) : to set status success or fail
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * give status of API call
     *
     * @return (boolean) : return Either true or false on API response
     */
    public boolean isSuccess() {
        return status == (STATUS_SUCCESS);
    }

    /**
     * give status of error API call
     *
     * @return (boolean) : return either true or false on API response
     */
    public boolean isFail() {
        return status == (STATUS_FAIL);
    }
}