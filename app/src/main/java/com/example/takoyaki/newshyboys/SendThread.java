package com.example.takoyaki.newshyboys;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by hjj12 on 2016-01-12.
 */
public class SendThread extends Thread {

        int studentORprofessor;
        Socket socket;
        DataOutputStream output;
        String inputtext;
        String roomcode;
        public SendThread(Socket socket,String roomcode, String inputtext,int studentORprofessor) {
            this.socket = socket;
            this.inputtext = inputtext;
            this.studentORprofessor = studentORprofessor;
            this.roomcode = roomcode;
            try {
                output = new DataOutputStream(this.socket.getOutputStream());
            } catch (Exception e) {
            }
        }

        public void run() {

            try {
                Log.d("debugNull",studentORprofessor+" / "+inputtext);
                Log.d("debugNull",output.toString());
                output.writeUTF(studentORprofessor +"*"+ roomcode +"*"+ inputtext);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }finally {
                if(studentORprofessor==0)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
        public void exitSocket(){
            try {
                output.writeUTF("Socketisclosed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
