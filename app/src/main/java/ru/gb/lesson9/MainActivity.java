package ru.gb.lesson9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements PopupMenuItemClicker, CityDialog.CityDialogController {

    private RecyclerView recyclerView;
    private CityAdapter adapter = new CityAdapter();
    private Repo repo = InMemoryCityRepo.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter.setPopupMenuItemClicker(this);

        adapter.setCities(repo.getAll());
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));






    }


    @Override
    public void onBackPressed() {
        // Показать диалог
        // super.onBackPressed();
    }

    // вызвать из диалога
    public void quit()
    {
        finish();
    }

    @Override
    public void click(int command, City city, int position) {
        switch (command)
        {
            case R.id.context_delete:
                repo.delete(city);
                adapter.delete(repo.getAll(), position);
                return;

            case R.id.context_modify:
                CityDialog.getInstance(city, position)
                        .show(getSupportFragmentManager(),
                                CityDialog.CITY
                        );
                return;
        }
    }

    @Override
    public void update(City city, int position) {
        repo.update(city);
        adapter.modify(repo.getAll(), position);
    }

    @Override
    public void create(String name, int population) {
        int pos = repo.getAll().size();
        repo.create(name, population);
        adapter.create(repo.getAll(),pos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.main_add:
                CityDialog.getInstance(null, -1)
                        .show(getSupportFragmentManager(),
                                CityDialog.CITY
                        );

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}