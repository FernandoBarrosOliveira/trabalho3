package com.example.fernando.trabalho3;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fernando.trabalho3.adapter.ListaFilmesAdapter;
import com.example.fernando.trabalho3.asynctask.FilmeAsyncTask;
import com.example.fernando.trabalho3.dao.FilmeDAO;
import com.example.fernando.trabalho3.model.Filme;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Opções");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_filme_populares:
                        new FilmeAsyncTask(MainActivity.this).execute("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=4895a1e4cec2aeb113fc7178193f3920");
                        break;
                    case R.id.menu_filme_acessados:
                        List<Filme> listaFilme = PegaFilmes();
                        if (listaFilme == null || listaFilme.size()<1){
                            Toast.makeText(MainActivity.this,"Nenhum filme cadastrado",Toast.LENGTH_LONG).show();
                        }
                        ListaFilmesAdapter listaFilmesAdapter = new ListaFilmesAdapter(listaFilme, MainActivity.this);
                        RecyclerView recyclerView = (RecyclerView) MainActivity.this.findViewById(R.id.recycler);
                        recyclerView.setAdapter(listaFilmesAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private List<Filme> PegaFilmes() {
        FilmeDAO filmeDAO = new FilmeDAO(this);
        return filmeDAO.listaFilme();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
