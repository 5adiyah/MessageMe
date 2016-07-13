package com.example.guest.messageme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.message) ImageView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mMessage){
            Intent intent = new Intent(MainActivity.this, MessagesActivity.class);
            startActivity(intent);
        }
    }
}
