package com.tjeit.twoweeksunday_playstorecopycat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity {

    ActivityFilterBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this, R.layout.activity_filter);

        act.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(FilterActivity.this, MainActivity.class);
//                intent.putExtra("필터값", 3);
//                startActivity(intent);

//                결과를 저장해서 , 필터화면을 호출한 곳으로 돌아가도록,
//                1. 결과 저장
                int minRating = Integer.parseInt(act.minRatingEdt.getText().toString());
//                2.메인화면으로 돌아가기 새로 띄우는거 x, 이전화면으로 돌아가기.
//                돌아가는 Intent() 아무것도 안넣음
                Intent resultIntent = new Intent();
                //결과 첨부
                resultIntent.putExtra("최소평점",minRating);
                //지금화면 닫느다.
                //finish 하기전에 결과 설정
                setResult(RESULT_OK, resultIntent);
                //모든설정이 끝났으니 이화면을 닫는다.
                finish();
            }
        });
    }
}
