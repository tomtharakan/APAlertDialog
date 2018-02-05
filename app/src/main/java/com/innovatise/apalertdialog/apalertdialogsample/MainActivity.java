package com.innovatise.apalertdialog.apalertdialogsample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.innovatise.apalertdialog.apalertdialogsample.R;

import com.innovatise.apalertdialog.apalertdialog.APAlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void didClickOnButton(View v) {

        APAlertDialog dialog = null;
        switch (v.getId()) {
            case R.id.success:
                dialog = new APAlertDialog(MainActivity.this, APAlertDialog.AlertDialogStyle.SUCCESS, "Sample alert title" , "Sample alert message ");
                dialog.addButton(APAlertDialog.ButtonStyle.BUTTON_STYLE_GREEN, "OK", this);
                break;

            case R.id.info:
                dialog = new APAlertDialog(MainActivity.this, APAlertDialog.AlertDialogStyle.INFO, "Sample alert title" , "Sample alert message ");
                dialog.addButton(APAlertDialog.ButtonStyle.BUTTON_STYLE_GREY, "OK", null);
                break;
            case R.id.warning:
                dialog = new APAlertDialog(MainActivity.this, APAlertDialog.AlertDialogStyle.WARNING, "Sample alert title" , "Sample alert message ");
                dialog.addButton(APAlertDialog.ButtonStyle.BUTTON_STYLE_GREY, "OK", null);
                break;
            case R.id.error:
                dialog = new APAlertDialog(MainActivity.this, APAlertDialog.AlertDialogStyle.ERROR, "Sample alert title" , "Sample alert message ");
                dialog.addButton(APAlertDialog.ButtonStyle.BUTTON_STYLE_RED, "OK", null);
                break;
            case R.id.question:
                dialog = new APAlertDialog(MainActivity.this, APAlertDialog.AlertDialogStyle.QUESTION, "Sample alert title" , "Sample alert message ");
                dialog.addButton(APAlertDialog.ButtonStyle.BUTTON_STYLE_GREEN, "Allow", null);
                dialog.addButton(APAlertDialog.ButtonStyle.BUTTON_STYLE_GREY, "Close", null);
                break;
        }

        dialog.present();

    }

    @Override
    public void onClick(View v) {

    }
}
