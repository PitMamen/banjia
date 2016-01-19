package com.max.jacentsao.banjia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.max.jacentsao.banjia.R;
import com.max.jacentsao.banjia.activity.CategoryListShowActivity;

/**
 * Created by JacenTsao on 2016/1/19.
 */
public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private int[] images;
    private String[] texts;
    private LayoutInflater layoutInflater;

    public SearchRecyclerViewAdapter(Context context, int[] images, String[] texts) {
        this.context = context;
        this.images = images;
        this.texts = texts;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_search_recycler_view, null);
        MyViewHolder viewHolder = new MyViewHolder(inflate);
        viewHolder.imageView = (ImageView) inflate.findViewById(R.id.iv_search_recycler_view);
        viewHolder.textView = (TextView) inflate.findViewById(R.id.tv_search_recycler_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(images[position]);
        holder.textView.setText(texts[position]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CategoryListShowActivity.class);
                intent.putExtra("bc", position + 1 + "");
                context.startActivity(intent);
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CategoryListShowActivity.class);
                intent.putExtra("bc", position + 1 + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        ImageView imageView;
        TextView textView;
    }
}
