package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.zhomprass_java.Models.CartProducts;
import com.my.zhomprass_java.Models.DashboardDetails;
import com.my.zhomprass_java.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardDetailsAdapter extends RecyclerView.Adapter<DashboardDetailsAdapter.ViewHolder> {

    private Context context;
    private List<DashboardDetails> list;

    public DashboardDetailsAdapter(List<DashboardDetails> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DashboardDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_details_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardDetailsAdapter.ViewHolder holder, int position) {

        DashboardDetails details = list.get(position);

        holder.idTv.setText(String.valueOf(details.getId()));
        holder.dateTv.setText(String.valueOf(details.getDate()));
        holder.fromUserNameTv.setText(details.getFrom_user_name());
        holder.balanceTv.setText(String.valueOf(details.getBalance()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<DashboardDetails> filteredList) {

        list = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView idTv, dateTv, fromUserNameTv, balanceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.idTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            fromUserNameTv = itemView.findViewById(R.id.nameTv);
            balanceTv = itemView.findViewById(R.id.balanceTv);

            
        }
    }
}
