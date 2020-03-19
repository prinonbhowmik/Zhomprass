package com.my.zhomprass_java.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.zhomprass_java.Models.DashboardDetails;
import com.my.zhomprass_java.Models.GenerationModel;
import com.my.zhomprass_java.R;

import java.util.List;

import retrofit2.Callback;

public class GenerationAdapter extends RecyclerView.Adapter<GenerationAdapter.ViewHolder> {

    private Context context;
    private List<GenerationModel> list;

    public GenerationAdapter(Context context, List<GenerationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generation_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GenerationModel generationModel = list.get(position);

        holder.idTv.setText(String.valueOf(generationModel.getId()));
        holder.dateTv.setText(String.valueOf(generationModel.getDate()));
        holder.positionTv.setText(String.valueOf(generationModel.getPosition()));
        holder.fromUserNameTv.setText(generationModel.getFrom_user_name());
        holder.balanceTv.setText(String.valueOf(generationModel.getBalance()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idTv, dateTv,positionTv, fromUserNameTv, balanceTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.idTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            positionTv = itemView.findViewById(R.id.positionTv);
            fromUserNameTv = itemView.findViewById(R.id.nameTv);
            balanceTv = itemView.findViewById(R.id.balanceTv);

        }
    }
}
