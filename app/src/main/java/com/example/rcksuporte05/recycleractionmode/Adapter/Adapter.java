package com.example.rcksuporte05.recycleractionmode.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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
    private List<ObjetoLista> lista = new ArrayList<>();
    private ListenerRecycler listenerRecycler;
    private SparseBooleanArray itensSelecionados;
    private Context context;

    public Adapter(List<ObjetoLista> lista, ListenerRecycler listenerRecycler, Context context) {
        this.lista = lista;
        this.listenerRecycler = listenerRecycler;
        this.itensSelecionados = new SparseBooleanArray();
        this.context = context;
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

        holder.itemView
                .setBackgroundColor(itensSelecionados.get(position) ? Color.parseColor("#dfdfdf")
                        : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public int getItemSelectedCount() {
        return itensSelecionados.size();
    }

    public ObjetoLista getItem(int position) {
        return lista.get(position);
    }

    public void toggleSelection(int position) {
        if (itensSelecionados.get(position, false)) {
            itensSelecionados.delete(position);
        } else {
            itensSelecionados.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void clearSelections() {
        itensSelecionados.clear();
        notifyDataSetChanged();
    }

    public void removeItens(List<ObjetoLista> lista) {
        this.lista.removeAll(lista);
        notifyDataSetChanged();
    }

    public List<ObjetoLista> getItensSelecionados() {
        List<ObjetoLista> lista = new ArrayList<>();
        for (int i = 0; itensSelecionados.size() > i; i++) {
            lista.add(this.lista.get(itensSelecionados.keyAt(i)));
        }
        return lista;
    }
}
