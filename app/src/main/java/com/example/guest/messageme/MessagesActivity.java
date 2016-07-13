package com.example.guest.messageme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessagesActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newMessage) ImageView mNewMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ButterKnife.bind(this);

        mNewMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mNewMessage){
            Intent intent = new Intent(MessagesActivity.this, NewMessageActivity.class);
            startActivity(intent);
        }
    }
}
