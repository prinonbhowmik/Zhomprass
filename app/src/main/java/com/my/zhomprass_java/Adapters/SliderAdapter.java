package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.my.zhomprass_java.ForApi.ApiInterface;
import com.my.zhomprass_java.Models.SliderImage;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.ApiUtils;
import com.my.zhomprass_java.Utils.Config;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.smarteist.autoimageslider.SliderViewAdapter.ViewHolder;

import java.util.List;

public class SliderAdapter extends  SliderViewAdapter<SliderAdapter.Holder>{

    private Context context;
    private List<SliderImage> list;

    public SliderAdapter(Context context, List<SliderImage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        Glide.with(context).load(Config.SLIDER_IMAGE_LINE+list.get(position).getUrl()).into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    class Holder extends SliderViewAdapter.ViewHolder{

        View itemView;
        ImageView imageViewBackground;

        public Holder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }



}
