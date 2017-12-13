package vn.manroid.kvservice.model.reuslt;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by manro on 12/12/2017.
 */

public class IndexResult {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
