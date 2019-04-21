package com.tjeit.twoweeksunday_playstorecopycat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityAppDetailBinding;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

public class AppDetailActivity extends AppCompatActivity {

    App mAppData; //이 화면에서 다룰 앱의 정보를 가진 멤버변수
    ActivityAppDetailBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = DataBindingUtil.setContentView(this, R.layout.activity_app_detail);

//        String appTitle = getIntent().getStringExtra("제목");
//        String appCompanyName = getIntent().getStringExtra("회사이름");

//        act.appTitleTxt.setText(appTitle);
//        act.companyNameTxt.setText(appCompanyName);

        mAppData = (App)getIntent().getSerializableExtra("앱정보");

        act.appTitleTxt.setText(mAppData.title);
        act.companyNameTxt.setText(mAppData.companyname);

        act.userRatingTxt.setText(String.format("%d점",mAppData.userRating));

        //구매여부에 따라 필요한 버튼만 보이기
        if(mAppData.isMine) {
            act.removeBtn.setVisibility(View.VISIBLE);
            act.launchBtn.setVisibility(View.VISIBLE);
            act.purchaseBtn.setVisibility(View.GONE);
        }
        else {
            act.removeBtn.setVisibility(View.GONE);
            act.launchBtn.setVisibility(View.GONE);
            act.purchaseBtn.setVisibility(View.VISIBLE);

            //구매하기 버튼의 문구도 올바른 가격으로
            act.purchaseBtn.setText(String.format("구매하기(%,d원)",mAppData.price));
        }

        act.dialBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Uri phoneUri = Uri.parse("tel:010-1234-5678");
                Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(intent);
            }
        });
    }
}
