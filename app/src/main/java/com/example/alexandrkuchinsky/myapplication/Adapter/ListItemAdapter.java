package com.example.alexandrkuchinsky.myapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexandrkuchinsky.myapplication.Model.ToDo;
import com.example.alexandrkuchinsky.myapplication.R;
import com.example.alexandrkuchinsky.myapplication.lab1;

import java.util.List;

/**
 * Created by Alexandr Kuchinsky on 18.02.2018.
 */

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

    ItemClickListener itemClickListener;
    TextView item_title, item_description;
    public ListItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
        item_title = (TextView)itemView.findViewById(R.id.item_title);
        item_description = (TextView)itemView.findViewById(R.id.item_description);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
itemClickListener,onClick(v, getAdapterPosition(), false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
contextMenu.setHeaderTitle("Выберите действие");
contextMenu.add(0,0,getAdapterPosition(),"Удалить");
    }
}


public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder>{
    lab1 lab1;
    List<ToDo> todoList;

    public ListItemAdapter(com.example.alexandrkuchinsky.myapplication.lab1 lab1, List<ToDo> todoList) {
        this.lab1 = lab1;
        this.todoList = todoList;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(lab1.getBaseContext());
        View view = inflater.inflate(R.layout.fl11,parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
