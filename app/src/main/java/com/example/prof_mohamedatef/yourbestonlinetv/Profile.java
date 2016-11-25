package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Profile extends Activity {

    private final String LOG_TAG = Registeration.class.getSimpleName();

    List<UsersEntity> Logger;
    EditText EditText_FirstName,txt_LastName,txt_email,txt_dOB;
    TextView txt_ISADMIN,txt_ClientStatus,txt_GenderStatus;
    Button ButtonText;
    String FirstName,LastName,Email,Gender,UserType,DOB,IsAdmin;
    String UpdatedFirstName,UpdatedLastName,UpdateDateOfBirth;
    DBHelper myDB;

    final Calendar c=Calendar.getInstance();
    final int year=c.get(Calendar.YEAR);
    final int month=c.get(Calendar.MONTH);
    final int day=c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        try{
            myDB = new DBHelper(this);
        }catch (Exception e){
            Log.e(LOG_TAG,"Didn't Create Database",e);
        }

        EditText_FirstName=(EditText)findViewById(R.id.EditText_FirstName);
        txt_LastName=(EditText)findViewById(R.id.txt_LastName);
        txt_email=(EditText)findViewById(R.id.txt_email);
        txt_dOB=(EditText)findViewById(R.id.txt_dOB);
        txt_ISADMIN=(TextView) findViewById(R.id.txt_ISADMIN);
        txt_ClientStatus=(TextView)findViewById(R.id.txt_ClientStatus);
        txt_GenderStatus=(TextView)findViewById(R.id.txt_GenderStatus);

        ButtonText=(Button)findViewById(R.id.ButtonText);

        EditText_FirstName.setEnabled(false);
        txt_LastName.setEnabled(false);
        txt_email.setEnabled(false);
        txt_dOB.setEnabled(false);
        ButtonText.setText("Edit Profile");

        Intent loggedUSER_Intent=getIntent();
        if (loggedUSER_Intent!=null){
            Logger = (ArrayList<UsersEntity>) loggedUSER_Intent.getSerializableExtra("LoggerInfo");
            for(UsersEntity usersEntity:Logger){
                FirstName= usersEntity.getFirstName();
                LastName=usersEntity.getLastName();
                Email=usersEntity.getEmail();
                Gender=usersEntity.getSELECTEDGender();
                UserType=usersEntity.getSelectedRadio_UserType();
                IsAdmin=usersEntity.getISADMIN();
                DOB=usersEntity.getDATEOFBIRTH();
            }


            txt_ClientStatus.setText(UserType);
            txt_GenderStatus.setText(Gender);
            EditText_FirstName.setText(FirstName);
            txt_LastName.setText(LastName);
            txt_email.setText(Email);
            txt_dOB.setText(DOB);
            if (IsAdmin!=null){
                txt_ISADMIN.setText(IsAdmin);
            }
        }

        txt_dOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog DatePick=new DatePickerDialog(Profile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//                        String[] monthes={"Jan","Feb","Mars","April","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
//                        for (int i=0;i<monthes.length;i++){
                        String month_SRT=null;
                        switch (month){
                            case 0:
                                month_SRT="Jan";
                                break;
                            case 1:
                                month_SRT="Feb";
                                break;
                            case 2:
                                month_SRT="Mars";
                                break;
                            case 3:
                                month_SRT="April";
                                break;
                            case 4:
                                month_SRT="May";
                                break;
                            case 5:
                                month_SRT="Jun";
                                break;
                            case 6:
                                month_SRT="Jul";
                                break;
                            case 7:
                                month_SRT="Aug";
                                break;
                            case 8:
                                month_SRT="Sep";
                                break;
                            case 9:
                                month_SRT="Oct";
                                break;
                            case 10:
                                month_SRT="Nov";
                                break;
                            case 11:
                                month_SRT="Dec";
                                break;
                        }
                        txt_dOB.setText(month_SRT.toString()+" "+dayOfMonth+", "+year);

                    }
                },year,month,day);
                DatePick.setTitle("Date Of Birth");
                DatePick.show();
            }
        });
    }

    public void UpdateProfile(View view) {
        if (EditText_FirstName.isEnabled()==false&&txt_LastName.isEnabled()==false&&txt_email.isEnabled()==false&&txt_dOB.isEnabled()==false){
            EditText_FirstName.setEnabled(true);
            txt_LastName.setEnabled(true);
            txt_dOB.setEnabled(true);
            ButtonText.setText("Save");

        }else {
            //update profile data


            UpdatedFirstName= EditText_FirstName.getText().toString();
            UpdatedLastName=txt_LastName.getText().toString();
            Email=txt_email.getText().toString();
            UpdateDateOfBirth=txt_dOB.getText().toString();

            boolean UpdateLoggedUser= myDB.UpdateLoggedUSerInfoData(Email,UpdatedFirstName,UpdatedLastName,UpdateDateOfBirth);
            if (UpdateLoggedUser==true){
                EditText_FirstName.setEnabled(false);
                txt_LastName.setEnabled(false);
                txt_email.setEnabled(false);
                txt_dOB.setEnabled(false);
                ButtonText.setText("Edit Profile");
                DialogueLoggedUSerInfoUpdatedSuccessfully();
            }
            else {
                Toast.makeText(this, "Has not Updated", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void DialogueLoggedUSerInfoUpdatedSuccessfully(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Successful.\n\nyourbest-online.com")
                .setTitle("Successful")
                .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }
}