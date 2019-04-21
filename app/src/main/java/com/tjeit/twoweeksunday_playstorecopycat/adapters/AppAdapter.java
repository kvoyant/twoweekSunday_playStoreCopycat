package com.tjeit.twoweeksunday_playstorecopycat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tjeit.twoweeksunday_playstorecopycat.R;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

import java.util.List;

public class AppAdapter extends ArrayAdapter<App> {

    Context mContext;
    List<App> mList;
    LayoutInflater inf;

    public AppAdapter(Context context, List<App> list) {

        super(context, R.layout.app_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if(row == null) {
            row = inf.inflate(R.layout.app_list_item, null);
        }

        

        return row;
    }
}
