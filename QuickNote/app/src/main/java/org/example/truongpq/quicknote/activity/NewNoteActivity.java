package org.example.truongpq.quicknote.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.example.truongpq.quicknote.R;
import org.example.truongpq.quicknote.dao.NoteDAO;
import org.example.truongpq.quicknote.model.Note;

public class NewNoteActivity extends ActionBarActivity {

    private EditText edit_note;
    private EditText edit_title;

    private NoteDAO noteDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {

            noteDAO = new NoteDAO(this);
            edit_title = (EditText) findViewById(R.id.edit_title);
            edit_note = (EditText) findViewById(R.id.edit_note);
            Note note = new Note(edit_title.getText() + "", edit_note.getText() + "");
            noteDAO.insert(note);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
