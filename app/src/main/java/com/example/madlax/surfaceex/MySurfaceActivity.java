package com.example.madlax.surfaceex;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by madlax on 2017/03/24.
 */

public class MySurfaceActivity extends Activity {//Activityクラスの継承
    @Override //onCreateのオーバーライドを行う
    public void onCreate(Bundle savedInstanceState){//onCreateのオーバーライド内容
        super.onCreate(savedInstanceState);//継承元のonCreateメソッドの実行
        setContentView(new MySurfaceView(this));//setContentView(Viewクラス)  Viewを呼び出すメソッド
    }
}
