package com.example.code19;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/22 22:12
 */
public class ViewItemFragment extends Fragment {
    private JSONArray mJSONArray;
    private ItemClickCallback mItemClickCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_item, container, false);
        //获取界面的返回按钮
        Button bnHome = rootView.findViewById(R.id.bn_home);
        RecyclerView succList = rootView.findViewById(R.id.succList);
        succList.setHasFixedSize(true);
        //为RecyclerView设置布局管理器
        succList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        TextView viewTitle = rootView.findViewById(R.id.view_title);
        bnHome.setOnClickListener(v -> {
            //TODO
        });
        assert getArguments() != null;
        String action = getArguments().getString("action");
        //定义发送请求的URL
        String url = HttpUtil.BASE_URL + action;
        if(action != null && action.equals("item/fail")){
            viewTitle.setText("");
        }
        String result = null;
        //向制定URL发送请求，并且把服务器响应转换成JSONArray对象
        try {
            result = HttpUtil.getRequest(url);
            mJSONArray = new JSONArray(result);
            //将JSONArray包装成Adapter
            JSONArrayAdapter adapter =new JSONArrayAdapter(getActivity(), mJSONArray, "name", mItemClickCallback);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public interface viewItemDetail{

    }
}
