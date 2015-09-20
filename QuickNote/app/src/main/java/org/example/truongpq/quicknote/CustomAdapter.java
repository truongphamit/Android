package org.example.truongpq.quicknote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.example.truongpq.quicknote.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by truongpq on 9/13/15.
 */
public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Note> notes;

    public CustomAdapter(Context context, ArrayList<Note> notes) {
        super(context, R.layout.note_detail, notes);
        this.context = context;
        this.notes = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.note_detail, null);
        } else {
            view = convertView;
        }

        TextView tvTittle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvNote = (TextView) view.findViewById(R.id.tvNote);
        tvTittle.setText(notes.get(position).getTitle());
        tvNote.setText(notes.get(position).getNote());

        return view;
    }
}
