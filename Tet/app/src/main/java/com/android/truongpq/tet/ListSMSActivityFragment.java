package com.android.truongpq.tet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.truongpq.tet.adapters.SmsAdapter;
import com.android.truongpq.tet.daos.SmsDAO;
import com.android.truongpq.tet.models.Sms;

import java.util.ArrayList;

public class ListSMSActivityFragment extends Fragment {

    ListView lv_sms;
    SmsDAO smsDAO;
    SmsAdapter adapter;

    public ListSMSActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sms, container, false);
        lv_sms = (ListView) view.findViewById(R.id.lv_sms);
        smsDAO = new SmsDAO(getActivity());
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra(Intent.EXTRA_TEXT);

        if (id.equals("10") != true) {
            final ArrayList<Sms> smses = smsDAO.findByType(Integer.parseInt(id));
            adapter = new SmsAdapter(getActivity(), smses);
            lv_sms.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                    if (smses.get(position).getBookmark() != 1) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Thêm vào yêu thích")
                                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        smsDAO.addBookmark(smses.get(position).getId());
                                        Sms sms = smses.get(position);
                                        sms.setBookmark(1);
                                        smses.set(position, sms);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .show();
                    } else {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Bỏ yêu thích")
                                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("Bỏ yêu thích", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Sms sms = smses.get(position);
                                        smsDAO.removeBookmark(sms.getId());
                                        sms.setBookmark(0);
                                        smses.set(position, sms);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .show();
                    }
                    return true;
                }
            });

            lv_sms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showDialogIntent(Html.fromHtml(smses.get(position).getContent()));
                }
            });

        } else {
            final ArrayList<Sms> smsBookmark = smsDAO.findByBoorkmark();
            adapter = new SmsAdapter(getActivity(), smsBookmark);
            lv_sms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showDialogIntent(Html.fromHtml(smsBookmark.get(position).getContent()));
                }
            });

            lv_sms.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Bỏ yêu thích")
                            .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("Bỏ yêu thích", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    smsDAO.removeBookmark(smsBookmark.get(position).getId());
                                    smsBookmark.remove(smsBookmark.get(position));
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .show();
                    return true;
                }
            });
        }

        lv_sms.setAdapter(adapter);
        getActivity().setTitle(intent.getStringExtra("title"));
        return view;
    }

    @SuppressWarnings("deprecation")
    public void copyToClipboard(Spanned text) {
        ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(text);
        Toast.makeText(getActivity(), "Đã sao chép", Toast.LENGTH_SHORT).show();
    }

    public void shareIntent(Spanned text) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text.toString());
        startActivity(Intent.createChooser(shareIntent, "Send with"));
    }

    public void showDialogIntent(final Spanned text) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Tùy chọn")
                .setPositiveButton("Sao chép", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        copyToClipboard(text);
                    }
                })
                .setNegativeButton("Gửi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shareIntent(text);
                    }
                })
                .show();
    }

}
