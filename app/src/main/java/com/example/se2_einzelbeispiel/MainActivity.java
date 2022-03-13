package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

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

        EditText inputMatNr = findViewById(R.id.inputMatNr);
        Button sendButton = findViewById(R.id.sendButton);
        TextView answerField = findViewById(R.id.answerField);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matNr = inputMatNr.getText().toString();
                Client client = new Client(matNr);
                Thread thread = new Thread(client);
                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String serverOutput = client.serverOutput;
                answerField.setText(serverOutput);
            }
        });
    }
}