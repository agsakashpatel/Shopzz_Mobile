package com.app.shopzz.api;


public interface RequestListener {
    /**
     * Called when a request completes with the given response. Executed by a
     * background thread: do not update the UI in this method.
     */
    void onComplete(RequestCode requestCode, Object object, String message);

    /**
     * Called when a request has a network or request error. Executed by a
     * background thread: do not update the UI in this method.
     */
    void onException(int statusCode, String error, RequestCode requestCode);

    /**
     * This method called when a request needs to recall
     *
     * @param requestCode (RequestCode) : To identify the request type
     */
    void onRetryRequest(RequestCode requestCode);
}