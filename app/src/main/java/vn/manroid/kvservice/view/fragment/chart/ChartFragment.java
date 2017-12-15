package vn.manroid.kvservice.view.fragment.chart;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.manroid.kvservice.R;
import vn.manroid.kvservice.adapter.CkAdapter;
import vn.manroid.kvservice.model.CK;
import vn.manroid.kvservice.model.reuslt.BaseResult;
import vn.manroid.kvservice.model.reuslt.Data;
import vn.manroid.kvservice.model.reuslt.IndexResult;
import vn.manroid.kvservice.service.IOnRequestListener;
import vn.manroid.kvservice.service.api.ApiManager;
import vn.manroid.kvservice.service.util.Logger;
import vn.manroid.kvservice.util.StringUtil;
import vn.manroid.kvservice.view.fragment.chart.today.ChartTodayFragment;
import vn.manroid.kvservice.view.fragment.chart.today.CurrencyFragment;
import vn.manroid.kvservice.view.fragment.chart.today.DbFragment;
import vn.manroid.kvservice.view.fragment.chart.today.NewsFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.grd_content)
    GridView grdContent;
    @BindView(R.id.grd_pre_his)
    GridView grdHisPre;
    @BindView(R.id.btn_news)
    Button btnNews;
    @BindView(R.id.btn_chart)
    Button btnChart;
    @BindView(R.id.btn_db)
    Button btnDb;
    @BindView(R.id.btn_currency)
    Button btnCurrency;
    @BindView(R.id.txt_first_vn)
    TextView txtFirstVN;
    @BindView(R.id.txt_first_vn30)
    TextView txtFirstVN30;
    @BindView(R.id.txt_first_hnx)
    TextView txtFirstHNX;
    @BindView(R.id.txt_nhp)
    TextView txtNHP;

    private Timer timer;
    private List<CK> list;
    private CkAdapter adapter;
    private Handler handler;
    private boolean loop = true;

//    private Button btnNews, btnChart, btnDb, btnCurrency;
//    private GridView grdContent, grdHisPre;

    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, v);
        handler = new Handler();
        //    grdContent = v.findViewById(R.id.grd_content);
        //    grdHisPre = v.findViewById(R.id.grd_pre_his);
        //    btnNews = (Button) v.findViewById(R.id.btn_news);
        //    btnChart = (Button) v.findViewById(R.id.btn_chart);
        //    btnDb = (Button) v.findViewById(R.id.btn_db);
        //    btnCurrency = (Button) v.findViewById(R.id.btn_currency);
        btnNews.setOnClickListener(this);
        btnDb.setOnClickListener(this);
        btnCurrency.setOnClickListener(this);
        btnChart.setOnClickListener(this);
        txtNHP.setOnClickListener(this);
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
        repeat();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
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
            case R.id.txt_nhp:
                getMaCK();
                break;
        }
    }

    private void repeat() {
        handler.postDelayed(runnable, 6000);
        setAnim();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getIndex();
            frame1.stop();
            frame2.stop();
            frame3.stop();
//            getMaCK();
            if (loop)
                repeat();
        }
    };


    private void getMaCK() {
        ApiManager.getInstance().getMaCk(new IOnRequestListener() {
            @Override
            public <T> void onResponse(T result) {
                Logger.d(result.toString());
                splitData(result.toString());
            }

            @Override
            public void onError(int statusCode) {

            }
        });
    }

    private void getIndex() {
        ApiManager.getInstance().getAllIndex(BaseResult.class, new IOnRequestListener() {
            @Override
            public <T> void onResponse(T result) {
                BaseResult response = BaseResult.class.cast(result);
                IndexResult vnResult = response.getVn_Index();
                IndexResult vn30Ruslt = response.getVn30_Index();
                IndexResult hnxResult = response.getHnx_Index();
                Data vn = vnResult.getData();
                Data vn30 = vn30Ruslt.getData();
                Data hnx = hnxResult.getData();
                txtFirstVN.setText(vn.getMarketIndex());
                txtFirstVN30.setText(vn30.getMarketIndex());
                txtFirstHNX.setText(StringUtil.RoundingModeUp(Double.parseDouble(hnx.getMarketIndex())));
                Logger.d(vn.getMarketIndex() + " ====== " + vn30.getMarketIndex() + " ====== " + hnx.getMarketIndex() + " ====== ");
                Logger.d("Repeat ===================");

            }

            @Override
            public void onError(int statusCode) {

            }
        });
    }

    private AnimationDrawable frame1;
    private AnimationDrawable frame2;
    private AnimationDrawable frame3;

    private void setAnim() {
        txtFirstHNX.setBackgroundResource(R.drawable.anim_list);
        txtFirstVN.setBackgroundResource(R.drawable.anim_list);
        txtFirstVN30.setBackgroundResource(R.drawable.anim_list);
        frame1 = (AnimationDrawable) txtFirstHNX.getBackground();
        frame2 = (AnimationDrawable) txtFirstVN.getBackground();
        frame3 = (AnimationDrawable) txtFirstVN30.getBackground();
        frame1.start();
        frame2.start();
        frame3.start();
    }

    public static void splitData(String json) {
        List<String> data = new ArrayList<String>();
        String[] strs = json.split("[,\\|]");
        for (int i = 0; i < strs.length; i++) {
            data.add(strs[i]);
            Logger.d(data.get(i));
        }
    }

    private void changeLayout(Fragment frag) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contentPanel, frag);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
