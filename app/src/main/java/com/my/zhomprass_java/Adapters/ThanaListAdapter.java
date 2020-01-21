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

import com.my.zhomprass_java.Activities.BazarActivity;
import com.my.zhomprass_java.Models.Thana;
import com.my.zhomprass_java.R;

import java.util.List;

public class ThanaListAdapter extends RecyclerView.Adapter<ThanaListAdapter.ViewHolder> {

    private List<Thana> list;
    private Context context;

    public ThanaListAdapter(List<Thana> list, Context context) {
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

        final Thana thana = list.get(position);

        holder.textView.setText(thana.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BazarActivity.class));
                SharedPreferences sharedPreferences = context.getSharedPreferences("Customer_Id",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("bazar_id",thana.getId());
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
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textView = itemView.findViewById(R.id.catagoryNameId);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
