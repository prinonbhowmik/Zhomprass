package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.zhomprass_java.Models.ZplDetails;
import com.my.zhomprass_java.R;

import java.util.ArrayList;
import java.util.List;

public class ZplDetailsAdapter extends RecyclerView.Adapter<ZplDetailsAdapter.ViewHolder> {

    private Context context;
    private List<ZplDetails> list;

    public ZplDetailsAdapter( List<ZplDetails> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zp_details_models, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ZplDetails zplDetails = list.get(position);

        holder.idTv.setText(String.valueOf(zplDetails.getId()));
        holder.dateTv.setText(String.valueOf(zplDetails.getDate()));
        holder.levelTv.setText(zplDetails.getLevel());
        holder.balanceTv.setText(String.valueOf(zplDetails.getBalance()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<ZplDetails> filteredList) {

        list = filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idTv, dateTv, levelTv, balanceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.idTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            levelTv = itemView.findViewById(R.id.levelTv);
            balanceTv = itemView.findViewById(R.id.balanceTv);
        }
    }
}
