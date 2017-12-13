package vn.manroid.kvservice.model.reuslt;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Mr.Sen on 10/23/2017.
 */

public class BaseResult {
    @SerializedName("10")
    private IndexResult vn_Index;

    @SerializedName("11")
    private IndexResult vn30_Index;

    @SerializedName("02")
    private IndexResult hnx_Index;

    public IndexResult getVn_Index() {
        return vn_Index;
    }

    public void setVn_Index(IndexResult vn_Index) {
        this.vn_Index = vn_Index;
    }

    public IndexResult getVn30_Index() {
        return vn30_Index;
    }

    public void setVn30_Index(IndexResult vn30_Index) {
        this.vn30_Index = vn30_Index;
    }

    public IndexResult getHnx_Index() {
        return hnx_Index;
    }

    public void setHnx_Index(IndexResult hnx_Index) {
        this.hnx_Index = hnx_Index;
    }

    //    private Map<String, String> data;

//    public String getIndex() {
//        return index;
//    }
//
//    public void setIndex(String index) {
//        this.index = index;
//    }

//    public Map<String, String> getData() {
//        return data;
//    }
//
//    public void setData(Map<String, String> data) {
//        this.data = data;
//    }
}
