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

import com.my.zhomprass_java.Activities.DistrictActivity;
import com.my.zhomprass_java.Models.Division;
import com.my.zhomprass_java.R;

import java.util.List;

public class DivisionListAdapter extends RecyclerView.Adapter<DivisionListAdapter.ViewHolder> {

    private List<Division> division;
    private Context context;

    public DivisionListAdapter(List<Division> division, Context context) {
        this.division = division;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.division_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Division list = division.get(position);

        holder.textView.setText(list.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, DistrictActivity.class));
                SharedPreferences sharedPreferences = context.getSharedPreferences("Customer_Id",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("district_id",list.getId());
                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
        return division.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.catagoryNameId);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
