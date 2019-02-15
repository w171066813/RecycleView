package com.example.cwang.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cwang on 2019/1/21.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    Context mContext;
    ArrayList<String> data = null;
    String selected = "";


    public MyAdapter(@NonNull Context mContext) {
        this.mContext = mContext;
    }
    public  void setData(ArrayList<String> data){
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder)viewHolder).tv.setText(data.get(i));

        if(selected.equals(data.get(i))){
            ((ViewHolder)viewHolder).ll.setSelected(true);
        }else{
            ((ViewHolder)viewHolder).ll.setSelected(false);
        }
        ((ViewHolder)viewHolder).tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    selected = data.get(i);
                    notifyDataSetChanged();
                    listener.shortClick((ViewHolder) viewHolder);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView  tv ;
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    public interface itemShortClickListener{
        void shortClick(ViewHolder holder);
    }
    private itemShortClickListener listener;

    public void setListener(itemShortClickListener listener){
        this.listener = listener;
    }

    public void move(int start ,int end){
        if(end-start>1){
            for(int j = start;j<end;j++){
                Collections.swap(data, j,j+1);
            }
        }else if(start - end >1){
            for(int j = start;j>end;j--){
                Collections.swap(data, j,j-1);
            }
        }else{
            Collections.swap(data, start, end);
        }

    }
}
