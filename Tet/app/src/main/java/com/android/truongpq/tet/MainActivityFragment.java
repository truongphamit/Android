package com.android.truongpq.tet;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.truongpq.tet.adapters.TitleAdapter;
import com.android.truongpq.tet.daos.TitleDAO;
import com.android.truongpq.tet.models.Title;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment {
    private View view;
    private ListView listView;
    private TitleAdapter adapter;
    private TitleDAO titleDAO;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        titleDAO = new TitleDAO(getActivity());
        final ArrayList<Title> titles = titleDAO.getList();
        adapter = new TitleAdapter(getActivity(),  titles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListSMSActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, titles.get(position).getId()+"");
                intent.putExtra("title", titles.get(position).getTitle() + "");
                startActivity(intent);
            }
        });
        return view;
    }
}
