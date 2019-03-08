package com.localizer.alfonsovgs.customedittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

interface OnBeforeAction {
    void doAction();
}

public class EditTextEx extends AppCompatEditText {
    private Drawable sendButton;
    private OnBeforeAction onBeforeAction;

    public EditTextEx(Context context) {
        super(context);
        setButton();
    }

    public EditTextEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        setButton();
    }

    public EditTextEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setButton();
    }

    public void setCustomAction(OnBeforeAction eventListener) {
        onBeforeAction = eventListener;
    }

    private void setButton() {
        //Este primer recurso por defecto pudiera ser el mismo icono pero con una opacidad
        //sendButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_send_24px, null);
        //setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        //setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null); //solo si minSdkVersion >= 17

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    //setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null); //solo si minSdkVersion >= 17
                }
                else{
                    sendButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_send_24px, null);
                    setCompoundDrawablesWithIntrinsicBounds(null, null, sendButton, null);
                    //setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, sendButton, null); //solo si minSdkVersion >= 17
                }
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText editText = (EditText)v;
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX() >= editText.getRight() - editText.getCompoundPaddingRight()){
                        if(onBeforeAction != null) onBeforeAction.doAction();
                        editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
