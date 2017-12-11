package vn.manroid.kvservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.manroid.kvservice.R;
import vn.manroid.kvservice.model.News;

/**
 * Created by manro on 08/12/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> listNews;

    public NewsAdapter(List<News> listNews) {
        this.listNews = listNews;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                (LayoutInflater) parent.getContext().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.item_news, null);
        return new NewsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news= listNews.get(position);
        holder.txtNews.setText(news.getContent());
        holder.txtNews.setOnClickListener(onItemClick);
        holder.txtNews.setTag(news);
    }

    private View.OnClickListener onItemClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News app = (News) v.getTag();
                }
            };

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView txtNews;

        public NewsViewHolder(View itemView) {
            super(itemView);
            txtNews = (TextView) itemView.findViewById(R.id.txt_content);
        }
    }
}
