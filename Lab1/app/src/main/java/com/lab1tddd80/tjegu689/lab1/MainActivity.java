package com.lab1tddd80.tjegu689.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submitButton = (Button) findViewById(R.id.submit);
        final EditText inputField = (EditText) findViewById(R.id.inputiField);
        final TextView addedNames = (TextView) findViewById(R.id.textView);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addedNames.append("\n" + inputField.getText());
                inputField.setText("");
            }
        });
    }
}
