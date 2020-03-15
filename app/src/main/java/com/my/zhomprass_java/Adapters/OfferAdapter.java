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
import com.my.zhomprass_java.Models.Offers;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private Context context;
    private List<Offers> list;

    public OfferAdapter(Context context, List<Offers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Offers offer = list.get(position);

        Glide.with(context).load(Config.IMAGE_LINE+offer.getImage()).into(holder.imageView);
        holder.textView.setText(offer.getProduct_name());
        double price = offer.getPrice();
        double offerprice = offer.getOffer();
        double mainprice = price-offerprice;
        holder.realPrice.setText(String.valueOf(offer.getPrice()));
        holder.price.setText(String.valueOf(mainprice)+"TK");
        holder.point.setText(offer.getPoint()+"PT");
        holder.discountText.setText(String.valueOf(offer.getOffer()+" Tk Off"));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsDetailsActivity.class);
                intent.putExtra("product_id",offer.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView textView,price,point,realPrice,discountText;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.offerProductPicId);
            textView = itemView.findViewById(R.id.offerProductId);
            price = itemView.findViewById(R.id.offerPrice);
            point = itemView.findViewById(R.id.offerPoint);
            cardView = itemView.findViewById(R.id.offerCardId);
            realPrice = itemView.findViewById(R.id.realPrice);
            discountText = itemView.findViewById(R.id.discountText);
        }
    }
}
