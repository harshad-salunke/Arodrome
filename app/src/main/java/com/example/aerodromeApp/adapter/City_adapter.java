package com.example.aerodromeApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aerodromeApp.CityInfoActivity;
import com.example.aerodromeApp.CitysActivity;
import com.example.aerodromeApp.R;

public class City_adapter extends BaseAdapter {
    Context context;
    String[] flowerName;
    int[] image;

    LayoutInflater inflater;

    public City_adapter(Context context, String[] flowerName, int[] image) {
        this.context = context;
        this.flowerName = flowerName;
        this.image = image;
    }

    @Override
    public int getCount() {
        return flowerName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){

            convertView = inflater.inflate(R.layout.city_list,null);

        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.item_name);
        String name = "i"+position;
        int id = context.getResources().getIdentifier(name, "drawable",context. getPackageName());
        
        imageView.setImageResource(id);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CityInfoActivity.class);
                intent.putExtra("city_name",flowerName[position]);
                context.startActivity(intent);
                
            }
        });
//        imageView.setImageResource(image[position]);
        textView.setText(flowerName[position]);

        return convertView;
    }

}
