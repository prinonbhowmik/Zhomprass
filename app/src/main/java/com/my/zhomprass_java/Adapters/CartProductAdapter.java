package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.database.CursorWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.my.zhomprass_java.Activities.CartActivity;
import com.my.zhomprass_java.Database.DatabaseHelper;
import com.my.zhomprass_java.Models.CartProducts;
import com.my.zhomprass_java.Models.DeleteProducts;
import com.my.zhomprass_java.R;
import com.my.zhomprass_java.Utils.Config;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {

    private Context context;
    private List<CartProducts> list;
    private DatabaseHelper helper;
    private List<DeleteProducts> removeList;
    private CartActivity cartActivity;
    private androidx.appcompat.app.AlertDialog.Builder alert;

    public CartProductAdapter(Context context, List<CartProducts> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load(Config.IMAGE_LINE+list.get(position).getImage()).into(holder.imageView);
        cartActivity = new CartActivity();
        holder.productsName.setText(list.get(position).getProduct_name());
        holder.qunatity.setText(String.valueOf(list.get(position).getQuantity()));
        holder.price.setText("৳"+String.valueOf(list.get(position).getPrice()*list.get(position).getQuantity()));
        holder.productsMul.setText((list.get(position).getPrice()+" X "+list.get(position).getQuantity()));
        helper = new DatabaseHelper(context);

        cartActivity.totalPriceTv.setText("Total : ৳"+total());

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count  = Integer.parseInt(holder.qunatity.getText().toString());
                count++;
                holder.qunatity.setText(String.valueOf(count));
                holder.price.setText("৳"+String.valueOf(list.get(position).getPrice()*count));
                holder.productsMul.setText(list.get(position).getPrice()+"X"+String.valueOf(count));
                helper.addQuantity(list.get(position).getId(),count);

                cartActivity.totalPriceTv.setText("Total : ৳"+total());

            }
        });
        holder.MinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count  = Integer.parseInt(holder.qunatity.getText().toString());

                if (count==1){
                    return;
                }
                count--;
                holder.qunatity.setText(String.valueOf(count));
                holder.price.setText("৳"+String.valueOf(list.get(position).getPrice()*count));
                holder.productsMul.setText(list.get(position).getPrice()+"X"+String.valueOf(count));
                helper.addQuantity(list.get(position).getId(),count);

                cartActivity.totalPriceTv.setText("Total : ৳"+total());

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert = new androidx.appcompat.app.AlertDialog.Builder(context);
                alert.setTitle("Delete!");
                alert.setMessage("Want to delete this product??");
                alert.setIcon(R.drawable.ic_delete_black_24dp);

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        helper = new DatabaseHelper(context);
                        helper.deleteData(list.get(position).getId());
                        list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Delete Successfull", Toast.LENGTH_SHORT).show();

                        cartActivity.totalPriceTv.setText("Total : ৳"+total());

                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }

    public  int total(){
        helper = new DatabaseHelper(context);
        Cursor cursor = helper.getCart();
        int count = 0;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                count+=cursor.getInt(5)*cursor.getInt(7);
            }
        }
        return count;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView productsName,price,productsMul,qunatity;
        private ImageButton plusButton,MinusButton;
        public ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.offerProductPicId);
            productsName=itemView.findViewById(R.id.cartTv);
            price=itemView.findViewById(R.id.textView);
            productsMul=itemView.findViewById(R.id.cartPriceMul);
            qunatity=itemView.findViewById(R.id.countTv);
            plusButton=itemView.findViewById(R.id.plusBtn);
            MinusButton=itemView.findViewById(R.id.minusBtn);
            delete=itemView.findViewById(R.id.deleteId);
        }
    }
}
