package com.task.blogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.task.blogs.Adapter.postadapter;
import com.task.blogs.model.postmodel;

import org.jetbrains.annotations.NonNls;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Homepage extends AppCompatActivity {

    FirebaseAuth auth;
    androidx.recyclerview.widget.RecyclerView RecyclerView;
    postadapter postadapter;
    List<postmodel> postmodellist;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    postsAapter adapter;
    List<posts> postsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        RecyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(layoutManager);
        progressBar = findViewById(R.id.progressBar);
        adapter = new postsAapter(postsList);
        RecyclerView.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        postmodellist = new ArrayList<>();

            loadPost();
            fetchPosts();
        }
//fetch post API
    private void fetchPosts() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetrofitClient().getposts().enqueue(new Callback<List<posts>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<posts>> call, Response<List<posts>> response) {
                if (response.isSuccessful() && response.body() != null){
                    postsList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<posts>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Homepage.this,"Error :"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//posts
    private void loadPost() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                postmodellist.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    postmodel postmodel = ds.getValue(com.task.blogs.model.postmodel.class);
                    postmodellist.add(postmodel);
                    postadapter = new postadapter(Homepage.this, postmodellist);
                    RecyclerView.setAdapter(postadapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            auth.signOut();
            startActivity(new Intent(Homepage.this , MainActivity.class));

        }
        if(item.getItemId() == R.id.action_add_post){
            startActivity(new Intent(Homepage.this , Addpost.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}