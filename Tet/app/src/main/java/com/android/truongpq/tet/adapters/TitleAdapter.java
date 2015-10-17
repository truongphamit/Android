package com.android.truongpq.tet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.truongpq.tet.R;
import com.android.truongpq.tet.models.Title;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by truongpq on 10/6/15.
 */
public class TitleAdapter extends ArrayAdapter<Title> {
    private Context context;
    private ArrayList<Title> titles;

    public TitleAdapter(Context context, ArrayList<Title> titles) {
       super(context, R.layout.list_title, titles);
        this.context = context;
        this.titles = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_title, parent, false);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(titles.get(position).getTitle());
        return view;
    }
}
