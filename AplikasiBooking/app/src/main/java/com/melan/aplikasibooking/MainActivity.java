package com.melan.aplikasibooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Booking> list_bkg = new ArrayList<Booking>();
    JSONArray listBkg = null;
    String url_read_bkg = "http://192.168.43.166/booking/public/api/booking";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID = "id";
    public static final String TAG_BKG = "booking";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.listBkg);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateActivity.class);
                startActivity(i);
            }
        });

        ReadBkgTask m = (ReadBkgTask) new ReadBkgTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {

                String idBkg = ((TextView) view.findViewById(R.id.id_bkg)).getText().toString();
                String namaKonsumenBkg = ((TextView) view.findViewById(R.id.nama_konsumen_bkg)).getText().toString();
                String jenisKelaminBkg = ((TextView) view.findViewById(R.id.jenis_kelamin_bkg)).getText().toString();
                String namaMotorBkg = ((TextView) view.findViewById(R.id.nama_motor_bkg)).getText().toString();
                String keluhanBkg = ((TextView) view.findViewById(R.id.keluhan_bkg)).getText().toString();
                String noPolisiBkg = ((TextView) view.findViewById(R.id.no_polisi_bkg)).getText().toString();
                String noHpBkg = ((TextView) view.findViewById(R.id.no_hp_bkg)).getText().toString();
                String emailBkg = ((TextView) view.findViewById(R.id.email_bkg)).getText().toString();
                String alamatBkg = ((TextView) view.findViewById(R.id.alamat_bkg)).getText().toString();
                String tanggalBkg = ((TextView) view.findViewById(R.id.tanggal_bkg)).getText().toString();
                String jamBkg = ((TextView) view.findViewById(R.id.jam_bkg)).getText().toString();

                Intent i = null;
                i = new Intent(MainActivity.this, ShowActivity.class);
                Bundle b = new Bundle();
                b.putString("id_bkg", idBkg);
                b.putString("nama_konsumen_bkg", namaKonsumenBkg);
                b.putString("jenis_kelamin_bkg", jenisKelaminBkg);
                b.putString("nama_motor_bkg", namaMotorBkg);
                b.putString("keluhan_bkg", keluhanBkg);
                b.putString("no_polisi_bkg", noPolisiBkg);
                b.putString("no_hp_bkg", noHpBkg);
                b.putString("email_bkg", emailBkg);
                b.putString("alamat_bkg", alamatBkg);
                b.putString("tanggal_bkg", tanggalBkg);
                b.putString("jam_bkg", jamBkg);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this,"Application Booking Service.",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class ReadBkgTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            String returnResult = getBkgList();
            return returnResult;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(MainActivity.this,"Unable to connect to server, please check your internet connection!",Toast.LENGTH_LONG).show();
            }
            if (result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(MainActivity.this,"Data empty",Toast.LENGTH_LONG).show();
            }
            else{
                list.setAdapter(new BookingAdapter(MainActivity.this,list_bkg));
            }

        }

        public String getBkgList()
        {
            Booking tempBkg = new Booking();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url_read_bkg,"GET",parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success==1)
                {
                    listBkg = json.getJSONArray(TAG_BKG);
                    for (int i = 0; i < listBkg.length(); i++)
                    {
                        JSONObject c = listBkg.getJSONObject(i);
                        tempBkg = new Booking();
                        tempBkg.setId(c.getString(TAG_ID));
                        tempBkg.setNama_konsumen(c.getString(TAG_NAMA_KONSUMEN));
                        tempBkg.setJenis_kelamin(c.getString(TAG_JENIS_KELAMIN));
                        tempBkg.setNama_motor(c.getString(TAG_NAMA_MOTOR));
                        tempBkg.setKeluhan(c.getString(TAG_KELUHAN));
                        tempBkg.setNo_polisi(c.getString(TAG_NO_POLISI));
                        tempBkg.setNo_hp(c.getString(TAG_NO_HP));
                        tempBkg.setEmail(c.getString(TAG_EMAIL));
                        tempBkg.setAlamat(c.getString(TAG_ALAMAT));
                        tempBkg.setTanggal_booking(c.getString(TAG_TANGGAL_BOOKING));
                        tempBkg.setJam_booking(c.getString(TAG_JAM_BOOKING));
                        list_bkg.add(tempBkg);
                    }
                    return "OK";
                }

                else{
                    return "no results";
                }
            }
            catch (Exception e)
            {
                return "Exception Caught";
            }
        }
    }
}
