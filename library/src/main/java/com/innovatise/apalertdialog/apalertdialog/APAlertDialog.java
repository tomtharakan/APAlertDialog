package com.innovatise.apalertdialog.apalertdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tomtharakan on 03/02/18.
 */

public class APAlertDialog extends AlertDialog implements View.OnClickListener {


    public enum AlertDialogStyle {
        SUCCESS,
        ERROR,
        WARNING,
        INFO,
        QUESTION
    }
    public enum ButtonStyle {

        BUTTON_STYLE_GREEN,
        BUTTON_STYLE_GREY,
        BUTTON_STYLE_RED,
        BUTTON_STYLE_CUSTOM;

        public int getColor() {
            switch (this) {
                case BUTTON_STYLE_GREEN:
                    return R.color.green;
                case BUTTON_STYLE_GREY:
                    return R.color.gray;
                case BUTTON_STYLE_RED:
                    return R.color.red;
                default:
                    return R.color.gray;
            }

        }
    }

    int buttonIndex = 0;

    private ViewGroup viewGroup;

    private AlertDialogStyle style;

    int buttonIds[]={R.id.alert_dialog_button_1, R.id.alert_dialog_button_2,
            R.id.alert_dialog_button_3, R.id.alert_dialog_button_4};

    ArrayList<MFAlertDialogButton> buttons = new ArrayList<>();

    String title;

    String message;

    public APAlertDialog(Context context) {
        super(context);
    }

    public APAlertDialog(Context context, AlertDialogStyle style, String title, String message) {
        super(context);
        this.title = title;
        this.message = message;
        this.style = style;
    }

    protected APAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    protected APAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public void present() {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ViewGroup promptView = (ViewGroup) layoutInflater.inflate(R.layout.ap_alert_dialog, null);

        ImageView headerIcon = promptView.findViewById(R.id.header_icon);
        ViewGroup headerBg = promptView.findViewById(R.id.header_bg);

        GradientDrawable bgShape = (GradientDrawable)headerBg.getBackground();

        switch (this.style) {
            case SUCCESS:
                headerIcon.setImageResource(R.drawable.ic_done_white_36dp);
                bgShape.setColor(ContextCompat.getColor(getContext(), R.color.red));
                break;
            case ERROR:
                headerIcon.setImageResource(R.drawable.ic_error_outline_white_36dp);
                bgShape.setColor(ContextCompat.getColor(getContext(), R.color.green));
                break;
            case WARNING:
                headerIcon.setImageResource(R.drawable.ic_warning_white_36dp);
                bgShape.setColor(ContextCompat.getColor(getContext(), R.color.warning_color));
                break;
            case INFO:
                headerIcon.setImageResource(R.drawable.ic_info_outline_white_36dp);
                bgShape.setColor(ContextCompat.getColor(getContext(), R.color.info_color));
                break;
            case QUESTION:
                headerIcon.setImageResource(R.drawable.ic_question);
                bgShape.setColor(ContextCompat.getColor(getContext(), R.color.question_color));
                break;
        }

        if(this.title != null) {
            TextView titleView = (TextView) promptView.findViewById(R.id.title_popup);
            titleView.setText(this.title);
            titleView.setVisibility(View.VISIBLE);
        }

        if(this.message != null) {
            TextView messageView = (TextView) promptView.findViewById(R.id.desc_popup);
            messageView.setText(this.message);
            messageView.setVisibility(View.VISIBLE);
        }

        int i = 0;
        for(MFAlertDialogButton buttonOb : this.buttons) {

            Button button = (Button) promptView.findViewById(buttonIds[i++]);
            button.setVisibility(View.VISIBLE);
            button.setBackgroundColor(ContextCompat.getColor(getContext(), buttonOb.style.getColor()));
            button.setText(buttonOb.title);
            button.setOnClickListener(this);
            button.setTag(buttonOb);
        }

        this.setView(promptView);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.show();
    }

    public void addButton(ButtonStyle style, String title, View.OnClickListener listener) {
        MFAlertDialogButton button = new MFAlertDialogButton(style, title, listener);
        buttons.add(button);
    }

    public void addButton(ButtonStyle style, int title, View.OnClickListener listener) {
        this.addButton(style, getContext().getString(title), listener);
    }

    public void addButton(ButtonStyle style, View.OnClickListener listener) {
        this.addButton(style, null, listener);
    }

    public class MFAlertDialogButton {

        ButtonStyle style = ButtonStyle.BUTTON_STYLE_GREEN;
        View.OnClickListener listener;
        String title;

        public MFAlertDialogButton(ButtonStyle style, String title, View.OnClickListener listener) {
            this.style = style;
            this.listener = listener;
            this.title = title;
        }
    }

    @Override
    public void onClick(View v) {
        this.dismiss();

        MFAlertDialogButton buttonOb = (MFAlertDialogButton) v.getTag();
        if(buttonOb.listener != null) {
            buttonOb.listener.onClick(v);
        }
    }
}
