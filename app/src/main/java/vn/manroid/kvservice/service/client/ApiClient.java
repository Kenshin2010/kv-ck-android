package vn.manroid.kvservice.service.client;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Logger;

import vn.manroid.kvservice.data.RequestInfor;
import vn.manroid.kvservice.model.reuslt.Data;
import vn.manroid.kvservice.service.IOnRequestListener;
import vn.manroid.kvservice.service.util.Constant;
import vn.manroid.kvservice.service.util.RequestHandler;
import vn.manroid.kvservice.service.volley.VolleySingleton;

/**
 * Created by manro on 12/12/2017.
 */

public class ApiClient {
    private static ApiClient instance;

    private ApiClient() {

    }

    public static ApiClient getInstance() {
        return instance == null ? new ApiClient() : instance;
    }

    public <T> void post(String url, final Class<T> clazz, final Map<String, String> headers, final String requestBody, final IOnRequestListener requestListener) {
        // Todo delete when release
        final RequestInfor requestInfor = new RequestInfor(url, headers, requestBody);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                T result = gson.fromJson(response, clazz);
                if (requestListener != null)
                    requestListener.onResponse(result);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (requestListener != null)
                            requestListener.onError(parseError(error));
                    }
                }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // Todo delete when release
                requestInfor.setResponse(new String(response.data));
                RequestHandler.getInstance().addRequest(requestInfor);
                return parseResponse(response);
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(Constant.REQUEST_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    public <T> void get(String url, final Class<T> clazz, final Map<String, String> headers, final IOnRequestListener requestListener) {
        // Todo delete when release
        final RequestInfor requestInfor = new RequestInfor(url, headers, null);
        final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (clazz != null){
                    Gson gson = new Gson();
                    T result = gson.fromJson(response, clazz);
                    if (requestListener != null)
                        requestListener.onResponse(result);
                }else {
                    if (requestListener != null)
                        requestListener.onResponse(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (requestListener != null)
                    requestListener.onError(parseError(error));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // Todo delete when release
                requestInfor.setResponse(new String(response.data));
                RequestHandler.getInstance().addRequest(requestInfor);
                return parseResponse(response);
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(Constant.REQUEST_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    public <T> void request(String url, int requestType, final Class<T> clazz, final Map<String, String> headers, final String requestBody, final IOnRequestListener requestListener) {
        final RequestInfor requestInfor = new RequestInfor(url, headers, requestBody);
        StringRequest request = new StringRequest(requestType, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // Todo delete when release
                requestInfor.setResponse(new String(response.data));
                RequestHandler.getInstance().addRequest(requestInfor);
                return parseResponse(response);
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(Constant.REQUEST_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    public Response<String> parseResponse(NetworkResponse response) {
        int statusCode = response.statusCode;
        if (Constant.STATUS_CODE_SUCCESS == statusCode) {
            String parsed;
            try {
                String charset = HttpHeaderParser.parseCharset(response.headers);
                parsed = new String(response.data, charset);
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }
        VolleyError error = new VolleyError(response);
        return Response.error(error);
    }

    public int parseError(VolleyError error) {
        if (error == null || error.networkResponse == null)
            return -1;
        NetworkResponse response = error.networkResponse;
        return response.statusCode;
    }
}
