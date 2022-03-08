package com.example.sohrab.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (ViewPager) findViewById(R.id.viewpager);

        ArrayList<Integer> id = new ArrayList<>();
        id.add(R.drawable.a);
        id.add(R.drawable.b);
        id.add(R.drawable.ic_android_black_24dp);
        id.add(R.drawable.ic_android_black_24dp);

        pagerAdapter = new PagerAdapter(id,this);

        viewPager.setAdapter(pagerAdapter);

        viewPager.setPadding(20,20,20,20);

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                simpleTransformer(page,position);
            }
        });

    }
    public void zoomOutTransformer(View page,float position){
        int width = page.getWidth();
        int heght = page.getHeight();

        if (position<-1){
            page.setAlpha(0);
        }
        else if(position<=1){
            float scaleFactor = Math.max(0.85f,1- Math.abs(position));
            float vertMargin = heght * (1-scaleFactor)/2;
            float horzMargin = width * (1-scaleFactor)/2;
            if (position<0){
                page.setTranslationX(horzMargin-vertMargin/2);
            }
            else{
                page.setTranslationX(-horzMargin+vertMargin/2);
            }

            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            page.setAlpha(0.5f + (scaleFactor - 0.85f)/(1-0.85f)*(1-0.5f));
        }
        else{
            page.setAlpha(0);
        }
    }
    public void depthPageTransformer(View page, float position){
        int width = page.getWidth();

        if (position<-1){
            page.setAlpha(0);
        }
        else if(position<=0){
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        }
        else if(position<=1){
            page.setAlpha(1-position);

            page.setTranslationX(width * -position);

            float scaleFactor = 0.85f + (1 - 0.85f) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

        }
        else{
            page.setAlpha(0);
        }
    }
    public void simpleTransformer(View page, float position){
        if (position<-1){
            page.setAlpha(0.5f);
        }
        else if (position<=0){
            float scaleFactor = 0.07f+ 1 - Math.abs(position);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(scaleFactor);
        }
        else if (position<=1){
            page.setAlpha(1-position);
        }
        else{
            page.setAlpha(1);
        }
    }
}
