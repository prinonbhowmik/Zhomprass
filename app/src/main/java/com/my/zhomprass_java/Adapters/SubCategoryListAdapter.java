package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.zhomprass_java.Activities.ThiredCategoryActivity;
import com.my.zhomprass_java.Models.SubCategoryList;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.ViewHolder> {
    private List<SubCategoryList> list;
    private Context context;

    public SubCategoryListAdapter(List<SubCategoryList> list, Context context) {
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

        final SubCategoryList categoryList = list.get(position);
        Glide.with(context).load(Config.IMAGE_LINE+categoryList.getImage()).centerCrop().fitCenter().into(holder.imageView);
        holder.textView.setText(categoryList.getSub_category_name());
        holder.total_product.setText("("+String.valueOf(categoryList.getTotal_product())+")");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("Customer_Id",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("category_id_third",categoryList.getId());
                editor.putString("category_name_third",categoryList.getSub_category_name());
                editor.apply();
                context.startActivity(new Intent(context, ThiredCategoryActivity.class));
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
        private TextView total_product;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.catagoryImage);
            textView = itemView.findViewById(R.id.catagoryNameId);
            total_product = itemView.findViewById(R.id.total_product);
            cardView = itemView.findViewById(R.id.catagoryCardview);
        }
    }
}
