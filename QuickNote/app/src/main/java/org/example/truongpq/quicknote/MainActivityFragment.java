package org.example.truongpq.quicknote;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.example.truongpq.quicknote.model.Note;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    FloatingActionButton btn_add;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        final ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("Welcome", "Welcome to QuickNote"));
        notes.add(new Note("Welcome", "Welcome to QuickNote"));
        notes.add(new Note("Welcome", "Welcome to QuickNote"));

        CustomAdapter adapter = new CustomAdapter(getActivity(), notes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NoteDetailActivity.class).putExtra("note", notes.get(position));
                startActivity(intent);
            }
        });

        btn_add = (FloatingActionButton) view.findViewById(R.id.add_btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
