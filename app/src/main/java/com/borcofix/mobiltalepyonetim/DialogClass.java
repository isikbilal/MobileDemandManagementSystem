package com.borcofix.mobiltalepyonetim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.borcofix.mobiltalepyonetim.Models.Result;
import com.borcofix.mobiltalepyonetim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogClass {

    Activity activity;
    Context context;

    public DialogClass(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;

    }


}
