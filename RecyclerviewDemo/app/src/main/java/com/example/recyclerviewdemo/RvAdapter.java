package com.example.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author xilinhan
 * @description:
 * @date :2022/3/26 18:46
 */
public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //增加多条目类型的选择
    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;

    //数据源
    private List<Data> mList;

    //设置监听
    private OnItemClickListener mOnItemClickListener;

    public RvAdapter(List<Data> list) {
        mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType == TYPE_0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_0, parent, false);
            viewHolder = new MyViewHolder0(item);
        }else if(viewType == TYPE_1){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_1, parent, false);

            viewHolder = new MyViewHolder0(item);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type == TYPE_0){
            //TODO
            MyViewHolder0 myViewHolder0 = (MyViewHolder0) holder;
            if(mOnItemClickListener != null){
                ((MyViewHolder0) holder).ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v,position);
                    }
                });
            }
        }
        if (type == TYPE_1){
            //TODO
        }
        Data data = mList.get(position);
        MyViewHolder0 myViewHolder0 = (MyViewHolder0) holder;
        myViewHolder0.mTextView.setText(data.getString());
        myViewHolder0.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 创建ViewHolder类，用来缓存item中的子控件，避免不必要的findViewById
     * */
    class MyViewHolder0 extends RecyclerView.ViewHolder{
        LinearLayout ll;
        TextView mTextView;
        Button mButton;

        public MyViewHolder0(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.item_text);
            mButton = (Button)itemView.findViewById(R.id.item_button);
        }
    }
}
