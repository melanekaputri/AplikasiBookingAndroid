package com.melan.aplikasibooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    String url_create_bkg = "http://192.168.43.166/booking/public/api/booking/store";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_NAMA_KONSUMEN = "nama_konsumen";
    public static final String TAG_JENIS_KELAMIN = "jenis_kelamin";
    public static final String TAG_NAMA_MOTOR = "nama_motor";
    public static final String TAG_KELUHAN = "keluhan";
    public static final String TAG_NO_POLISI = "no_polisi";
    public static final String TAG_NO_HP = "no_hp";
    public static final String TAG_EMAIL = "email";
    private static final String TAG_ALAMAT = "alamat";
    private static final String TAG_TANGGAL_BOOKING = "tanggal_booking";
    private static final String TAG_JAM_BOOKING = "jam_booking";

    EditText EditNamaKonsumen, EditJenisKelamin, EditNamaMotor, EditKeluhan,
             EditNoPolisi, EditNoHp, EditEmail, EditAlamat, EditTanggalBooking, EditJamBooking;

    Button addBtn, backBtn;
    String namaKonsumenStr, jenisKelaminStr, namaMotorStr,keluhanStr,
           noPolisiStr, noHpStr, emailStr, alamatStr, tanggalBookingStr, jamBookingStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        EditNamaKonsumen = (EditText)findViewById(R.id.input_nama_konsumen);
        EditJenisKelamin = (EditText)findViewById(R.id.input_jenis_kelamin);
        EditNamaMotor = (EditText)findViewById(R.id.input_nama_motor);
        EditKeluhan = (EditText)findViewById(R.id.input_keluhan);
        EditNoPolisi = (EditText)findViewById(R.id.input_no_polisi);
        EditNoHp = (EditText)findViewById(R.id.input_no_hp);
        EditEmail = (EditText)findViewById(R.id.input_email);
        EditAlamat = (EditText)findViewById(R.id.input_alamat);
        EditTanggalBooking = (EditText)findViewById(R.id.input_tanggal_booking);
        EditJamBooking = (EditText)findViewById(R.id.input_jam_booking);

        addBtn = (Button) findViewById(R.id.button_add);
        backBtn = (Button) findViewById(R.id.button_back);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaKonsumenStr = EditNamaKonsumen.getText().toString();
                jenisKelaminStr = EditJenisKelamin.getText().toString();
                namaMotorStr = EditNamaMotor.getText().toString();
                keluhanStr = EditKeluhan.getText().toString();
                noPolisiStr = EditNoPolisi.getText().toString();
                noHpStr = EditNoHp.getText().toString();
                emailStr = EditEmail.getText().toString();
                alamatStr = EditAlamat.getText().toString();
                tanggalBookingStr = EditTanggalBooking.getText().toString();
                jamBookingStr = EditJamBooking.getText().toString();

                new CreateBkgTask().execute();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private class CreateBkgTask extends AsyncTask<String, String, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(CreateActivity.this);
            dialog.setMessage("Please waiting...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair(TAG_NAMA_KONSUMEN, namaKonsumenStr));
            params.add(new BasicNameValuePair(TAG_JENIS_KELAMIN, jenisKelaminStr));
            params.add(new BasicNameValuePair(TAG_NAMA_MOTOR, namaMotorStr));
            params.add(new BasicNameValuePair(TAG_KELUHAN, keluhanStr));
            params.add(new BasicNameValuePair(TAG_NO_POLISI, noPolisiStr));
            params.add(new BasicNameValuePair(TAG_NO_HP, noHpStr));
            params.add(new BasicNameValuePair(TAG_EMAIL, emailStr));
            params.add(new BasicNameValuePair(TAG_ALAMAT, alamatStr));
            params.add(new BasicNameValuePair(TAG_TANGGAL_BOOKING, tanggalBookingStr));
            params.add(new BasicNameValuePair(TAG_JAM_BOOKING, jamBookingStr));

            JSONObject json = jsonParser.makeHttpRequest(url_create_bkg, "POST", params);

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success==1)
                {
                    finish();
                }

                else{
                    return "gagal_database";
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return "gagal_koneksi_or_exception";
            }
            return "sukses";
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (result.equalsIgnoreCase("gagal_database"))
            {
                dialog.dismiss();
                Toast.makeText(CreateActivity.this, "Terjadi kesalahan! Silakan cek koneksi anda!", Toast.LENGTH_SHORT).show();
            }

            else if (result.equalsIgnoreCase("gagal_koneksi_or_exception"))
            {
                dialog.dismiss();
                Toast.makeText(CreateActivity.this, "Terjadi masalah! Silakan cek koneksi anda!", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("sukses"))
            {
                dialog.dismiss();
                Intent i = null;
                i = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(CreateActivity.this,"Data Booking successfully created.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
