package com.tjeit.twoweeksunday_playstorecopycat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tjeit.twoweeksunday_playstorecopycat.adapters.AppAdapter;
import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityMainBinding;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppAdapter mAppAdapter;
    List<App> appList = new ArrayList<>();
    ActivityMainBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        act.titleTxt.setText("제목 확인");
        fillApps();

        mAppAdapter = new AppAdapter(MainActivity.this, appList);
        act.appRankListView.setAdapter(mAppAdapter);

//      Q1. 확인버튼이 눌리면 "확인버튼을 눌렀습니다." 라는 토스트를 띄워봅시다.

        act.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "확인버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        act.appRankListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, String.format("%d번 줄 클릭", position), Toast.LENGTH_SHORT).show();

                App clickedAppData = appList.get(position);

                Intent intent = new Intent(MainActivity.this, AppDetailActivity.class);
//                intent.putExtra("제목", clickedAppData.title);
//                intent.putExtra("회사이름",clickedAppData.companyname);

                intent.putExtra("앱정보", clickedAppData);

                startActivity(intent);
            }
        });

        act.appRankListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, String.format("%d번 줄을 오래 누름",position),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    void fillApps() {
        appList.add(new App(1, "아스팔트 8: 에어본", "GameLoft", 5, 6000, true));
        appList.add(new App(2, "MineCraft - Pocket Edition", "Mojang", 4, 5000, true));
        appList.add(new App(3, "아스팔트 7: 하트", "GameLoft", 2, 1000, false));
        appList.add(new App(4, "팔라독(Paladog)", "FazeCat", 3, 1087, false));
        appList.add(new App(5, "Plants Vs. Zombies", "EA Swiss Sarl", 1, 2000, false));
        appList.add(new App(6, "스왐피(Swampy)", "Disney", 4, 3000, false));

//        appList.add(new App(1, "아스팔트 8: 에어본","GameLoft",5,6000,true));
//        appList.add(new App(1, "MineCraft","GameLoft",4,4000,false));
//        appList.add(new App(1, "아스팔트2","gamesoft",3,3000,true));
//        appList.add(new App(1, "아스팔트3","gamesoft",5,16000,false));
//        appList.add(new App(1, "아스팔트4","gamesoft",2,7000,true));
//        appList.add(new App(1, "아스팔트5","gamesoft",1,5000,false));


    }
}
