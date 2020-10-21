package com.melan.aplikasibooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();

    String url_update_bkg = "http://192.168.43.166/booking/public/api/booking/update";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID = "id";
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

    TextView txtId;
    EditText editNamaKonsumen, editJenisKelamin, editNamaMotor, editKeluhan, editNoPolisi, editNoHp, editEmail,
             editAlamat, editTanggalBooking, editJamBooking;
    Button updateBtn, backBtn;

    String  idStr, namaKonsumenStr, jenisKelaminStr, namaMotorStr,keluhanStr,
            noPolisiStr, noHpStr, emailStr, alamatStr, tanggalBookingStr, jamBookingStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtId = (TextView)findViewById(R.id.input_id);
        editNamaKonsumen = (EditText)findViewById(R.id.input_nama_konsumen);
        editJenisKelamin = (EditText)findViewById(R.id.input_jenis_kelamin);
        editNamaMotor = (EditText)findViewById(R.id.input_nama_motor);
        editKeluhan = (EditText)findViewById(R.id.input_keluhan);
        editNoPolisi = (EditText)findViewById(R.id.input_no_polisi);
        editNoHp = (EditText)findViewById(R.id.input_no_hp);
        editEmail = (EditText)findViewById(R.id.input_email);
        editAlamat = (EditText)findViewById(R.id.input_alamat);
        editTanggalBooking = (EditText)findViewById(R.id.input_tanggal_booking);
        editJamBooking = (EditText)findViewById(R.id.input_jam_booking);

        updateBtn = (Button)findViewById(R.id.btn_update);
        backBtn = (Button) findViewById(R.id.btn_back);

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

        txtId.setText(isi_id);
        editNamaKonsumen.setText(isi_nama_konsumen);
        editJenisKelamin.setText(isi_jenis_kelamin);
        editNamaMotor.setText(isi_nama_motor);
        editKeluhan.setText(isi_keluhan);
        editNoPolisi.setText(isi_no_polisi);
        editNoHp.setText(isi_no_hp);
        editEmail.setText(isi_email);
        editAlamat.setText(isi_alamat);
        editTanggalBooking.setText(isi_tanggal_booking);
        editJamBooking.setText(isi_jam_booking);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                idStr = txtId.getText().toString();
                namaKonsumenStr = editNamaKonsumen.getText().toString();
                jenisKelaminStr = editJenisKelamin.getText().toString();
                namaMotorStr = editNamaMotor.getText().toString();
                keluhanStr = editKeluhan.getText().toString();
                noPolisiStr = editNoPolisi.getText().toString();
                noHpStr = editNoHp.getText().toString();
                emailStr = editEmail.getText().toString();
                alamatStr = editAlamat.getText().toString();
                tanggalBookingStr = editTanggalBooking.getText().toString();
                jamBookingStr = editJamBooking.getText().toString();

                new UpdateBkgTask().execute();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idbkg = txtId.getText().toString();
                String namaKonsumenBkg = editNamaKonsumen.getText().toString();
                String jenisKelaminBkg = editJenisKelamin.getText().toString();
                String namaMotorBkg = editNamaMotor.getText().toString();
                String keluhanBkg = editKeluhan.getText().toString();
                String noPolisiBkg = editNoPolisi.getText().toString();
                String noHpBkg = editNoHp.getText().toString();
                String emailBkg = editEmail.getText().toString();
                String alamatBkg = editAlamat.getText().toString();
                String tanggalBkg = editTanggalBooking.getText().toString();
                String jamBkg = editJamBooking.getText().toString();


                Intent i = null;
                i = new Intent(EditActivity.this, ShowActivity.class);
                Bundle b = new Bundle();
                b.putString("id_bkg", idbkg);
                b.putString("nama_konsumen_bkg", namaKonsumenBkg);
                b.putString("jenis_kelamin_bkg", jenisKelaminBkg);
                b.putString("nama_motor_bkg", namaMotorBkg);
                b.putString("keluhan_bkg", keluhanBkg);
                b.putString("no_polisi_bkg", noPolisiBkg);
                b.putString("no_hp", noHpBkg);
                b.putString("email_bkg", emailBkg);
                b.putString("alamat_bkg", alamatBkg);
                b.putString("tanggal_bkg", tanggalBkg);
                b.putString("jam_bkg", jamBkg);

                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    class UpdateBkgTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(EditActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();

            parameter.add(new BasicNameValuePair(TAG_ID, idStr));
            parameter.add(new BasicNameValuePair(TAG_NAMA_KONSUMEN, namaKonsumenStr));
            parameter.add(new BasicNameValuePair(TAG_JENIS_KELAMIN, jenisKelaminStr));
            parameter.add(new BasicNameValuePair(TAG_NAMA_MOTOR, namaMotorStr));
            parameter.add(new BasicNameValuePair(TAG_KELUHAN, keluhanStr));
            parameter.add(new BasicNameValuePair(TAG_NO_POLISI, noPolisiStr));
            parameter.add(new BasicNameValuePair(TAG_NO_HP, noHpStr));
            parameter.add(new BasicNameValuePair(TAG_EMAIL, emailStr));
            parameter.add(new BasicNameValuePair(TAG_ALAMAT, alamatStr));
            parameter.add(new BasicNameValuePair(TAG_TANGGAL_BOOKING, tanggalBookingStr));
            parameter.add(new BasicNameValuePair(TAG_JAM_BOOKING, jamBookingStr));

            try {
                JSONObject json = jParser.makeHttpRequest(url_update_bkg,"POST",parameter);

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
                Toast.makeText(EditActivity.this, "Unable to connect to server, please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(EditActivity.this, "Fail... Try Again",Toast.LENGTH_LONG).show();
            }

            else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(EditActivity.this,"Data Booking successfully updated.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
