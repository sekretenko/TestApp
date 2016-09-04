package activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.test.sekretenko.testapp.R;
import java.util.ArrayList;

import adapters.TestAdapter;
import api.TestApi;
import api.models.TestItem;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Создаем и настраиваем layout manager
        recyclerView = (RecyclerView) findViewById(R.id.test_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<TestItem> data = null;

        //Progress bar
        View progressBarView = findViewById(R.id.progress);

        if(savedInstanceState != null) {
            data = savedInstanceState.getParcelableArrayList("TestData");
        }

        progressBarView.setVisibility(View.VISIBLE);
        if (data == null) {
            //Получаем список тестовых айтемов и передаем их в адаптер
            TestApi api = new TestApi();
            api.getItems()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(testResponse -> {
                        //Создаем адаптер и передаем его в recycler view
                        adapter = new TestAdapter((ArrayList<TestItem>) testResponse.body, this);
                        recyclerView.setAdapter(adapter);
                        progressBarView.setVisibility(View.GONE);
                    },throwable -> {
                        throwable.printStackTrace();
                        progressBarView.setVisibility(View.GONE);
                    });
        } else {
            adapter = new TestAdapter(data, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("TestData",  adapter.getData());
        super.onSaveInstanceState(outState);
    }
}
