package com.example.internetpacks;

import android.net.Uri;

public class InfoUcell {
    final static String UCELL_URL = "http://ucell.uz/ru/subscribers/internet/inet_services/monthly_packages";

    final static float UCELL_COAST_50MB = 1.25f;
    final static float UCELL_COAST_150MB = 3.0f;
    final static float UCELL_COAST_300MB = 4.5f;
    final static float UCELL_COAST_500MB = 6.0f;
    final static float UCELL_COAST_1000MB = 8.0f;
    final static float UCELL_COAST_1500MB = 10.0f;
    final static float UCELL_COAST_2500MB = 16.0f;
    final static float UCELL_COAST_4000MB = 25.0f;

    final static String UCELL_USSD_CHECK_BALANCE = "*100" + Uri.encode("#");
    final static String UCELL_USSD_CHECK_PACK_REST = "*107" + Uri.encode("#");

    final static String UCELL_USSD_50 = "*555*3*1*1" + Uri.encode("#");
    final static String UCELL_USSD_150 = "*555*3*2*1" + Uri.encode("#");
    final static String UCELL_USSD_300 = "*555*3*3*1" + Uri.encode("#");
    final static String UCELL_USSD_500 = "*555*3*4*1" + Uri.encode("#");
    final static String UCELL_USSD_1000 = "*555*3*5*1" + Uri.encode("#");
    final static String UCELL_USSD_1500 = "*555*3*6*1" + Uri.encode("#");
    final static String UCELL_USSD_2500 = "*555*3*7*1" + Uri.encode("#");
    final static String UCELL_USSD_4000 = "*555*3*8*1" + Uri.encode("#");

    final static float[] UCELL_COASTS = {
            UCELL_COAST_50MB, UCELL_COAST_150MB,
            UCELL_COAST_300MB, UCELL_COAST_500MB,
            UCELL_COAST_1000MB, UCELL_COAST_1500MB,
            UCELL_COAST_2500MB, UCELL_COAST_4000MB
    };

    final static String[] UCELL_USSDS = {
            UCELL_USSD_50, UCELL_USSD_150,
            UCELL_USSD_300, UCELL_USSD_500,
            UCELL_USSD_1000, UCELL_USSD_1500,
            UCELL_USSD_2500, UCELL_USSD_4000
    };
}
