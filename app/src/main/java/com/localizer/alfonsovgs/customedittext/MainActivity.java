package com.localizer.alfonsovgs.customedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditTextEx editTextEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEx = findViewById(R.id.myEditText);
        editTextEx.setCustomAction(new OnBeforeAction() {
            @Override
            public void doAction() {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Toast por defecto: " + editTextEx.getText(), Toast.LENGTH_SHORT);

                toast1.show();
            }
        });
    }
}
