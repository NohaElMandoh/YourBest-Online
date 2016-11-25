package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IamAUser(View view) {
        Intent intent_Login=new Intent(this,Login.class);
        startActivity(intent_Login);
    }

    public void IamNew(View view) {
        Intent intent_Registeration=new Intent(this,Registeration.class);
        startActivity(intent_Registeration);
    }
}