package com.example.internetpacks;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    TextView usdRateTV, packCoast, localPackCoast;
    Button buyPack, checkBalance, checkPackRest, goToSite;
    Spinner spinnerPacks;
    int spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final int descriptor = getIntent().getIntExtra(MainActivity.OPERATOR_DESC, 0);

        spinnerPacks = (Spinner)findViewById(R.id.spinnerPacks);
        packCoast = (TextView)findViewById(R.id.packCoast);
        localPackCoast = (TextView)findViewById(R.id.localPackCoast);
        usdRateTV = (TextView)findViewById(R.id.usdRate);

        buyPack = (Button)findViewById(R.id.buyPack);
        checkBalance = (Button)findViewById(R.id.checkBalance);
        checkPackRest = (Button)findViewById(R.id.checkPackRest);
        goToSite = (Button)findViewById(R.id.goToSite);

        final Float usdRateFloat = Float.parseFloat(getIntent().getStringExtra(MainActivity.RATE));
        usdRateTV.setText(String.format(" %.2f", usdRateFloat));

        ArrayAdapter<CharSequence> adapter;
        if(descriptor == MainActivity.BEELINE_DESC) {
            adapter = ArrayAdapter.createFromResource(this, R.array.beeline_packs,
                    android.R.layout.simple_spinner_item);
        } else {
            adapter = ArrayAdapter.createFromResource(this, R.array.ucell_packs,
                    android.R.layout.simple_spinner_item);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPacks.setAdapter(adapter);
        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = position;
                packCoast.setText(String.format(" %.2f", getPackCoast(descriptor, position)));
                localPackCoast.setText(String.format(" %.2f", getPackCoast(descriptor, position) * usdRateFloat));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerPacks.setOnItemSelectedListener(onItemSelectedListener);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buyPack:
                        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getUSSD(descriptor, spinnerPosition)));
                        break;
                    case R.id.checkBalance:
                        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                                + (descriptor == MainActivity.BEELINE_DESC ? InfoBeeline.BEELINE_USSD_CHECK_BALANCE
                                : InfoUcell.UCELL_USSD_CHECK_BALANCE)));
                        break;
                    case R.id.checkPackRest:
                        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                                + (descriptor == MainActivity.BEELINE_DESC ? InfoBeeline.BEELINE_USSD_CHECK_PACK_REST
                                : InfoUcell.UCELL_USSD_CHECK_PACK_REST)));
                        break;
                    case R.id.goToSite:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(descriptor == MainActivity.BEELINE_DESC
                                ? InfoBeeline.BEELINE_URL
                                : InfoUcell.UCELL_URL));
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        };
        buyPack.setOnClickListener(onClickListener);
        checkBalance.setOnClickListener(onClickListener);
        checkPackRest.setOnClickListener(onClickListener);
        goToSite.setOnClickListener(onClickListener);
    }

    private float getPackCoast(int desc, int position) {
        if(desc == MainActivity.BEELINE_DESC) {
            return InfoBeeline.BEELINE_COASTS[position];
        } else {
            return InfoUcell.UCELL_COASTS[position];
        }
    }

    private String getUSSD(int desc, int spinnerPosition) {
        if(desc == MainActivity.BEELINE_DESC) {
            return InfoBeeline.BEELINE_USSDS[spinnerPosition];
        } else {
            return InfoUcell.UCELL_USSDS[spinnerPosition];
        }
    }
}
