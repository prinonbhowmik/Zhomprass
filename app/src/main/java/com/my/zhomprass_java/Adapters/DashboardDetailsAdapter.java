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

import java.util.List;

public class DashboardDetailsAdapter extends RecyclerView.Adapter<DashboardDetailsAdapter.ViewHolder> {

    private Context context;
    private List<DashboardDetails> list;

    public DashboardDetailsAdapter(Context context, List<DashboardDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DashboardDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardDetailsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView idTv, dateTv, fromUserNameTv, balanceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            
        }
    }
}
