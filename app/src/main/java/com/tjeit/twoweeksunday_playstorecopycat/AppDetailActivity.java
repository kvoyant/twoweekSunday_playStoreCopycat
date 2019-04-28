package com.tjeit.twoweeksunday_playstorecopycat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tjeit.twoweeksunday_playstorecopycat.databinding.ActivityAppDetailBinding;
import com.tjeit.twoweeksunday_playstorecopycat.datas.App;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
//                Intent intent = new Intent(Intent.ACTION_CALL, phoneUri);
                startActivity(intent);
            }
        });

        //연습문제
        //날짜를 누루고 선택하면 반영되게
        act.dateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(AppDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();

                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        cal.set(year,month,dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");

                        String datestr = sdf.format(cal.getTimeInMillis());

                        act.dateTxt.setText(datestr);
                    }
                },2019,3,27);

                dpd.show();
            }
        });

        //시간을 누르고 선택하면 반영되게 AM/PM을 오전/오후로 변경.
        act.timeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(AppDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("a h시 m분", Locale.KOREA);

                        String timeStr = sdf.format(cal.getTimeInMillis());

                        act.timeTxt.setText(timeStr);

                    }
                }, 3, 15, true);

                tpd.show();
            }
        });

        act.smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri smsUri = Uri.parse("smsto:01012345678");
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsUri);
                intent.putExtra("sms_body","미리 작성된 메세지");
                startActivity(intent);
            }
        });

        act.goHomepageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.naver.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        // AMD에 플레이 스토어가 없으면 에러날수 있음
        act.purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=com.supercell.brawlstars");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });



/*
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

                        //cal에 저장된 값을 String으로 (양식에 맞게) 바꿔서 TextView로 세팅
                        //날짜를 양식을 바꾸고 싶을때 : SimpleDateFormat을 사용.

                        //어떤 양식으로 문자를 출력할지 지정, 양식 지정.
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일");

                        //지정된 양식으로 Calender 변수를 String으로 변환
                        String dateStr = sdf.format(cal.getTimeInMillis());

                        act.dateTxt.setText(dateStr);
                    }
                }, 2019,3,27);
                dpd.show();
            }
        });

        act.timeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(AppDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("a h시 m분", Locale.KOREA);

                        String timeStr = sdf.format(cal.getTimeInMillis());

                        act.timeTxt.setText(timeStr);
                    }
                }, 15,15, true);

                tpd.show();
            }
        });
*/
    }
}
