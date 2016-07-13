package com.example.guest.messageme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewMessageActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mMessageReference;

    @Bind(R.id.sendMessage) ImageView mSendMessage;
    @Bind(R.id.messageText) EditText mMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        mMessageReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_MESSAGE);

        ButterKnife.bind(this);
        mSendMessage.setOnClickListener(this);
        mMessageText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mSendMessage){
            String message = mMessageText.getText().toString();
            saveMessageToFirebase(message);

            Intent intent = new Intent(NewMessageActivity.this, MessagesActivity.class);
            intent.putExtra("message", message);
            startActivity(intent);
        }
    }

    public void saveMessageToFirebase(String location){
        mMessageReference.push().setValue(location);
    }
}


