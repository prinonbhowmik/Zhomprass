package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.zhomprass_java.Activities.ProductsDetailsActivity;
import com.my.zhomprass_java.Models.RelatedProduct;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.ViewHolder> {

    List<RelatedProduct> list;
    private Context context;

    public RelatedProductAdapter(List<RelatedProduct> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_product_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load(Config.IMAGE_LINE+list.get(position).getImage()).into(holder.imageView);

        holder.text.setText(list.get(position).getProduct_name());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsDetailsActivity.class);
                intent.putExtra("product_id",list.get(position).getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text;
        TextView price;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.offerProductPicId);
            text=itemView.findViewById(R.id.offerProductId);
            price=itemView.findViewById(R.id.offerPrice);
            cardView=itemView.findViewById(R.id.offerCardId);
        }
    }
}
