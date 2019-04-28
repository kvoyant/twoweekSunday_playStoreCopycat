package com.tjeit.twoweeksunday_playstorecopycat;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Toast;

import com.tjeit.twoweeksunday_playstorecopycat.adapters.AppAdapter;
import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityMainBinding;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int REQ_FOR_FILTER = 150;

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
//                Toast.makeText(MainActivity.this, "확인버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();

                //
                AlertDialog.Builder okAlert = new AlertDialog.Builder(MainActivity.this);
                okAlert.setTitle("게임 추가 알림");
                okAlert.setMessage("임시 게임이 추가되었습니다");
                okAlert.setPositiveButton("확인",null);
                okAlert.show();

                //내용추가
                appList.add(new App(10,"임시게임","이상",4,39000,false));
                mAppAdapter.notifyDataSetChanged();
                //마지막으로 이동
                act.appRankListView.smoothScrollToPosition(appList.size()-1);

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
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(MainActivity.this, String.format("%d번 줄을 오래 누름",position),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("앱 삭제 확인");
                alert.setMessage("정말 이 앱을 삭제하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        appList.remove(position);//삭제
                        //리스트의 내용이 바꼈음을 어댑터에게 알려줌.
                        //어댑터가 변경내용을 감지해서 새로고침하듯이 리스트 갱신
                        mAppAdapter.notifyDataSetChanged();//삭제완료처리

                        Toast.makeText(MainActivity.this, "해당 앱이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("취소", null);
                alert.show();

                return false;
            }
        });

        act.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                필터입력하는 화면을 실행
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
//                startActivity(intent);//편도로 가는 방법
                startActivityForResult(intent, REQ_FOR_FILTER);//150 필터를 가지고 가는 요청

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("액티비티 결과", "결과가 돌아옴 !");
        Log.d("리퀘스트코드",requestCode+"");
        Log.d("resultCode",resultCode+"");

        if(requestCode == REQ_FOR_FILTER) {////150 필터를 가지고 가는 요청인가?
            //필터 설정하러 갔다 들어온게 맞다!
            if(resultCode == RESULT_OK) {
                //확인 버튼 눌린게 맞다
//                Toast.makeText(this, "필터설정을 확인 했습니다.", Toast.LENGTH_SHORT).show();
                int filteredRating = data.getIntExtra("최소평점", 0);
                act.filterRatingTxt.setText(String.format("( 현재 필터 : %d )",filteredRating));
            }
            else {
                //확인아니고 뒤로가기나 취소..
                Toast.makeText(this, "필터설정을 취소했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void fillApps() {
        appList.add(new App(1, "아스팔트 8: 에어본", "GameLoft", 5, 6000, true));
        appList.add(new App(2, "MineCraft - Pocket Edition", "Mojang", 4, 5000, true));
        appList.add(new App(3, "아스팔트 7: 하트", "GameLoft", 2, 1000, false));
        appList.add(new App(4, "팔라독(Paladog)", "FazeCat", 3, 1087, false));
        appList.add(new App(5, "Plants Vs. Zombies", "EA Swiss Sarl", 1, 2000, false));
        appList.add(new App(6, "스왐피(Swampy)", "Disney", 4, 3000, false));
        appList.add(new App(1, "아스팔트 8: 에어본", "GameLoft", 5, 6000, true));
        appList.add(new App(2, "MineCraft - Pocket Edition", "Mojang", 4, 5000, true));
        appList.add(new App(3, "아스팔트 7: 하트", "GameLoft", 2, 1000, false));
        appList.add(new App(4, "팔라독(Paladog)", "FazeCat", 3, 1087, false));
        appList.add(new App(5, "Plants Vs. Zombies", "EA Swiss Sarl", 1, 2000, false));
        appList.add(new App(6, "스왐피(Swampy)", "Disney", 4, 3000, false));
        appList.add(new App(1, "아스팔트 8: 에어본", "GameLoft", 5, 6000, true));
        appList.add(new App(2, "MineCraft - Pocket Edition", "Mojang", 4, 5000, true));
        appList.add(new App(3, "아스팔트 7: 하트", "GameLoft", 2, 1000, false));
        appList.add(new App(4, "팔라독(Paladog)", "FazeCat", 3, 1087, false));
        appList.add(new App(5, "Plants Vs. Zombies", "EA Swiss Sarl", 1, 2000, false));
        appList.add(new App(6, "스왐피(Swampy)", "Disney", 4, 3000, false));
        appList.add(new App(1, "아스팔트 8: 에어본", "GameLoft", 5, 6000, true));
        appList.add(new App(2, "MineCraft - Pocket Edition", "Mojang", 4, 5000, true));
        appList.add(new App(3, "아스팔트 7: 하트", "GameLoft", 2, 1000, false));
        appList.add(new App(4, "팔라독(Paladog)", "FazeCat", 3, 1087, false));
        appList.add(new App(5, "Plants Vs. Zombies", "EA Swiss Sarl", 1, 2000, false));
        appList.add(new App(6, "스왐피(Swampy)", "Disney", 4, 3000, false));
        appList.add(new App(1, "아스팔트 8: 에어본", "GameLoft", 5, 6000, true));
        appList.add(new App(2, "MineCraft - Pocket Edition", "Mojang", 4, 5000, true));
        appList.add(new App(3, "아스팔트 7: 하트", "GameLoft", 2, 1000, false));
        appList.add(new App(4, "팔라독(Paladog)", "FazeCat", 3, 1087, false));
        appList.add(new App(5, "Plants Vs. Zombies", "EA Swiss Sarl", 1, 2000, false));
        appList.add(new App(6, "스왐피(Swampy)", "Disney", 4, 3000, false));
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
