package com.example.mashrueiadmin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mashrueiadmin.Activity.AddCenterActivity;
import com.example.mashrueiadmin.Activity.LoginActivity;
import com.example.mashrueiadmin.Activity.MessagesActivity;
import com.example.mashrueiadmin.Activity.NotificatioActivity;
import com.example.mashrueiadmin.Activity.PostActivity;
import com.example.mashrueiadmin.Activity.StoreActivity;
import com.example.mashrueiadmin.Model.DesignItemHome;
import com.example.mashrueiadmin.R;

import java.util.ArrayList;

public class AdapterSectionTypes extends RecyclerView.Adapter<AdapterSectionTypes.viewItem> {

    private ArrayList<DesignItemHome> items;
    private Context context;
    String TitleCategory;


    public AdapterSectionTypes(Context context, ArrayList<DesignItemHome> items) {

        this.items = items;
        this.context = context;
    }

    class viewItem extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        LinearLayout linearLayout;

        public viewItem(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageItem);
            title = itemView.findViewById(R.id.titleItem);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    @NonNull
    @Override
    public viewItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.design_item_home, viewGroup, false);


        return new viewItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewItem holder, final int position) {


        if (position == 0) {
            holder.linearLayout.setBackgroundColor(context.getColor(R.color.colotOne));
        } else if (position == 1) {
            holder.linearLayout.setBackgroundColor(context.getColor(R.color.colotTwo));
        } else if (position == 2) {
            holder.linearLayout.setBackgroundColor(context.getColor(R.color.colotThree));
        } else if (position == 3) {
            holder.linearLayout.setBackgroundColor(context.getColor(R.color.colotfour));
        } else if (position == 4) {
            holder.linearLayout.setBackgroundColor(context.getColor(R.color.colotfive));
        } else if (position == 5) {
            holder.linearLayout.setBackgroundColor(context.getColor(R.color.colotTwo));
        }
        holder.image.setImageResource(items.get(position).getImage());
        holder.title.setText(items.get(position).getTitle());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position == 0) {
                    Intent go = new Intent(context, StoreActivity.class);
                    context.startActivity(go);
                } else if (position == 1) {
                    Intent go = new Intent(context, AddCenterActivity.class);
                    context.startActivity(go);
                } else if (position == 2) {
                    Intent go = new Intent(context, PostActivity.class);
                    context.startActivity(go);
                }else if (position == 3) {
                    Intent go = new Intent(context, NotificatioActivity.class);
                    context.startActivity(go);
                }else if (position == 4) {
                    Intent go = new Intent(context, MessagesActivity.class);
                    context.startActivity(go);
                }else if (position == 5) {
                    SharedPreferences preferences=context.getSharedPreferences("SuccessLogIn",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("login",false);
                    editor.apply();
                    Intent go = new Intent(context, LoginActivity.class);
                    context.startActivity(go);
                    ((Activity)context).finish();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}