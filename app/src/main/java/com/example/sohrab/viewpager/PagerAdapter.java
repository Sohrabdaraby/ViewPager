package com.example.sohrab.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by sohrab on 3/5/2022.
 */

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

    ArrayList<Integer> id = new ArrayList<>();
    Context context;

    public PagerAdapter(ArrayList<Integer> id, Context context) {
        this.id = id;
        this.context = context;
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,container,false);

        ImageView img = (ImageView) view.findViewById(R.id.img);
        img.setImageResource(id.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((LinearLayout)object);
    }
}
