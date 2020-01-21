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
import com.my.zhomprass_java.Activities.ProductsDetailsActivity;
import com.my.zhomprass_java.Models.SearchProduct;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

import retrofit2.Callback;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {

    private List<SearchProduct> list;
    private Context context;

    public SearchProductAdapter(List<SearchProduct> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_product_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SearchProduct multiProducts = list.get(position);

        Glide.with(context).load(Config.IMAGE_LINE + multiProducts.getImage()).into(holder.imageView);
        holder.textView.setText(multiProducts.getProduct_name());
        holder.priceTv.setText(multiProducts.getPrice()+" TK");
        holder.pointTv.setText(multiProducts.getPoint()+" PT");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsDetailsActivity.class);
                intent.putExtra("product_id", multiProducts.getId());
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
        private TextView priceTv, pointTv;
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.offerProductPicId);
            textView = itemView.findViewById(R.id.offerProductId);
            priceTv = itemView.findViewById(R.id.offerPrice);
            pointTv = itemView.findViewById(R.id.pointId);
            cardView = itemView.findViewById(R.id.offerCardId);

        }
    }
}
