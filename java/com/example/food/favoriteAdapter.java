package com.example.food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class favoriteAdapter extends RecyclerView.Adapter<favoriteAdapter.favoriteViewHolder> {

    Context mcontext;
    ArrayList<FavoriteData> list;

    public favoriteAdapter(Context mcontext, ArrayList<FavoriteData> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public favoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item,parent,false);
        return new favoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull favoriteViewHolder holder, int position) {
        FavoriteData Data=list.get(position);
        holder.favoriteName.setText(Data.getItemName());
        holder.favoriteURL.setText(Data.geturl());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,FavoritesWebView.class);
                intent.putExtra("URL",list.get(holder.getAdapterPosition()).geturl());
                mcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class favoriteViewHolder extends RecyclerView.ViewHolder {

         TextView favoriteName,favoriteURL;
         CardView mCardView;

        public favoriteViewHolder( View itemView) {
            super(itemView);
            favoriteName=itemView.findViewById(R.id.favorite_name);
            favoriteURL=itemView.findViewById(R.id.favorite_URL);
            mCardView=itemView.findViewById(R.id.favoriteCV);
        }
    }
}
