package com.example.truongpq.scoreboardbadminton;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewGameActivityFragment extends Fragment {

    public NewGameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);

        final EditText player1_edText = (EditText) rootView.findViewById(R.id.player1_edText);
        final EditText player2_edText = (EditText) rootView.findViewById(R.id.player2_edText);

        Button start_btn = (Button) rootView.findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayingActivity.class);
                intent.putExtra("player1_name", player1_edText.getText().toString());
                intent.putExtra("player2_name", player2_edText.getText().toString());
                startActivity(intent);
            }
        });
        return rootView;
    }
}
