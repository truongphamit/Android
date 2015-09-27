package org.example.truongpq.quicknote.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.example.truongpq.quicknote.R;
import org.example.truongpq.quicknote.dao.NoteDAO;
import org.example.truongpq.quicknote.model.Note;

/**
 * A placeholder fragment containing a simple view.
 */
public class NoteDetailFragment extends Fragment {

    private EditText edit_title;
    private EditText edit_note;

    public NoteDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_note_detail, container, false);

        edit_title = (EditText) view.findViewById(R.id.edit_title);
        edit_note = (EditText) view.findViewById(R.id.edit_note);

        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra(Intent.EXTRA_TEXT);
        Note note = new NoteDAO(getActivity()).findById(Integer.parseInt(id));


        if (note != null) {
            edit_title.setText(note.getTitle());
            edit_note.setText(note.getNote());
        }

        return view;
    }

}
