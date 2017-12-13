package vn.manroid.kvservice.service.api;

import android.content.Context;

import vn.manroid.kvservice.constant.KVContans;
import vn.manroid.kvservice.service.IOnRequestListener;
import vn.manroid.kvservice.service.client.ApiClient;
import vn.manroid.kvservice.util.StringUtil;

/**
 * Created by manro on 12/12/2017.
 */

public class ApiManager {
    public static ApiManager instance;
    private Context mContext;

    private ApiManager() {

    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public String getUrl(String index, String baseUrl) {
        StringBuilder sb = new StringBuilder(baseUrl);
        // get index
        if (StringUtil.isNullOrEmpty(index))
            sb.append(KVContans.REQUEST_VN_INDEX);
        else
            sb.append(index);

        sb.append(KVContans.COMMA).append(KVContans.REQUEST_VN30_INDEX).append(KVContans.COMMA).append(KVContans.REQUEST_HNX_INDEX);
        return sb.toString();
    }

    public String getUrlMACK(String index, String baseUrl) {
        StringBuilder sb = new StringBuilder(baseUrl);
        // get index
        if (StringUtil.isNullOrEmpty(index))
            sb.append(KVContans.REQUEST_MA_CK_HKB);
        else
            sb.append(index);
        return sb.toString();
    }


    public <T> void getAllIndex(final Class<T> clazz, IOnRequestListener listener) {
        String url = getUrl(KVContans.REQUEST_VN_INDEX, KVContans.BASE_REQUEST_INDEX);
        ApiClient.getInstance().get(url, clazz, null, listener);
    }

    public void getMaCk(IOnRequestListener listener) {
        String url = getUrlMACK(KVContans.REQUEST_MA_CK_HKB,KVContans.BASE_REQUEST_MA_CK );
        ApiClient.getInstance().get(url,null,null,listener);
    }
}
