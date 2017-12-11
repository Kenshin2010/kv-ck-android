package vn.manroid.kvservice.view.fragment.chart;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import vn.manroid.kvservice.R;
import vn.manroid.kvservice.adapter.CkAdapter;
import vn.manroid.kvservice.model.CK;
import vn.manroid.kvservice.view.fragment.chart.today.ChartTodayFragment;
import vn.manroid.kvservice.view.fragment.chart.today.CurrencyFragment;
import vn.manroid.kvservice.view.fragment.chart.today.DbFragment;
import vn.manroid.kvservice.view.fragment.chart.today.NewsFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment implements View.OnClickListener{

    private GridView grdContent, grdHisPre;
    private List<CK> list;
    private CkAdapter adapter;
    private Button btnNews,btnChart,btnDb,btnCurrency;

    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        grdContent = v.findViewById(R.id.grd_content);
        grdHisPre = v.findViewById(R.id.grd_pre_his);
        btnNews = (Button) v.findViewById(R.id.btn_news);
        btnChart = (Button) v.findViewById(R.id.btn_chart);
        btnDb = (Button) v.findViewById(R.id.btn_db);
        btnCurrency = (Button) v.findViewById(R.id.btn_currency);
        btnNews.setOnClickListener(this);
        btnDb.setOnClickListener(this);
        btnCurrency.setOnClickListener(this);
        btnChart.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CK("2," + i));
        }
        adapter = new CkAdapter(list, getLayoutInflater());
        grdContent.setAdapter(adapter);
        grdHisPre.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_news:
                changeLayout(new NewsFragment());
                break;
            case R.id.btn_chart:
                changeLayout(new ChartTodayFragment());
                break;
            case R.id.btn_db:
                changeLayout(new DbFragment());
                break;
            case R.id.btn_currency:
                changeLayout(new CurrencyFragment());
                break;
        }
    }

    private void changeLayout(Fragment frag) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contentPanel, frag);
        transaction.commit();
    }
}
