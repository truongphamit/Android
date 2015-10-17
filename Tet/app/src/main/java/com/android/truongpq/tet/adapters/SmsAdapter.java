package com.android.truongpq.tet.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.truongpq.tet.R;
import com.android.truongpq.tet.models.Sms;
import com.android.truongpq.tet.models.Title;

import java.util.ArrayList;

/**
 * Created by truongpq on 10/8/15.
 */
public class SmsAdapter extends ArrayAdapter<Sms> {
    private Context context;
    private ArrayList<Sms> smses;

    public SmsAdapter(Context context, ArrayList<Sms> smses) {
        super(context, R.layout.list_sms, smses);
        this.context = context;
        this.smses = smses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_sms, parent, false);
        TextView tv_sms = (TextView) view.findViewById(R.id.tv_sms);
        tv_sms.setText(Html.fromHtml(smses.get(position).getContent()));
        if (smses.get(position).getBookmark() == 1) {
            ImageView img_bookmark = (ImageView) view.findViewById(R.id.img_bookmark);
            img_bookmark.setImageResource(R.drawable.ic_star);
        }
        return view;
    }
}
