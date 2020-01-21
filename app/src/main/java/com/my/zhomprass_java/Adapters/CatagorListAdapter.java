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
import com.my.zhomprass_java.Activities.SubcategoryActivity;
import com.my.zhomprass_java.Models.CatagoryList;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class CatagorListAdapter extends RecyclerView.Adapter<CatagorListAdapter.ViewHolder> {

    private List<CatagoryList> list;
    private Context context;
    int number = 0;
    int count = 0;

    public CatagorListAdapter(List<CatagoryList> list, Context context) {
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

        final CatagoryList catagoryList = list.get(position);

        Glide.with(context).load(Config.IMAGE_LINE+catagoryList.getImage()).centerCrop().fitCenter().into(holder.imageView);
        holder.textView.setText(catagoryList.getCategory_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SubcategoryActivity.class));
                SharedPreferences sharedPreferences = context.getSharedPreferences("Customer_Id",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("category_id",catagoryList.getId());
                editor.putString("category_name",catagoryList.getCategory_name());
                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
         count = list.size();
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
