package com.slaptrap.poetist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4/15/2017.
 */

public class SongAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public SongAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    public void add(Songs object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        SongHolder songHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            songHolder = new SongHolder();
            songHolder.tx_author = (TextView) row.findViewById(R.id.tx_author);
            songHolder.tx_title = (TextView) row.findViewById(R.id.tx_title);
            songHolder.tx_lines = (TextView) row.findViewById(R.id.tx_lines);
            row.setTag(songHolder);
        } else {

            songHolder = (SongHolder) row.getTag();
        }
        Songs songs = (Songs) this.getItem(position);
        songHolder.tx_author.setText(songs.getAuthor());
        songHolder.tx_title.setText(songs.getTitle());
        songHolder.tx_lines.setText(songs.getLines());
        return row;
    }

    static class SongHolder {
        TextView tx_author,tx_title,tx_lines;
    }
}
