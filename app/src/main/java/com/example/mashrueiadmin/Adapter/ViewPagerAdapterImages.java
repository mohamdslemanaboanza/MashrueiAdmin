package com.example.mashrueiadmin.Adapter;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mashrueiadmin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerAdapterImages extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> images  ;

    public ViewPagerAdapterImages(Context context , ArrayList<String> images) {
        this.images = images;
        this.context=context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        final ImageView imageView = view.findViewById(R.id.imgfromdata);
        final LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        final TextView nameApp = view.findViewById(R.id.nameApp);
        final TextView name = view.findViewById(R.id.name);

        final ViewPagerAdapterImages adpterRec;
        adpterRec=new ViewPagerAdapterImages(context,images);
        Picasso.get().load(images.get(position)).into(imageView);

        if (context.toString().contains("DetailsActivity")){
            name.setVisibility(View.GONE);
            nameApp.setVisibility(View.GONE);
            linearLayout.setBackgroundColor(Color.BLACK);

        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.full_image_dilog);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                ImageView close = dialog.findViewById(R.id.close);
                final ViewPager viewPager = dialog.findViewById(R.id.viewPager);
                viewPager.setAdapter(adpterRec);
                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.show();


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



            }
        });





        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);





        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }



}
