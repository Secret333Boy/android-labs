package edu.kpi.android.labs;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<HistoryItem> historyItemList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = openFileInput("store.txt");

            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);

            String valuesString = new String(bytes, StandardCharsets.UTF_8);

            for (String row : valuesString.split("\n")) {
                try {
                    String[] valueArr = row.split(";");
                    HistoryItem historyItem = new HistoryItem(Integer.parseInt(valueArr[0]), valueArr[1]);
                    historyItemList.add(historyItem);
                } catch (Exception ignored) {}
            }

            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(historyItemList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}