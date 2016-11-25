package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private final String LOG_TAG = Registeration.class.getSimpleName();

    SessionManagement sessionManagement;
    DBHelper DB;
    TextView txt_error;
    EditText txt_email;
    EditText txt_password;
    String txt_Email_STR;
    String txt_Password_STR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManagement = new SessionManagement(getApplicationContext());
        try {
            DB = new DBHelper(this);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Didn't Create Database", e);
        }

        txt_email = (EditText) findViewById(R.id.txt_email_loggin);
        txt_password = (EditText) findViewById(R.id.txt_password_loggin);

    }

    public void LogIn_method(View view) {
        try {
            txt_Email_STR = txt_email.getText().toString();
            txt_Password_STR = txt_password.getText().toString();
            if (!txt_Email_STR.matches("") && !txt_Password_STR.matches("")) {
                boolean Are_Founded = DB.Login(txt_Email_STR, txt_Password_STR);
                UsersEntity usersEntity = DB.UserIdentity(txt_Email_STR, txt_Password_STR);
                String Email = usersEntity.getEmail();
                String selectedRadio_UserType = usersEntity.getSelectedRadio_UserType();
                if (Are_Founded == true) {
                    if (!Email.matches("") && !selectedRadio_UserType.matches("")) {
                        Intent intent_Home_Login = new Intent(this, androidlistviewactivity.class);
                        Bundle b = new Bundle();
                        b.putString("Email", Email);
                        b.putString("UserType", selectedRadio_UserType);
                        intent_Home_Login.putExtras(b);
                        sessionManagement.createLoginSession(selectedRadio_UserType, txt_Email_STR);
                        startActivity(intent_Home_Login);
                        finish();
                    }
                } else {
                    DialogueUserNotExist();
                }
            } else {
                txt_email.requestFocus();
                txt_password.setError("missing Email or Password");
                txt_password.requestFocus();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Remove all entries then Enter it again", Toast.LENGTH_LONG).show();
        }

    }

    public void DialogueUserNotExist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("User may not exist or entries are written wrongly.\n\nyourbest-online.com\n")
                .setTitle("Not Exist")
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }
}