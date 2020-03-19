package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.my.zhomprass_java.Activities.ProductsActivity;
import com.my.zhomprass_java.Models.ShopList;
import com.my.zhomprass_java.R;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    List<ShopList> list;
    private Context context;

    public ShopListAdapter(List<ShopList> list, Context context) {
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ShopList shopList = list.get(position);

        holder.textView.setText(shopList.getShop_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ShopList shop = list.get(position);

                holder.textView.setText(shop.getShop_name());
                holder.total_product.setText("("+String.valueOf(shop.getTotal_product())+")");
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ProductsActivity.class);
                        intent.putExtra("id",shop.getShop_id());
                        intent.putExtra("flags",3);
                        context.startActivity(intent);
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView total_product;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.catagoryNameId);
            total_product = itemView.findViewById(R.id.total_shop);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
