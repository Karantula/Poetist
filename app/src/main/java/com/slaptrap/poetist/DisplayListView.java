package com.slaptrap.poetist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    SongAdapter songAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView) findViewById(R.id.listview);
        songAdapter = new SongAdapter(this,R.layout.row_layout);
        listView.setAdapter(songAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonArray = new JSONArray(json_string);
            String author, title, lines;
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject JO = jsonArray.getJSONObject(i);
                author = JO.getString("author");
                title = JO.getString("title");
                lines = JO.getString("lines");
                Songs songs = new Songs(author, title, lines);
                songAdapter.add(songs);
                Log.i("author", author);
                Log.i("title", title);
                Log.i("lines", lines);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
