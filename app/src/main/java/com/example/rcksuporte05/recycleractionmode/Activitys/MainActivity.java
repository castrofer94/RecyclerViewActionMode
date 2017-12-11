package com.example.rcksuporte05.recycleractionmode.Activitys;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listaTeste.setLayoutManager(new LinearLayoutManager(this));
        listaTeste.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        preencheLista();
    }

    public void preencheLista() {
        List<ObjetoLista> lista = new ArrayList<>();
        int i = 0;
        while (i < 1000) {
            ObjetoLista objetoLista = new ObjetoLista(i + 1, "Nome do Caboco", "Descrição", "Outra Descrição");
            lista.add(objetoLista);
            i++;
        }
        adapter = new Adapter(lista, this);
        listaTeste.setAdapter(adapter);
    }

    @Override
    public void OnItemClickListener(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(adapter.getItem(position).getId() + "\n" + adapter.getItem(position).getNome() + "\n"
                + adapter.getItem(position).getDescricao() + "\n" + adapter.getItem(position).getOutraDescricao());
        alert.setTitle(String.valueOf(adapter.getItem(position).getId()));
        alert.setNeutralButton("OK", null);
        alert.show();
    }

    @Override
    public void OnItemLongClickListener(int positon) {

    }
}
