package org.lequochai.fashionshop.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogHelper {
//    Static methods:
    public static void showAlertDialog(Context context, String title, String message,
                                       DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .show();
    }

    public static void showAlertDialog(Context context, String title, String message) {
        showAlertDialog(context, title, message, null);
    }

    public static void showErrorDialog(Context context, String message) {
        showAlertDialog(context, "Đã có lỗi xảy ra", message);
    }

//    Constructors:
    private DialogHelper() {

    }
}
