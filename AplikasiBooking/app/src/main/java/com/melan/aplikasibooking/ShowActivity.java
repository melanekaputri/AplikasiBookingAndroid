package com.melan.aplikasibooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();

    TextView IdBkg, NamaKonsumenBkg, JenisKelaminBkg, NamaMotorBkg,KeluhanBkg, NoPolisiBkg,
             NoHpBkg, EmailBkg, AlamatBkg, TanggalBkg, JamBkg;

    Button backBtn, editBtn, deleteBtn;

    String url_delete_bkg = "http://192.168.43.166/booking/public/api/booking/destroy";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID = "id";

    String bkg_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        IdBkg = (TextView) findViewById(R.id.idValue);
        NamaKonsumenBkg = (TextView) findViewById(R.id.nama_konsumenValue);
        JenisKelaminBkg = (TextView) findViewById(R.id.jenis_kelaminValue);
        NamaMotorBkg = (TextView) findViewById(R.id.nama_motorValue);
        KeluhanBkg = (TextView) findViewById(R.id.keluhanValue);
        NoPolisiBkg = (TextView) findViewById(R.id.no_polisiValue);
        NoHpBkg = (TextView) findViewById(R.id.no_hpValue);
        EmailBkg = (TextView) findViewById(R.id.emailValue);
        AlamatBkg = (TextView) findViewById(R.id.alamatValue);
        TanggalBkg = (TextView) findViewById(R.id.tanggal_bookingValue);
        JamBkg = (TextView) findViewById(R.id.jam_bookingValue);

        backBtn =(Button) findViewById(R.id.btn_back);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        editBtn = (Button)findViewById(R.id.btn_edit);

        Bundle b = getIntent().getExtras();
        String isi_id = b.getString("id_bkg");
        String isi_nama_konsumen = b.getString("nama_konsumen_bkg");
        String isi_jenis_kelamin = b.getString("jenis_kelamin_bkg");
        String isi_nama_motor = b.getString("nama_motor_bkg");
        String isi_keluhan = b.getString("keluhan_bkg");
        String isi_no_polisi = b.getString("no_polisi_bkg");
        String isi_no_hp = b.getString("no_hp_bkg");
        String isi_email = b.getString("email_bkg");
        String isi_alamat = b.getString("alamat_bkg");
        String isi_tanggal_booking = b.getString("tanggal_bkg");
        String isi_jam_booking = b.getString("jam_bkg");

        IdBkg.setText(isi_id);
        NamaKonsumenBkg.setText(isi_nama_konsumen);
        JenisKelaminBkg.setText(isi_jenis_kelamin);
        NamaMotorBkg.setText(isi_nama_motor);
        KeluhanBkg.setText(isi_keluhan);
        NoPolisiBkg.setText(isi_no_polisi);
        NoHpBkg.setText(isi_no_hp);
        EmailBkg.setText(isi_email);
        AlamatBkg.setText(isi_alamat);
        TanggalBkg.setText(isi_tanggal_booking);
        JamBkg.setText(isi_jam_booking);

        bkg_id = isi_id;

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = null;
                i = new Intent(ShowActivity.this, EditActivity.class);
                Bundle b = new Bundle();
                b.putString("id_bkg", IdBkg.getText().toString());
                b.putString("nama_konsumen_bkg", NamaKonsumenBkg.getText().toString());
                b.putString("jenis_kelamin_bkg", JenisKelaminBkg.getText().toString());
                b.putString("nama_motor_bkg", NamaMotorBkg.getText().toString());
                b.putString("keluhan_bkg", KeluhanBkg.getText().toString());
                b.putString("no_polisi_bkg", NoPolisiBkg.getText().toString());
                b.putString("no_hp_bkg", NoHpBkg.getText().toString());
                b.putString("email_bkg", EmailBkg.getText().toString());
                b.putString("alamat_bkg", AlamatBkg.getText().toString());
                b.putString("tanggal_bkg", TanggalBkg.getText().toString());
                b.putString("jam_bkg", JamBkg.getText().toString());
                i.putExtras(b);
                startActivity(i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteBkgTask().execute();
            }
        });
    }
    class DeleteBkgTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_ID, bkg_id));

            try {
                JSONObject json = jParser.makeHttpRequest(url_delete_bkg,"GET",parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success==1)
                {
                    return "OK";
                }

                else{
                    return "FAIL";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();

            if (result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(ShowActivity.this,"Unable to connect to server,please check your internet connection!",Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(ShowActivity.this,"Fail... try again",Toast.LENGTH_LONG).show();
            }

            else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(ShowActivity.this,"Data Booking successfully deleted.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
