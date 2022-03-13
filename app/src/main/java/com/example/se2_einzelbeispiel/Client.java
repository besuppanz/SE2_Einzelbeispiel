package com.example.se2_einzelbeispiel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable {
    String matNr;
    String serverOutput;

    Client(String matNr) {
        this.matNr = matNr;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212); //create client socket, connect to server
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //create output stream attached to socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //create input stream
            outToServer.writeBytes(this.matNr + '\n');  //send line to server
            this.serverOutput = inFromServer.readLine();    //get response from server
            clientSocket.close();   //close connection to server
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
