package com.tjeit.twoweeksunday_playstorecopycat;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityMainBinding;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<App> appList = new ArrayList<>();
    ActivityMainBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        act.titleTxt.setText("제목 확인");
        fillApps();
    }

    void fillApps() {
        appList.add(new App(1, "아스팔트 8: 에어본","GameLoft",5,6000,true));
        appList.add(new App(1, "MineCraft","GameLoft",4,4000,false));
        appList.add(new App(1, "아스팔트2","gamesoft",3,3000,true));
        appList.add(new App(1, "아스팔트3","gamesoft",5,16000,false));
        appList.add(new App(1, "아스팔트4","gamesoft",2,7000,true));
        appList.add(new App(1, "아스팔트5","gamesoft",1,5000,false));
    }
}
