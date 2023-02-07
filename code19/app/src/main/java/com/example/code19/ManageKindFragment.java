package com.example.code19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/23 19:22
 */
public class ManageKindFragment extends Fragment {
    public static final int ADD_KIND = 0x1007;
    private Callbacks mCallbacks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.manage_kind, container, false);
        Button bnHome = rootView.findViewById(R.id.bn_home);
        Button bnAdd = rootView.findViewById(R.id.bnAdd);
        RecyclerView kindList = rootView.findViewById(R.id.kindList);
        kindList.setHasFixedSize(true);
        kindList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        bnHome.setOnClickListener(v -> {

        });
        String url = HttpUtil.BASE_URL + "kinds";
        String result = null;
        try {
            result = HttpUtil.getRequest(url);
            JSONArray jsonArray = new JSONArray(result);
            kindList.setAdapter();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
