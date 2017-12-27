package com.example.rcksuporte05.recycleractionmode.Frag;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rcksuporte05.recycleractionmode.Adapter.Adapter;
import com.example.rcksuporte05.recycleractionmode.Interfaces.ListenerRecycler;
import com.example.rcksuporte05.recycleractionmode.Model.ObjetoLista;
import com.example.rcksuporte05.recycleractionmode.R;
import com.example.rcksuporte05.recycleractionmode.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RCKSUPORTE05 on 26/12/2017.
 */

public class Frag1 extends Fragment implements ListenerRecycler {

    @BindView(R.id.listaFrag1)
    RecyclerView recyclerView;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private Adapter adapter;
    private ActionMode actionMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        preencheLista();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                preencheLista();
            }
        });

        return view;
    }

    public void preencheLista() {
        swipe.setRefreshing(true);
        List<ObjetoLista> lista = new ArrayList<>();
        int i = 0;
        while (i < 1000) {
            ObjetoLista objetoLista = new ObjetoLista(i + 1, "Nome do Caboco", "Descrição", "Outra Descrição");
            lista.add(objetoLista);
            i++;
        }
        adapter = new Adapter(lista, this, getActivity());
        recyclerView.setAdapter(adapter);
        swipe.setRefreshing(false);
    }

    @Override
    public void OnRecyclerClickListener(int position) {
        if (adapter.getItemSelectedCount() > 0)
            enableActionMode(position);
    }

    @Override
    public void OnRecyclerLongClickListener(int positon) {
        enableActionMode(positon);
    }

    public void enableActionMode(int position) {
        if (actionMode == null)
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.excluir:
                            adapter.removeItens(adapter.getItensSelecionados());
                            actionMode.finish();
                            adapter.clearSelections();
                            break;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    actionMode.finish();
                    adapter.clearSelections();
                    actionMode = null;
                }
            });
        toggleSelection(position);
    }

    public void toggleSelection(int position) {
        adapter.toggleSelection(position);
        if (adapter.getItemSelectedCount() == 0) {
            actionMode.finish();
            actionMode = null;
        } else {
            actionMode.setTitle(String.valueOf(adapter.getItemSelectedCount()));
            actionMode.invalidate();
        }
    }
}
