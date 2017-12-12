package com.example.rcksuporte05.recycleractionmode.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rcksuporte05.recycleractionmode.Interfaces.ListenerRecycler;
import com.example.rcksuporte05.recycleractionmode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RCKSUPORTE05 on 11/12/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.id)
    public TextView id;
    @BindView(R.id.nome)
    public TextView nome;
    @BindView(R.id.descricao)
    public TextView descricao;
    @BindView(R.id.outraDescricao)
    public TextView outraDescricao;

    public ViewHolder(View itemView, final ListenerRecycler listenerRecycler) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerRecycler.OnRecyclerClickListener(getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listenerRecycler.OnRecyclerLongClickListener(getAdapterPosition());
                return false;
            }
        });
    }
}