package com.example.sy3_2chen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {
    private ArrayList<Map<String,String>> mdata;
    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.mdata = (ArrayList<Map<String, String>>) data;
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        final int mposition = position;
        return super.getView(position,converView,parent);
    }
    @Override
    public int getCount() {
        return mdata.size();
    }
}
