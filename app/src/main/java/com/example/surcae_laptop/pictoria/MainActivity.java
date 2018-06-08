package com.example.surcae_laptop.pictoria;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    /*
    여기 들어왔으면 이제 FirebaseUser 데이터에 사용자 데이터가 들어와있고
    이 사용자를 이용해서 들고 볶고 썰고 할 수 있다.
     */


    //Context instance, setter, getter 생성

    private static Context context;
    RecyclerView recyclerView;
    //BottomNavigationview 작업 준비
    BottomNavigationView bottomNavigationView;


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MainActivity.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setContext(this);

        recyclerView =  (RecyclerView) findViewById(R.id.r_main);
        Bitmap[] bitmaps=setBitmaps();
        //우선 col2개로 설정
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //어뎁터 설정
        recyclerView.setAdapter(new GridAdapter(bitmaps));

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.favorite:
                        replaceFragment(FavoriteFragment.newInstance());
                        return true;
                    //mainactivty로 돌아간다
                    case R.id.home:
                        Intent intent = new Intent();
                        intent.setClass(getContext(),MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.setting:
                        replaceFragment(SettingFragment.newInstance());
                        return true;
                }
                return false;
            }

            //Fragment instance 받아서 replace
            private void replaceFragment(Fragment fragment) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.container,fragment).commit();
            }

        });

    }

    //이미지 가져올 메소드 설정
    private Bitmap[] setBitmaps() {
        //sample bitmap 설정
        Bitmap[] bitmaps = new Bitmap[10];
        bitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_00);
        bitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_01);
        bitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_02);
        bitmaps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_03);
        bitmaps[4] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_04);
        bitmaps[5] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_05);
        bitmaps[6] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_06);
        bitmaps[7] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_07);
        bitmaps[8] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_00);
        bitmaps[9] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_01);
        return bitmaps;
    }

    @Override public void onBackPressed(){ // 백버튼 막음
        //super.onBackPressed();
    }




}
