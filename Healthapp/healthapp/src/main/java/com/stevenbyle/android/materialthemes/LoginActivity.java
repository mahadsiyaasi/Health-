package com.stevenbyle.android.materialthemes;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    String URL= "http://172.17.10.38/login";

    JSONParser jsonParser=new JSONParser();

    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin =  (Button) findViewById(R.id.btnlogin);
        final TextInputEditText email = (TextInputEditText)findViewById(R.id.emailedit);
        final TextInputEditText password = (TextInputEditText)findViewById(R.id.passwordedit);
        final TextInputLayout emailcover = (TextInputLayout) findViewById(R.id.email);
        final TextInputLayout passcover = (TextInputLayout) findViewById(R.id.password);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid(email,emailcover,"Enter valid email") && validateEditText(password,"can not leave blank password email",passcover)){
                    AttemptLogin attemptLogin= new AttemptLogin();
                    attemptLogin.execute(email.getText().toString(),password.getText().toString(),password.getText().toString());
                }
            }
        });

    }
    private boolean validateEditText(TextInputEditText s,String msg,TextInputLayout cover) {
        if (!TextUtils.isEmpty(s.getText())) {
            cover.setError(null);
                return true;
        }
        else{
            cover.setError(msg.toString());
            return false;
        }

    }
    public boolean isEmailValid(TextInputEditText emailcontainner,TextInputLayout cover,String msg){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailcontainner.getText().toString());
        if (!TextUtils.isEmpty(emailcontainner.getText()) && matcher.matches()) {
         return  matcher.matches();
        }else
            cover.setError(msg.toString());
        return false;

    }
    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String email = args[2];
            String password = args[1];
            String name= args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}