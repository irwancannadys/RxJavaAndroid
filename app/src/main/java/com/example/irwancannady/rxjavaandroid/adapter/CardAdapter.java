package com.example.irwancannady.rxjavaandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.irwancannady.rxjavaandroid.R;
import com.example.irwancannady.rxjavaandroid.model.Github;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adhitiahidayat on 10/14/16.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Holder> {

    List<Github> items;

    public CardAdapter(){
        super();
        items = new ArrayList<Github>();
    }

    public void addData(Github github){
        items.add(github);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card,parent, false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Github github = items.get(position);
        holder.login.setText(github.getLogin());
        holder.repos.setText("Repos : " + github.getPublic_repos());
        holder.blog.setText("Blog : " + github.getBlog());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        public TextView login;
        public TextView repos;
        public TextView blog;


        public Holder(View itemView) {
            super(itemView);

            login = (TextView) itemView.findViewById(R.id.login);
            repos = (TextView) itemView.findViewById(R.id.repos);
            blog = (TextView) itemView.findViewById(R.id.blog);
        }
    }
}
