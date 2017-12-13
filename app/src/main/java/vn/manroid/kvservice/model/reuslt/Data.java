package vn.manroid.kvservice.model.reuslt;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by manro on 12/12/2017.
 */

public class Data {

    @SerializedName("marketIndex")
    private String marketIndex;

    public String getMarketIndex() {
        return marketIndex;
    }

    public void setMarketIndex(String marketIndex) {
        this.marketIndex = marketIndex;
    }
}
