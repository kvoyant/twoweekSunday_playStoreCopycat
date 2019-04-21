package com.tjeit.twoweeksunday_playstorecopycat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        //각줄에 맞는 앱 데이터를 mList에서 추출
        App appData = mList.get(position);

        TextView rankAndTitleTxt = row.findViewById(R.id.rankAndTitleTxt);
        TextView companyNameTxt = row.findViewById(R.id.companyNameTxt);
        TextView priceOrInstalledTxt = row.findViewById(R.id.priceOrInstalledTxt);

        //등수와 제목을 세팅 .
        rankAndTitleTxt.setText(String.format("%d. %s",appData.rank, appData.title));
        //회사이름은 가진 그대로
        companyNameTxt.setText(appData.companyname);

        //만약 설치가 되었다면 ? 설치된 항목 아니면 가격 3.000 의 양식
        if(appData.isMine == true) {
            //설치된 항목 그래로 표기..
            //재사용성을 인해 원하지 않은 데이터가 나올수 있으므로..설치된항목은 명시해야 한다.
            priceOrInstalledTxt.setText("설치된 항목");
        }
        else {
            //설치하지 않은 경우
            priceOrInstalledTxt.setText(String.format("%,d원",appData.price));
        }



        return row;
    }
}
