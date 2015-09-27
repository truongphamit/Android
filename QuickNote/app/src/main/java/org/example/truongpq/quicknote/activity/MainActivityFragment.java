package org.example.truongpq.quicknote.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.example.truongpq.quicknote.R;
import org.example.truongpq.quicknote.dao.NoteDAO;
import org.example.truongpq.quicknote.model.Note;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View view;
    private FloatingActionButton btn_add;
    private ListView listView = null;

    private NoteDAO noteDAO = null;
    private ArrayList<Note> notes = null;
    private CustomAdapter adapter = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        setListView();
        setAddButton();
        return view;
    }

    public void setListView() {
        listView = (ListView) view.findViewById(R.id.listView);

        noteDAO = new NoteDAO(getActivity());
        notes = noteDAO.getList();
        adapter = new CustomAdapter(getActivity(), notes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NoteDetailActivity.class).putExtra(Intent.EXTRA_TEXT, notes.get(position).getId() + "");
                startActivity(intent);
            }
        });

    }

    public void setAddButton() {
        btn_add = (FloatingActionButton) view.findViewById(R.id.add_btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }

}
