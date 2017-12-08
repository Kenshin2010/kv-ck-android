package vn.manroid.kvservice.view.fragment.chart.today;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.manroid.kvservice.R;
import vn.manroid.kvservice.adapter.NewsAdapter;
import vn.manroid.kvservice.model.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private RecyclerView rcvNews;
    private NewsAdapter adapter;
    private List<News> listNews;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, container, false);
        rcvNews = (RecyclerView) v.findViewById(R.id.rcv_news);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL,false);
        rcvNews.setLayoutManager(manager);
        listNews = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listNews.add(new News(getResources().getString(R.string.text_show)));
        }
        adapter = new NewsAdapter(listNews);
        rcvNews.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
