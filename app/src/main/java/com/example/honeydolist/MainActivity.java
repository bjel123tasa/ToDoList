package com.example.honeydolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button saveButton;
    EditText message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = findViewById(R.id.button_save);
        message = findViewById(R.id.edit_text);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!message.getText().toString().equals("")){
                    String msg = message.getText().toString();
                    writeToFile(msg);
                }



            }
        });

        if(readFromFile() != null){
            message.setText(readFromFile());

        }
    }

    private void writeToFile(String text){
        try {
            OutputStreamWriter outPutStreamWriter =  new OutputStreamWriter(openFileOutput("todolist.txt", Context.MODE_PRIVATE));
            outPutStreamWriter.write(String.valueOf(message));
            outPutStreamWriter.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readFromFile(){

        String result = "";

        try  {
            InputStream inputStream = openFileInput("todo.txt");

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String tempString = "";

                StringBuilder stringBuilder = new StringBuilder();

                while ((tempString = bufferedReader.readLine())!= null){
                    stringBuilder.append(tempString);

                }

                inputStream.close();

                result = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
