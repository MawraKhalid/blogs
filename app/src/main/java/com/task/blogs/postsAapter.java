package com.task.blogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class postsAapter extends RecyclerView.Adapter<postsAapter.ViewHolder> {

   private final List<posts> postsList;

   public postsAapter(List<posts> postsList){
       this.postsList = postsList;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.list_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       holder.tvTitle.setText(postsList.get(position).getTitle());
       holder.tvBody.setText(postsList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder {

       TextView tvTitle;
       TextView tvBody;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTitle = itemView.findViewById(R.id.tvBody);

        }

    }
}
