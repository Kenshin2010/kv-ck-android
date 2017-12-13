package vn.manroid.kvservice.service.util;

import java.util.ArrayList;
import java.util.List;

import vn.manroid.kvservice.data.RequestInfor;

/**
 * Created by manro on 12/12/2017.
 */

public class RequestHandler {
    private static RequestHandler instance;
    private List<RequestInfor> listRequestInfor = new ArrayList<>();

    private RequestHandler() {

    }

    public static RequestHandler getInstance() {
        if (instance == null)
            instance = new RequestHandler();
        return instance;
    }

    public void addRequest(RequestInfor requestInfor) {
        listRequestInfor.add(requestInfor);
    }

    public List<RequestInfor> getListRequestInfor() {
        return listRequestInfor;
    }
}

