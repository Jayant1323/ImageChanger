package com.gal.imagechanger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
private static ImageView imgview;
private Button button;
String mypreference="imagepref",data="";
TextView tv;
int id;
private int current_img;
SharedPreferences sharedPreferences;
int[] images={R.drawable.imageone,R.drawable.imagetwo,R.drawable.imagethree};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        imgview = findViewById(R.id.imageView2);
        data = Integer.toString(imgview.getId());
        sharedPreferences=getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        FileOutputStream fin=null;
        try {
            fin = openFileOutput(mypreference, MODE_PRIVATE);
            fin.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonclick();
    }
    public void buttonclick()
    {
        imgview= findViewById(R.id.imageView2);
        button= findViewById(R.id.bt1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_img++;
                current_img=current_img % images.length;
                imgview.setImageResource(images[current_img]);
            }
        });
    }

    public void show(View view) {
        FileOutputStream fout=null;
        try {
//            fout = openFileOutput("input.txt", MODE_PRIVATE);
//            fout.write(data.getBytes());
//            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
            FileInputStream fin;
            fin = openFileInput("hello.txt");
            InputStreamReader isr=new InputStreamReader(fin);
            BufferedReader br=new BufferedReader(isr);
            StringBuffer sb=new StringBuffer();
            String text="";
            while((text=br.readLine())!=null){
                sb.append(text).append("\n");
            }
            tv.setText(sb.toString());


        }catch (Exception e){

        }
    }
}
