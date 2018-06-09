package com.app.shopzz.api;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.shopzz.BaseApplication;
import com.app.shopzz.customView.CustomDialog;
import com.app.shopzz.helper.ConnectivityHelper;
import com.app.shopzz.utility.Debug;
import com.app.shopzz.utility.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AGS on 01-06-2018.
 */

public class RestClient {
    public static Gson gson;
    private static RestClient instance;

    static {
        gson = new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting().create();
    }

    private RestClient() {
    }

    public static RestClient getInstance() {
        if (null == instance) {
            instance = new RestClient();
        }
        return instance;
    }

    public void post(Activity mContext, int requestType, String url, JSONObject params, final RequestListener responseHandler, final RequestCode requestCode, final boolean isDialogRequired, final boolean isFinalRequest) {
        if (ConnectivityHelper.isConnectingToInternet()) {
            if (isDialogRequired) {
                if (!CustomDialog.getInstance().isDialogShowing()) {
                    CustomDialog.getInstance().show(mContext);
                }
            }

            url = getAbsoluteUrl(url);

            if (params == null) {
                Debug.trace("TAG: " + url + " No requestParams");
            } else {
                Debug.trace("TAG: " + url + " " + params.toString());
            }

            JsonObjectRequest request = new JsonObjectRequest(requestType, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Debug.trace("Response:" + response.toString());

                    verifyResponse(response.toString(), requestCode, new IListener() {
                        @Override
                        public void onError(String errorMessage, int status) {
                            responseHandler.onException(status, errorMessage, requestCode);
                            if (isDialogRequired && isFinalRequest) {
                                if (CustomDialog.getInstance().isDialogShowing()) {
                                    CustomDialog.getInstance().hide();
                                }
                            }
                        }

                        @Override
                        public void onProcessCompleted(String message, Object object) throws IOException, ClassNotFoundException {
                            if (responseHandler != null) {
                                responseHandler.onComplete(requestCode, object, message);
                            }

                            if (CustomDialog.getInstance().isDialogShowing()) {
                                if (isFinalRequest) {
                                    CustomDialog.getInstance().hide();
                                }
                            }
                        }
                    });
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    if (isDialogRequired && isFinalRequest) {
                        if (CustomDialog.getInstance().isDialogShowing()) {
                            CustomDialog.getInstance().hide();
                        }
                    }
                    responseHandler.onException(ResponseStatus.STATUS_FAIL, error.getMessage(), requestCode);
                }
            }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();

                    String authorization = Utils.getInstance().getMD5EncryptedString();
                    Debug.trace("Authorization:" + authorization);
                    headers.put(ApiList.KEY_CONTENT, ApiList.KEY_CONTENT_TYPE);
                    headers.put(ApiList.KEY_AUTHENTICATE, authorization);
                    return headers;
                }
            };

            BaseApplication.getInstance().setRequestTimeout(request);
            BaseApplication.getInstance().addToRequestQueue(requestCode.name(), request);
        }
    }

    private String getAbsoluteUrl(final String relativeUrl) {
        return ServerConfig.SERVER_URL + relativeUrl;
    }

    private void verifyResponse(String response, RequestCode requestCode, IListener listener) {
        if (listener != null) {
            ResponseStatus responseStatus;
            try {
                JSONObject jResult = new JSONObject(response);
                if (jResult.has("d")) {
                    String strResult = jResult.getString("d");
                    jResult = new JSONObject(strResult);
                }
                responseStatus = gson.fromJson(new JSONObject(jResult.toString()).toString(), ResponseStatus.class);

                if (responseStatus.isFail()) {
                    processError(responseStatus, listener);
                } else {
                    if (responseStatus.isSuccess()) {
                        processSuccess(jResult.toString(), responseStatus, requestCode, listener);
                    }
                }
            } catch (JsonSyntaxException | JSONException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * interface for handling the
     * other errors, hiding process dialog
     * and process further the response
     **/
    private interface IListener {
        /**
         * <pre>{@code
         * status = 1  // response status success
         * status = 0  // response status fail
         * }
         * </pre>
         *
         * @param errorMessage (String) : appropriate message relevant to errors
         * @param status       (String) : status of response
         */
        void onError(String errorMessage, int status);

        /**
         * @param object (Object) : succeed response in object
         */
        void onProcessCompleted(String message, Object object) throws IOException, ClassNotFoundException;
    }

    /**
     * This method process the succeed response
     *
     * @param response       (String)              : succeed response
     * @param responseStatus (ResponseStatus instance) : response status e.g success
     * @param listener       (IListener instance) : handle progress dialog visibility, response completion and
     *                       other errors e.g slow internet connection, request timeout
     */
    private static <T> void processSuccess(final String response, final ResponseStatus responseStatus, final RequestCode requestCode, final IListener listener) throws IOException, ClassNotFoundException {

        if (listener != null) {
            Object object = null;
            if ((requestCode.getLocalClass() != null) && requestCode.getLocalClass().getSimpleName().equalsIgnoreCase("ResponseStatus")) {
                object = responseStatus;
            } else {
                try {
                    object = ResponseManager.parse(requestCode, response, gson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (object instanceof ResponseStatus) {
                listener.onError(responseStatus.getMessage(), responseStatus.getStatus());
            } else {
                listener.onProcessCompleted(responseStatus.getMessage(), object);
            }
        }
    }

    /**
     * This method handle errors e.g slow internet connection, request timeout
     *
     * @param responseStatus (ResponseStatus instance) : it process the response error
     * @param listener       (IListener instance) : handle progress dialog visibility, response completion and
     *                       other errors e.g slow internet connection, request timeout
     */
    private static void processError(final ResponseStatus responseStatus, final IListener listener) {
        if (listener != null) {
            listener.onError(responseStatus.getMessage(), responseStatus.getStatus());
        }
    }
}