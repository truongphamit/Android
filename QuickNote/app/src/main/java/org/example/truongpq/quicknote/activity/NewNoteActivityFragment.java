package org.example.truongpq.quicknote.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.example.truongpq.quicknote.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewNoteActivityFragment extends Fragment {

    private View view;

    public NewNoteActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_new_note, container, false);
        return view;
    }
}
