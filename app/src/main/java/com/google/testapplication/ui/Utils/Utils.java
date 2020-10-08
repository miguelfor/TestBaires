package com.google.testapplication.ui.Utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.testapplication.R;

public class Utils {
    private static ProgressDialog progress;

    public static void showDialog(Context context) {
        progress = new ProgressDialog(context);
        progress.setTitle(context.getString(R.string.label_loading_tittle));
        progress.setMessage(context.getString(R.string.label_loading_message));
        progress.setCancelable(true);
        progress.show();
    }

    public static void dismissDialog() {
        try {

            if (progress != null) {
                progress.dismiss();
                progress.hide();
                progress = null;
            }
        } catch (Exception e) {
        }

    }
}
