package vn.manroid.kvservice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.manroid.kvservice.R;
import vn.manroid.kvservice.model.CK;

/**
 * Created by manro on 06/12/2017.
 */

public class CkAdapter extends BaseAdapter {

    private List<CK> list;
    private LayoutInflater inflater;

    public CkAdapter(List<CK> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CK getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CK ck = getItem(i);

        view = inflater.inflate(R.layout.item_content,null);
        TextView txtNumber = view.findViewById(R.id.txt_number);

        txtNumber.setText(ck.getNumber());

        return view;
    }
}
