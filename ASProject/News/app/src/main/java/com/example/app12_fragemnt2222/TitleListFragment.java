package com.example.app12_fragemnt2222;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;


/*
 *用来显示 Title
 */

public class TitleListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置listVi显示列表
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,DataUtils.TITLES));
        //默认选中第一个item
        getListView().setItemChecked(0,true);
        showDetail(0);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetail(position);
        Toast.makeText(getContext(),"已经切换到了"+DataUtils.TITLES[position], Toast.LENGTH_SHORT).show();
    }

    /*
     *显示下标的详情
     * @param detail
     */
    private void showDetail(int position) {
        //创建DetailFragement
        DetailFragment fragment = new DetailFragment();
        //将对应的详情数据携带过去
        Bundle args = new Bundle();
        args.putIntegerArrayList("PICTURE", (ArrayList<Integer>) DataUtils.PICTURE);
        args.putStringArrayList("NEWS", (ArrayList<String>) DataUtils.NEWSSTRING);
        fragment.setArguments(args);
        //将其替换到FrameLayout的容器布局中
        getFragmentManager().beginTransaction().replace(R.id.fl_main_container,fragment).commit();
    }
}
