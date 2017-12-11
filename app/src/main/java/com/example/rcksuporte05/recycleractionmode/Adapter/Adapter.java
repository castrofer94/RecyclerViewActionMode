package com.example.rcksuporte05.recycleractionmode.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rcksuporte05.recycleractionmode.Adapter.ViewHolder.ViewHolder;
import com.example.rcksuporte05.recycleractionmode.Interfaces.ListenerRecycler;
import com.example.rcksuporte05.recycleractionmode.Model.ObjetoLista;
import com.example.rcksuporte05.recycleractionmode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCKSUPORTE05 on 11/12/2017.
 */

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    List<ObjetoLista> lista = new ArrayList<>();
    private ListenerRecycler listenerRecycler;

    public Adapter(List<ObjetoLista> lista, ListenerRecycler listenerRecycler   ) {
        this.lista = lista;
        this.listenerRecycler = listenerRecycler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder, parent, false);

        return new ViewHolder(view, listenerRecycler);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(lista.get(position).getId()));
        holder.nome.setText(lista.get(position).getNome());
        holder.descricao.setText(lista.get(position).getDescricao());
        holder.outraDescricao.setText(lista.get(position).getOutraDescricao());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public ObjetoLista getItem(int position) {
        return lista.get(position);
    }
}
