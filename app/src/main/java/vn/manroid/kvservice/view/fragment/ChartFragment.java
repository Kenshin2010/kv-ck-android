package vn.manroid.kvservice.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import vn.manroid.kvservice.R;
import vn.manroid.kvservice.adapter.CkAdapter;
import vn.manroid.kvservice.model.CK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private GridView grdContent,grdHisPre;
    private List<CK> list;
    private CkAdapter adapter;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chart, container, false);

        grdContent = v.findViewById(R.id.grd_content);
        grdHisPre = v.findViewById(R.id.grd_pre_his);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CK("2,"+i ));
        }
        adapter = new CkAdapter(list,getLayoutInflater());
        grdContent.setAdapter(adapter);
        grdHisPre.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
