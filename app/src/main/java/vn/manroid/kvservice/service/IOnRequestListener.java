package vn.manroid.kvservice.service;

/**
 * Created by manro on 12/12/2017.
 */

public interface IOnRequestListener {
    <T> void onResponse(T result);

    void onError(int statusCode);
}
