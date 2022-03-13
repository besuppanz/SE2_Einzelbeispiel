package com.example.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputMatNr = findViewById(R.id.inputMatNr);
        Button sendButton = findViewById(R.id.sendButton);
        TextView answerField = findViewById(R.id.answerField);
        Button calculateButton = findViewById(R.id.calculateButton);

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

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matNr = inputMatNr.getText().toString();
                matNr = deletePrimeNumber(matNr);
                matNr = sortNumbers(matNr);
                answerField.setText(matNr);
            }
        });
    }

    private String sortNumbers(String matNr) {
        String sortedMatNr = "";
        int[] arr = new int[matNr.length()];
        for(int i = 0; i < matNr.length(); i++) {
            arr[i] = Character.getNumericValue(matNr.charAt(i));    //store chars as ints in array
        }
        Arrays.sort(arr);   //sorts numbers ascending
        for(int i = 0; i < arr.length; i++) {
            sortedMatNr += Integer.toString(arr[i]);    //convert sorted int numbers to string and store them in return value
        }

        return sortedMatNr;
    }

    private String deletePrimeNumber(String matNr) {
        String cutMatNr = matNr;
        boolean isPrime;

        for(int i = 0; i < matNr.length(); i++) {
            isPrime = true;
            int digit = Character.getNumericValue(matNr.charAt(i)); //get chars from string, convert to int
            //Check if digit is prime number
            if(digit <= 1) {
                isPrime = false;
            } else {
                for(int j = 2; j <= digit/2; j++) {
                    if(digit % j == 0) {    //if statement is true for one j, number is not prime, so break loop
                        isPrime = false;
                        break;
                    }
                }
            }
            if(isPrime) {
                cutMatNr = cutMatNr.replace(Integer.toString(digit), ""); //delete prime number from string
            }
        }
        return cutMatNr;
    }
}