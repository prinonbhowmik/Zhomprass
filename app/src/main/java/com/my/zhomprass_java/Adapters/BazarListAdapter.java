package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.my.zhomprass_java.Activities.ShopActivity;
import com.my.zhomprass_java.Models.Bazar;
import com.my.zhomprass_java.R;

import java.util.List;

public class BazarListAdapter extends RecyclerView.Adapter<BazarListAdapter.ViewHolder> {

    List<Bazar> list;
    Context context;

    public BazarListAdapter(List<Bazar> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.division_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Bazar bazar = list.get(position);

        holder.textView.setText(bazar.getName());
        holder.total_shop.setText("("+String.valueOf(bazar.getTotal_shop())+")");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ShopActivity.class));
                SharedPreferences sharedPreferences = context.getSharedPreferences("Customer_Id",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("shop_id",bazar.getId());
                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView total_shop;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.catagoryNameId);
            total_shop = itemView.findViewById(R.id.total_shop);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
