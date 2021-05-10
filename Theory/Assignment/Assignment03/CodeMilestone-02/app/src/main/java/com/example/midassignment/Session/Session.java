package com.example.midassignment.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String  sharedPref = "session";
    String sessionKey = "sessionKey";


    public Session(Context context){
        mSharedPreferences = context.getSharedPreferences(sharedPref,context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

    }

    public void sessionSave(User user){

        String email = user.getEmail();
        mEditor.putString(sessionKey,email).commit();

    }

    public String getSession(){

        return mSharedPreferences.getString(sessionKey,"");

    }

    public void sessionRemove(){

        mEditor.putString(sessionKey,"").commit();

    }
}
