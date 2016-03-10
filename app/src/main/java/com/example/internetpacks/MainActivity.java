package com.example.internetpacks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private Button about, beeline, ucell;
    static final String OPERATOR_DESC = "OPERATOR_DESCRIPTOR";
    static final int BEELINE_DESC = 1;
    static final int UCELL_DESC = 2;

    static final String URL_USD_RATE = "http://www.cbu.uz/section/rates/widget/xml/usd";
    static final String DATE_ACT = "date_act";
    static final String RATE = "rate";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        class GetUsdRate extends AsyncTask<String, Void, String[]> {
            @Override
            protected String[] doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();

                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(connection.getInputStream());

                    return (new String[]{document.getElementsByTagName(DATE_ACT).item(0).getTextContent(),
                            document.getElementsByTagName(RATE).item(0).getTextContent()});
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String[] result) {
                if (result != null) {
                    sharedPreferences.edit().putString(DATE_ACT, result[0])
                            .putString(RATE, result[1]).apply();
                } else {
                    Toast.makeText(MainActivity.this,
                            MainActivity.this.getResources().getText(R.string.toast_connectionError),
                            Toast.LENGTH_LONG).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        final ConnectivityManager conn = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                        conn.addDefaultNetworkActiveListener(new ConnectivityManager.OnNetworkActiveListener() {
                            @Override
                            public void onNetworkActive() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                doInBackground(URL_USD_RATE);
                            }
                        });
                    }
                }
            }
        }
        if(Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY
                &&
                !sharedPreferences.getString(DATE_ACT, "")
                        .equals(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()))
                ||
                !sharedPreferences.contains(RATE)) {
            new GetUsdRate().execute(URL_USD_RATE);
        }

        about = (Button)findViewById(R.id.about);
        beeline = (Button)findViewById(R.id.beeline);
        ucell = (Button)findViewById(R.id.ucell);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.beeline:
                        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                        intent.putExtra(OPERATOR_DESC, BEELINE_DESC)
                                .putExtra(RATE, sharedPreferences.getString(RATE, "1.0"));
                        startActivity(intent);
                        break;
                    case R.id.ucell:
                        intent = new Intent(MainActivity.this, InfoActivity.class);
                        intent.putExtra(OPERATOR_DESC, UCELL_DESC)
                                .putExtra(RATE, sharedPreferences.getString(RATE, "1.0"));
                        startActivity(intent);
                        break;
                    case R.id.about:
                        intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };
        about.setOnClickListener(onClickListener);
        beeline.setOnClickListener(onClickListener);
        ucell.setOnClickListener(onClickListener);
    }
}
