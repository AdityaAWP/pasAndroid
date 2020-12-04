package com.example.ptsfootballdb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter_rc extends RecyclerView.Adapter<Adapter_rc.ViewHolder> {
    List<ModelData> modelData= new ArrayList<ModelData>();

    public Adapter_rc(ArrayList<ModelData> modelData) {
        this.modelData = modelData;
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.circle_img);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rc_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    Glide.with(holder.img.getContext()).load(modelData.get(position).team_logo).into(holder.img);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(holder.itemView.getContext(),dataClub.class);
            i.putExtra("logo",modelData.get(position).getTeam_logo());
            i.putExtra("name",modelData.get(position).getTeam_name());
            i.putExtra("jersey",modelData.get(position).getTeam_jersey());
            i.putExtra("tahun",modelData.get(position).getTeam_year());
            i.putExtra("web",modelData.get(position).getTeam_web());
            i.putExtra("stadium",modelData.get(position).getTeam_stadium_img());
            i.putExtra("deskripsi",modelData.get(position).getTeam_detail());
            holder.itemView.getContext().startActivity(i);
        }
    });
    }

    @Override
    public int getItemCount() {

        return modelData.size();
    }
}
