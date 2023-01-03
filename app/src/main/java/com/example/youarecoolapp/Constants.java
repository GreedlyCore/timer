package com.example.youarecoolapp;

import android.content.Context;

public class Constants {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Constants.context = context;
    }
}
