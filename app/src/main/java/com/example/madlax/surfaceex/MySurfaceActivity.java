package com.example.madlax.surfaceex;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by madlax on 2017/03/24.
 */

public class MySurfaceActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }
}
