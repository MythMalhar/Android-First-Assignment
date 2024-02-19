package com.example.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder>{
    List<model> data;
    Context context;

    public recyclerAdapter(List<model> data, HomeActivity activity) {
        this.data=data;
        this.context=activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_card,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final model dataList = data.get(position);
        holder.name.setText(dataList.getName());
        holder.type.setText(dataList.getType());
        holder.hitpoints.setText(dataList.getHitpoints());
        holder.location.setText(dataList.getLocation());
        Picasso.get().load(dataList.getImg()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataList.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,type,hitpoints,location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_card);
            name = itemView.findViewById(R.id.text_pokaemonname);
            type = itemView.findViewById(R.id.text_type);
            hitpoints = itemView.findViewById(R.id.text_hitpoints);
            location = itemView.findViewById(R.id.text_location);
        }

    }
}
