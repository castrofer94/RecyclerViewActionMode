package com.example.rcksuporte05.recycleractionmode.Activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.rcksuporte05.recycleractionmode.Adapter.Adapter;
import com.example.rcksuporte05.recycleractionmode.Interfaces.ListenerRecycler;
import com.example.rcksuporte05.recycleractionmode.Model.ObjetoLista;
import com.example.rcksuporte05.recycleractionmode.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListenerRecycler {

    @BindView(R.id.listaTeste)
    RecyclerView listaTeste;

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private Adapter adapter;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listaTeste.setLayoutManager(new LinearLayoutManager(this));
        listaTeste.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        preencheLista();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                preencheLista();
            }
        });
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
        adapter = new Adapter(lista, this, this);
        listaTeste.setAdapter(adapter);
        swipe.setRefreshing(false);
    }

    @Override
    public void OnRecyclerClickListener(int position) {
        if (adapter.getItemSelectedCount() > 0)
            enableActionMode(position);
        else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(adapter.getItem(position).getId() + "\n" + adapter.getItem(position).getNome() + "\n"
                    + adapter.getItem(position).getDescricao() + "\n" + adapter.getItem(position).getOutraDescricao());
            alert.setTitle(String.valueOf(adapter.getItem(position).getId()));
            alert.setNeutralButton("OK", null);
            alert.show();
        }
    }

    @Override
    public void OnRecyclerLongClickListener(int positon) {
        enableActionMode(positon);
    }

    private void enableActionMode(int position) {
        if (actionMode == null)
            actionMode = startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
                    return true;
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
