package com.example.our_kart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.our_kart.Activities.DetailActivity;
import com.example.our_kart.Domain.ApiDataGetterSetter;
import com.example.our_kart.databinding.MainRecyclerItemviewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

  private ArrayList<ApiDataGetterSetter> apiDataArrayList;
    LayoutInflater inflater;
    MainRecyclerItemviewBinding mainRecyclerItemviewBinding;
    Context context;

    public MainRecyclerAdapter(Context context, ArrayList<ApiDataGetterSetter> apiDataArrayList) {

        this.apiDataArrayList = apiDataArrayList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainRecyclerItemviewBinding = MainRecyclerItemviewBinding.inflate(inflater);
        return new ViewHolder(mainRecyclerItemviewBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        ApiDataGetterSetter dataArraylist = apiDataArrayList.get(position);
        mainRecyclerItemviewBinding.itemViewPrice.setText(dataArraylist.getPrice());
        mainRecyclerItemviewBinding.itemViewTitle.setText(dataArraylist.getTitle());
        mainRecyclerItemviewBinding.itemViewReview.setText(dataArraylist.getReview());
        mainRecyclerItemviewBinding.itmeViewRating.setText(dataArraylist.getRating());
        Picasso.get().load(dataArraylist.getImage()).into(mainRecyclerItemviewBinding.itemViewImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detailImage", dataArraylist.getImage());
                intent.putExtra("detailTitle", dataArraylist.getTitle());
                intent.putExtra("detailPrice", dataArraylist.getPrice());
                intent.putExtra("detailRating",dataArraylist.getRating());
                intent.putExtra("detailReview",dataArraylist.getReview());
                intent.putExtra("detailDescription",dataArraylist.getDescription());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return apiDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
