package com.example.sanchit.scraplabs.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.sanchit.scraplabs.R;
import com.example.sanchit.scraplabs.RequestHandler.CustomRequest;
import com.example.sanchit.scraplabs.SharedPrefrences.UserLocalStore;
import com.example.sanchit.scraplabs.Utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    UserLocalStore userlocalstore;
    int flag = 0;

    EditText eno, password;
    ImageView seepassword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        eno = (EditText) findViewById(R.id.eno);
        password = (EditText) findViewById(R.id.password);
        textView = (TextView)findViewById(R.id.login_edit);
        seepassword = (ImageView)findViewById(R.id.imageView2);


        userlocalstore = new UserLocalStore(this);

        if (userlocalstore.getuserloggedIn()) {
            Intent i = new Intent(MainActivity.this, Home.class);
            startActivity(i);
        }

        eno.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(eno, 0);
            }
        }, 200);


        seepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {

                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    flag = 1;
                    Resources res = getResources();
                    seepassword.setImageDrawable(res.getDrawable(R.drawable.ic_custom_hide));

                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = 0;
                    Resources res = getResources();
                    seepassword.setImageDrawable(res.getDrawable(R.drawable.ic_custom_show));

                }
            }
        });


    }


    public void login(View v) {

        Toast.makeText(MainActivity.this, "Clicked",Toast.LENGTH_SHORT).show();
        System.out.println("Clicked");

            hideSoftKeyboard();

            if (new Util().check_connection(MainActivity.this)) {

                if (validate()) {

                    String enos = eno.getText().toString();
                    String passwords = password.getText().toString();

                    authenticate(enos, passwords);
                } else {
/*
                    if (eno.getText().toString().trim().equals("")) {
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .playOn(findViewById(R.id.eno));
                    }
                    if (password.getText().toString().trim().equals("")) {
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .playOn(findViewById(R.id.password));
                    }
*/

                }
            } else {

                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("No Internent Connection")
                        .setContentText("Won't be able to login!")
                        .setConfirmText("Go to Settings!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                startActivity(new Intent(Settings.ACTION_SETTINGS));
                                sDialog.cancel();
                            }
                        })
                        .show();

            }
    }

    void authenticate(String eno,String password){

        final AlertDialog dialog = new SpotsDialog(this, R.style.Custom);
        dialog.show();

/*for time being till no db*/

        userlocalstore.setUserloggedIn(true);
        userlocalstore.userData(eno.toString());
        Intent i = new Intent(MainActivity.this,Home.class);
        startActivity(i);
/*till here*/


        String url = "http://yashgupta.96.lt/lrc/login.php";
        Map<String, String> params = new HashMap<String, String>();
        params.put("eno",eno);
        params.put("password",password);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response: ", response.toString());
                System.out.println("Response" + response.toString());
                dialog.dismiss();

                response_is(response);

                //return response;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    dialog.hide();
                    new Util().showerrormessage(MainActivity.this, "Time Out Error.....Try Later!!!");

                } else if (error instanceof AuthFailureError) {
                    dialog.hide();
                    new Util().showerrormessage(MainActivity.this, "Authentication Error.....Try Later!!!");
                } else if (error instanceof ServerError) {
                    dialog.hide();
                    new Util().showerrormessage(MainActivity.this,"Server Error.....Try Later!!!");
                } else if (error instanceof NetworkError) {
                    dialog.hide();
                    new Util().showerrormessage(MainActivity.this, "Network Error.....Try Later!!!");
                } else if (error instanceof ParseError) {
                    dialog.hide();
                    Log.d("Response: ", error.toString());
                    System.out.println("Resonse" + error.toString());
                    new Util().showerrormessage(MainActivity.this, "Unknown Error.....Try Later!!!");
                }
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);
    }


    void response_is(JSONArray response){

        try {

            JSONObject jsonObject = response.getJSONObject(0);

            String result = jsonObject.getString("response");

            if(result.equals("Success")){
                userlocalstore.setUserloggedIn(true);
                userlocalstore.userData(eno.toString());
                Intent i = new Intent(MainActivity.this,Home.class);
                startActivity(i);

            }
            else{
                new Util().showerrormessage(MainActivity.this,result);
            }

        } catch (JSONException e) {
        }
    }

    private boolean validate() {
        return !eno.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("");
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}