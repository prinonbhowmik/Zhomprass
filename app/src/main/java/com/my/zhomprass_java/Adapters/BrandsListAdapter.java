package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.zhomprass_java.Activities.ProductsActivity;
import com.my.zhomprass_java.Models.BrandList;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class BrandsListAdapter extends RecyclerView.Adapter<BrandsListAdapter.ViewHolder> {
    private List<BrandList> list;
    private Context context;
    int number = 0;
    int count = 0;

    public BrandsListAdapter(List<BrandList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final BrandList brand = list.get(position);

        Glide.with(context).load(Config.IMAGE_LINE+brand.getImage()).centerCrop().fitCenter().into(holder.imageView);
        holder.textView.setText(brand.getBrand_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("id",brand.getId());
                intent.putExtra("sub_id",brand.getCat_id());
                intent.putExtra("flags",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.catagoryImage);
            textView = itemView.findViewById(R.id.catagoryNameId);
            cardView = itemView.findViewById(R.id.catagoryCardview);
        }
    }
}
