package com.task.blogs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.task.blogs.R;
import com.task.blogs.model.postmodel;

import java.util.List;


public class postadapter extends RecyclerView.Adapter<postadapter.MyHolder>  {

    Context context;
    List<postmodel> postmodellist ;

    public postadapter(Context context, List<postmodel> postadapter){
        this.context = context;
        this.postmodellist = postmodellist;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.home_post , parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String title = postmodellist.get(position).getPTitle();
        String description = postmodellist.get(position).getPDescription();
        String image = postmodellist.get(position).getpImage();
        holder.postTitle.setText(title);
        holder.postDiscription.setText(description);


        Glide.with(context).load(image).into(holder.postImages);

           }

    @Override
    public int getItemCount() {
        return postmodellist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

         ImageView postImages;
         TextView postTitle , postDiscription ;
         public MyHolder(@NonNull View itemView){

             super(itemView);

             postImages = itemView.findViewById(R.id.postimage);
             postTitle= itemView.findViewById(R.id.postTitle);
             postDiscription = itemView.findViewById(R.id.postDiscription);

        }

    }
}
