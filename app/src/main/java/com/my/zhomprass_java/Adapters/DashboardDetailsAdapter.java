package com.my.zhomprass_java.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardDetailsAdapter extends RecyclerView.Adapter<DashboardDetailsAdapter.ViewHolder> {
    @NonNull
    @Override
    public DashboardDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardDetailsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView idTv, dateTv, fromUserNameTv, balanceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            
        }
    }
}
