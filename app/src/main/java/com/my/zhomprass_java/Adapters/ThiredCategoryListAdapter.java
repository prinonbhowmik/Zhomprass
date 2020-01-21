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
import com.my.zhomprass_java.Models.ThiredCategoryList;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class ThiredCategoryListAdapter extends RecyclerView.Adapter<ThiredCategoryListAdapter.ViewHolder> {

    private List<ThiredCategoryList> list;
    private Context context;
    private int count = 0;

    public ThiredCategoryListAdapter(List<ThiredCategoryList> list, Context context) {
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

        final ThiredCategoryList third = list.get(position);

        Glide.with(context).load(Config.IMAGE_LINE+third.getImage()).centerCrop().fitCenter().into(holder.imageView);
        holder.textView.setText(third.getSub_category_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("id",third.getId());
                intent.putExtra("flags",2);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        count=list.size();
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.catagoryImage);
            textView=itemView.findViewById(R.id.catagoryNameId);
            cardView=itemView.findViewById(R.id.catagoryCardview);

        }
    }
}
