package com.example.code45;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/14 10:56
 */
public class BookListFragment extends ListFragment {
    private Callbacks mCallbacks;
    //定义一个回调接口，该Fragment所在的Activity需要实现该接口，并且该Fragment通过该接口和它所在的Activity进行交互
    interface Callbacks{
        void onItemSelected(int id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为该ListFragment设置Adapter
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1));
    }
    //当该Fragment被添加、显示到它所在的Context的时候，回调该方法

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof Callbacks)){
            throw new IllegalStateException("..");
        }
        mCallbacks = (Callbacks) context;
    }

    //当该Fragment从它所属的Activity中删除的时候回调该方法

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    //当用户单击某列表项的时候激发该回调方法

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    //为自适应手机和平板电脑屏幕的方法
    public void setActivateOnItemClick(boolean activateOnItemClick){
        getListView().setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
    }
}
