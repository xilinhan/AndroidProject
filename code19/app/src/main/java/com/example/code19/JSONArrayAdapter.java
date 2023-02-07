package com.example.code19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/22 22:24
 */
public class JSONArrayAdapter extends RecyclerView.Adapter<JSONArrayAdapter.TextViewHolder> {
    private Context mContext;
    //定义需要包装的JSONArray对象
    private JSONArray mJSONArray;
    //定义列表项显示JSONObject对象的哪个属性
    private String property;
    private ItemClickCallback mItemClickCallback;

    public JSONArrayAdapter(Context context, JSONArray JSONArray, String property, ItemClickCallback itemClickCallback) {
        mContext = context;
        mJSONArray = JSONArray;
        this.property = property;
        mItemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(mContext).inflate(R.layout.view_item,linearLayout);

        return new TextViewHolder(linearLayout,mItemClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.mTextView.setText("");
    }

    @Override
    public int getItemCount() {
        return mJSONArray.length();
    }


    class TextViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        public TextViewHolder(@NonNull View itemView, ItemClickCallback itemClickCallback) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.view_title);
            //如果callback不为null。则为列表项绑定事件监听器
            if(itemClickCallback != null){
                itemView.findViewById(R.id.view_title).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }
}
