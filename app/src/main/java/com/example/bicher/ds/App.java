package com.example.bicher.ds;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

public class App extends Application {
    static private Picasso instance;

    public static Picasso getInstancePicasso(Context context)
    {
        if(instance == null)
        {
            instance = new Picasso.Builder(context)
                .executor(Executors.newSingleThreadExecutor())
                .memoryCache(Cache.NONE)
                .indicatorsEnabled(true)
                .build();
        }
        return instance;
    }

}
