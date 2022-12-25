package com.example.mashrueiadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mashrueiadmin.Model.custm_item_text;
import com.example.mashrueiadmin.R;

import java.util.ArrayList;

public class Adapter_choose_type_store extends ArrayAdapter<custm_item_text>
{
    Context context;
    private String[] images;

    public Adapter_choose_type_store(Context context, ArrayList<custm_item_text> custm_items) {
        super(context, 0, custm_items);
        this.context=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return custmview(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return custmview(position, convertView, parent);
    }

    public View custmview(int position, View convertView, ViewGroup parent)
    {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_spainer_choose_type,parent,false);

        }
        TextView ts=convertView.findViewById(R.id.txt_spiner);
        ImageView img=convertView.findViewById(R.id.img);


        custm_item_text item=getItem(position);



        if(item != null)
        {

            ts.setText(item.getSpinnertext());
            img.setImageResource(item.getImage());
        }
        return convertView;
    }
}
