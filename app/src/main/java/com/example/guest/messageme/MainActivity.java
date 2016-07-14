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
    @Bind(R.id.newMessage) ImageView mNewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNewMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mNewMessage){
            Intent intent = new Intent(MainActivity.this, NewMessageActivity.class);
            startActivity(intent);
        }
    }
}

//Start here, click on messages button and that takes you to Messages Activity