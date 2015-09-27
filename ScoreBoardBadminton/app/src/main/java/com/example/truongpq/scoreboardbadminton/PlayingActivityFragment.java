package com.example.truongpq.scoreboardbadminton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayingActivityFragment extends Fragment {

    View rootView;
    private TextView score_left_text, score_right_text;
    private TextView player1_text, player2_text;
    private TextView set_left_text, set_right_text;
    private PlayerSet player1, player2;
    private Set set;

    public PlayingActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_playing, container, false);

        setNamePlayer();
        addScore();

        return rootView;
    }

    private void setNamePlayer() {
        Intent intent = getActivity().getIntent();
        String player1_name = intent.getStringExtra("player1_name");
        String player2_name = intent.getStringExtra("player2_name");
        player1 = new PlayerSet(player1_name);
        player2 = new PlayerSet(player2_name);
        set = new Set(player1, player2);
        player1_text = (TextView) rootView.findViewById(R.id.player1_text);
        player2_text = (TextView) rootView.findViewById(R.id.player2_text);
        if (player1_name.equals("") == false) {
            player1_text.setText(player1_name);
        }
        if (player2_name.equals("") == false) {
            player2_text.setText(player2_name);
        }
    }

    private void add(TextView textView, PlayerSet playerSet) {
        playerSet.addScore();
        textView.setText(playerSet.getScore() + "");
    }

    private void setTitle() {
        TextView set_text = (TextView) rootView.findViewById(R.id.set_text);
        set_text.setText("SET " + (player1.getWinSet() + player2.getWinSet() + 1));
    }

    private void newSet() {
        score_left_text = (TextView) rootView.findViewById(R.id.score_left_text);
        score_right_text = (TextView) rootView.findViewById(R.id.score_right_text);
        player1.setScore(0);
        player2.setScore(0);
        score_left_text.setText("00");
        score_right_text.setText("00");

    }

    private void checkWinSet() {
        if (set.checkWinSet() == 1) {
            //Log.d("TAG", player1.getName() + " win");
            player1.addWinSet();
            showDialog(player1.getName() + " is winner set " + (player1.getWinSet() + player2.getWinSet()));
            newSet();
            set_left_text = (TextView) rootView.findViewById(R.id.set_left_text);
            set_left_text.setText(player1.getWinSet() + "");
            setTitle();

        }

        if (set.checkWinSet() == 2) {
            //Log.d("TAG", player2.getName() + " win");
            player2.addWinSet();
            showDialog(player2.getName() + " is winner set " + (player1.getWinSet() + player2.getWinSet()));
            newSet();
            set_right_text = (TextView) rootView.findViewById(R.id.set_right_text);
            set_right_text.setText(player2.getWinSet() + "");
            setTitle();
        }
    }

    public void showDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Win");
        alertDialog.setMessage(message);
        alertDialog.setButton("Continue", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getActivity(), "Continue.............", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

    private void addScore() {
        score_left_text = (TextView) rootView.findViewById(R.id.score_left_text);
        score_right_text = (TextView) rootView.findViewById(R.id.score_right_text);
        score_left_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(score_left_text, player1);
                checkWinSet();
            }
        });

        score_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(score_right_text, player2);
                checkWinSet();
            }
        });
    }

}
