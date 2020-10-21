package com.melan.aplikasibooking;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Booking> data_bkg = new ArrayList<Booking>();
    private  static LayoutInflater inflater = null;

    public BookingAdapter(Activity a, ArrayList<Booking> d)
    {
        activity = a;
        data_bkg = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data_bkg.size();
    }

    @Override
    public Object getItem(int position) {
        return data_bkg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView==null)
            view = inflater.inflate(R.layout.list_item_bkg,null);
        TextView id_bkg = (TextView) view.findViewById(R.id.id_bkg);
        TextView nama_konsumen_bkg = (TextView) view.findViewById(R.id.nama_konsumen_bkg);
        TextView jenis_kelamin_bkg = (TextView) view.findViewById(R.id.jenis_kelamin_bkg);
        TextView nama_motor_bkg = (TextView) view.findViewById(R.id.nama_motor_bkg);
        TextView keluhan_bkg = (TextView) view.findViewById(R.id.keluhan_bkg);
        TextView no_polisi_bkg = (TextView) view.findViewById(R.id.no_polisi_bkg);
        TextView no_hp_bkg = (TextView) view.findViewById(R.id.no_hp_bkg);
        TextView email_bkg = (TextView) view.findViewById(R.id.email_bkg);
        TextView alamat_bkg = (TextView) view.findViewById(R.id.alamat_bkg);
        TextView tanggal_bkg = (TextView) view.findViewById(R.id.tanggal_bkg);
        TextView jam_bkg = (TextView) view.findViewById(R.id.jam_bkg);


        Booking list_bkg = data_bkg.get(position);
        id_bkg.setText(list_bkg.getId());
        nama_konsumen_bkg.setText(list_bkg.getNama_konsumen());
        jenis_kelamin_bkg.setText(list_bkg.getJenis_kelamin());
        nama_motor_bkg.setText(list_bkg.getNama_motor());
        keluhan_bkg.setText(list_bkg.getKeluhan());
        no_polisi_bkg.setText(list_bkg.getNo_polisi());
        no_hp_bkg.setText(list_bkg.getNo_hp());
        email_bkg.setText(list_bkg.getEmail());
        alamat_bkg.setText(list_bkg.getAlamat());
        tanggal_bkg.setText(list_bkg.getTanggal_booking());
        jam_bkg.setText(list_bkg.getJam_booking());

        return view;
    }
}
