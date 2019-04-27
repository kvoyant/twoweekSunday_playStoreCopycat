package com.tjeit.twoweeksunday_playstorecopycat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityAppDetailBinding;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

import java.util.Calendar;

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

        act.dateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("텍스트뷰클릭!", "실제로 동작하나?");

                DatePickerDialog dpd = new DatePickerDialog(AppDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Toast.makeText(AppDetailActivity.this, String.format("%d년 %d월 %d일",year,month,dayOfMonth),Toast.LENGTH_SHORT).show();

                        Calendar cal = Calendar.getInstance(); //new Calendar라고 만들지 않는다.

                        // 1. 항목별로 어떤값을 갖게 할건지? 코딩 방식
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        //2. 년/월/일 한꺼번에 세팅
                        cal.set(year, month , dayOfMonth);

                        //같은 메쏘드인데 , arg의 종류/갯수에 따라 다른 행동을 함. => overloading 의 예시.




                    }
                }, 2019,3,27);
                dpd.show();
            }
        });
    }
}
