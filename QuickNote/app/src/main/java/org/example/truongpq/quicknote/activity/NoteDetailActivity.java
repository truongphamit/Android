package org.example.truongpq.quicknote.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.example.truongpq.quicknote.R;
import org.example.truongpq.quicknote.dao.NoteDAO;
import org.example.truongpq.quicknote.model.Note;

public class NoteDetailActivity extends ActionBarActivity {

    private EditText edit_title;
    private EditText edit_note;

    private NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = this.getIntent();
        String idNote = intent.getStringExtra(Intent.EXTRA_TEXT);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            edit_title = (EditText) findViewById(R.id.edit_title);
            edit_note = (EditText) findViewById(R.id.edit_note);

            noteDAO = new NoteDAO(this);
            Note note = noteDAO.findById(Integer.parseInt(idNote));

            note.setTitle(edit_title.getText() + "");
            note.setNote(edit_note.getText() + "");
            new NoteDAO(this).update(note);

            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);

            return true;
        }

        if (id == R.id.action_delete) {
            new NoteDAO(this).delete(Integer.parseInt(idNote));
            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        }

        return super.onOptionsItemSelected(item);
    }
}
