package vn.manroid.kvservice.view.fragment.chart.today;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.manroid.kvservice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartTodayFragment extends Fragment {


    public ChartTodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_today, container, false);
    }

}
