package me.arnaumas.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Content> contents = new ArrayList<>();
        contents.add(new Content(0));
        contents.add(new Content(1));
        contents.add(new Content(1));
        contents.add(new Content(2));
        contents.add(new Content(2));
        contents.add(new Content(3));
        contents.add(new Content(3));
        contents.add(new Content(2));
        contents.add(new Content(1));

        MultiElementAdapter adapter = new MultiElementAdapter(contents);
        recyclerView.setAdapter(adapter);
    }
}