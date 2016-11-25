package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeration extends AppCompatActivity {

    private final String LOG_TAG = Registeration.class.getSimpleName();

    SessionManagement sessionManagement;
    DBHelper DB;
    boolean Inserted;
    String selectedRadio_UserType;
    String selectedRadio_Gender;

    EditText txt_FirstName;
    EditText txt_LastName;
    EditText txt_email;
    TextView txt_email_astrisk;
    EditText txt_Password;
    EditText txt_ConfirmPassword;
    EditText txt_DateOfBirth;
    RadioGroup radioUserTypeGroup,radioGenderGroup;
    RadioButton Radio_Merchandise,Radio_Client,Radio_Male,Radio_Female,Radio_Gender,Radio_UserType;

    final Calendar c=Calendar.getInstance();
    final int year=c.get(Calendar.YEAR);
    final int month=c.get(Calendar.MONTH);
    final int day=c.get(Calendar.DAY_OF_MONTH);

    String txt_FirstName_STR,txt_LastName_STR,txt_email_STR,txt_Password_STR,txt_ConfirmPassword_STR,txt_Gender_STR,txt_DateOfBirth_STR,txt_UserType_STR;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    RadioButton radiobtn,radiobtn2;
    String name,Gendername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        sessionManagement= new SessionManagement(getApplicationContext());
        try{
            DB = new DBHelper(this);
        }catch (Exception e){
            Log.e(LOG_TAG,"Didn't Create Database",e);
        }

        txt_FirstName=(EditText) findViewById(R.id.EditText_FirstName);
        txt_LastName=(EditText)findViewById(R.id.txt_LastName);
        txt_email=(EditText)findViewById(R.id.txt_email);
        txt_email_astrisk=(TextView)findViewById(R.id.txt_email_astrisk);
        Radio_Merchandise=(RadioButton)findViewById(R.id.Radio_Merchandise);
        Radio_Client=(RadioButton)findViewById(R.id.Radio_Client);
        Radio_Male=(RadioButton)findViewById(R.id.Radio_Male);
        Radio_Female=(RadioButton)findViewById(R.id.Radio_Female);
        radioUserTypeGroup = (RadioGroup) findViewById(R.id.radioUserTypeGroup);
        radioGenderGroup=(RadioGroup)findViewById(R.id.radioGenderGroup);
        txt_Password=(EditText)findViewById(R.id.txt_Password);
        txt_ConfirmPassword=(EditText)findViewById(R.id.txt_ConfirmPassword);
        txt_DateOfBirth=(EditText)findViewById(R.id.txt_dOB);


        txt_DateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog DatePick=new DatePickerDialog(Registeration.this, new DatePickerDialog.OnDateSetListener() {
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
                        txt_DateOfBirth.setText(month_SRT.toString()+" "+dayOfMonth+", "+year);

                    }
                },year,month,day);
                DatePick.setTitle("Date Of Birth");
                DatePick.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        txt_email_astrisk.setError(null);
        txt_Password.setError(null);
        txt_ConfirmPassword.setError(null);
    }

    public void SubmitNewUser(View view) {
        txt_FirstName_STR= txt_FirstName.getText().toString();
        txt_LastName_STR=txt_LastName.getText().toString();
        txt_email_STR=txt_email.getText().toString();


        radiobtn=(RadioButton) radioUserTypeGroup.findViewById(radioUserTypeGroup.getCheckedRadioButtonId());
        name= radiobtn.getText().toString();

        radiobtn2=(RadioButton)radioGenderGroup.findViewById(radioGenderGroup.getCheckedRadioButtonId());
        Gendername=radiobtn2.getText().toString();


        txt_Password_STR=txt_Password.getText().toString();
        txt_ConfirmPassword_STR=txt_ConfirmPassword.getText().toString();
        txt_DateOfBirth_STR=txt_DateOfBirth.getText().toString();

        if(!validateEmail(txt_email.getText().toString())) {
            txt_email_astrisk.setError("**");
            txt_email.requestFocus();
        }else {
            txt_email_astrisk.setError(null);
            if (!validatePassword(txt_Password.getText().toString())) {
                txt_Password.setError("Invalid Password");
                txt_Password.requestFocus();
            } else {
                txt_Password.setError(null);
                if (!validateConfirmPassword(txt_Password.getText().toString(), txt_ConfirmPassword.getText().toString())) {
                    txt_ConfirmPassword.setError("doesn't match or less than 9 chars");
                    txt_ConfirmPassword.requestFocus();
                } else {
                    txt_ConfirmPassword.setError(null);
                    //(ID INTEGER PRIMARY KEY AUTOINCREMENT, FirstName TEXT     LastName TEXT,     Email TEXT,   UserType TEXT,         Gender TEXT,Password TEXT,DateOfBirth TEXT,IS_Admin TEXT)
                    //                                          String FirstName ,  String LastName , String Email, String UserType , String Gender ,String Password ,String DateOfBirth,String IS_Admin)
                    if (!txt_FirstName_STR.matches("") && !txt_LastName_STR.matches("") && !txt_email_STR.matches("") &&  !txt_Password_STR.matches("") && !txt_ConfirmPassword_STR.matches("") && !txt_DateOfBirth_STR.matches("")) {
                        UsersEntity emailExistance= DB.getEmailNoDuplicationConfirmation(txt_email_STR);
                        String email= String.valueOf(emailExistance.getEmail());

                        if (txt_email_STR.matches(email)){
                            DialogueChangeEmail();
                        }else {

                            if (name.equals("Client") && Gendername.equals("Male")) {
                                Inserted = DB.insertData(txt_FirstName_STR, txt_LastName_STR, txt_email_STR, name, "Male", txt_Password_STR, txt_ConfirmPassword_STR, txt_DateOfBirth_STR, "Client");
                            } else if (name.equals("Merchandise") && Gendername.equals("Female")) {
                                Inserted = DB.insertData(txt_FirstName_STR, txt_LastName_STR, txt_email_STR, name, "Female", txt_Password_STR, txt_ConfirmPassword_STR, txt_DateOfBirth_STR, "Merchandise");
                            } else if (name.equals("Client") && Gendername.equals("Female")) {
                                Inserted = DB.insertData(txt_FirstName_STR, txt_LastName_STR, txt_email_STR, name, "Female", txt_Password_STR, txt_ConfirmPassword_STR, txt_DateOfBirth_STR, "Client");
                            } else if (name.equals("Merchandise") && Gendername.equals("Male")) {
                                Inserted = DB.insertData(txt_FirstName_STR, txt_LastName_STR, txt_email_STR, name, "Male", txt_Password_STR, txt_ConfirmPassword_STR, txt_DateOfBirth_STR, "Merchandise");
                            }
                            if (Inserted == true) {
                                if (txt_email_STR != "" && selectedRadio_UserType != ""&&txt_FirstName_STR!="") {
                                    Intent intent_Home_Register = new Intent(this, androidlistviewactivity.class);
                                    Bundle b = new Bundle();
                                    b.putString("FirstName", txt_FirstName_STR);// This data is missing here
                                    b.putString("Email", txt_email_STR);// This data is missing here
                                    b.putString("UserType", selectedRadio_UserType);//
                                    intent_Home_Register.putExtras(b);
                                    sessionManagement.createLoginSession(txt_FirstName_STR,txt_email_STR);
                                    startActivity(intent_Home_Register);
                                    finish();
                                }
                            } else {
                                Toast.makeText(this, "Has not Registered", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(this, "Fill all your data", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }

    private boolean validateConfirmPassword(String Password, String ConfirmPassword) {
        if(Password.equals(ConfirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validatePassword(String password) {
        if(password!=null && password.length()>9) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void DialogueChangeEmail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Change your email please.\n\nyourbest-online.com\n")
                .setTitle("Email Exists")
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }
}