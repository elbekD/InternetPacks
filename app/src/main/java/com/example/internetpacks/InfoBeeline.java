package com.example.internetpacks;

import android.net.Uri;

public class InfoBeeline {
    final static String BEELINE_URL = "https://beeline.uz/uz/Catalog/Packages/Mobile-packages/Mobile-Internet/c/packagesmobileinternet";
    final static float BEELINE_COAST_50MB = 1.7f;
    final static float BEELINE_COAST_80MB = 2.2f;
    final static float BEELINE_COAST_125MB = 3.2f;
    final static float BEELINE_COAST_325MB = 5.5f;
    final static float BEELINE_COAST_525MB = 7.5f;
    final static float BEELINE_COAST_1100MB = 10.0f;
    final static float BEELINE_COAST_1700MB = 13.0f;
    final static float BEELINE_COAST_2700MB = 20.0f;
    final static float BEELINE_COAST_3700MB = 27.0f;

    final static String BEELINE_USSD_CHECK_BALANCE = "*102" + Uri.encode("#");
    final static String BEELINE_USSD_CHECK_PACK_REST = "*103" + Uri.encode("#");

    final static String BEELINE_USSD_50 = "*110*0*01" + Uri.encode("#");
    final static String BEELINE_USSD_80 = "*110*0*02" + Uri.encode("#");
    final static String BEELINE_USSD_125 = "*110*0*03" + Uri.encode("#");
    final static String BEELINE_USSD_325 = "*110*0*04" + Uri.encode("#");
    final static String BEELINE_USSD_525 = "*110*0*05" + Uri.encode("#");
    final static String BEELINE_USSD_1100 = "*110*0*06" + Uri.encode("#");
    final static String BEELINE_USSD_1700 = "*110*0*07" + Uri.encode("#");
    final static String BEELINE_USSD_2700 = "*110*0*08" + Uri.encode("#");
    final static String BEELINE_USSD_3700 = "*110*0*09" + Uri.encode("#");

    final static float[] BEELINE_COASTS = {BEELINE_COAST_50MB, BEELINE_COAST_80MB, BEELINE_COAST_125MB,
            BEELINE_COAST_325MB, BEELINE_COAST_525MB, BEELINE_COAST_1100MB, BEELINE_COAST_1700MB,
            BEELINE_COAST_2700MB, BEELINE_COAST_3700MB};

    final static String[] BEELINE_USSDS = {BEELINE_USSD_50, BEELINE_USSD_80, BEELINE_USSD_125, BEELINE_USSD_325,
            BEELINE_USSD_525, BEELINE_USSD_1100, BEELINE_USSD_1700, BEELINE_USSD_2700, BEELINE_USSD_3700};
}
