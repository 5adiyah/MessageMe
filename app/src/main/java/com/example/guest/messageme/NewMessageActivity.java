package com.example.guest.messageme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewMessageActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mMessageReference;
    private ValueEventListener mMessageReferenceListener;

    @Bind(R.id.sendMessage) ImageView mSendMessage;
    @Bind(R.id.logo) ImageView mLogo;
    @Bind(R.id.messageText) EditText mMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        mMessageReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_MESSAGE);

        mMessageReferenceListener = mMessageReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
                    String message = messageSnapshot.getValue().toString();
                    Log.d("Message", message); //Right now I'm just logging, I need to write it to my app tho
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ButterKnife.bind(this);
        mSendMessage.setOnClickListener(this);
        mMessageText.setOnClickListener(this);
        mLogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mSendMessage){
            String message = mMessageText.getText().toString();
            saveMessageToFirebase(message);

            Intent intent = new Intent(NewMessageActivity.this, MainActivity.class);
            intent.putExtra("message", message);
            startActivity(intent);
        } else if(v==mLogo){
            Intent intent = new Intent(NewMessageActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void saveMessageToFirebase(String messageText){
        Message message = new Message(messageText);
        mMessageReference.push().setValue(message);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mMessageReference.removeEventListener(mMessageReferenceListener);
    }
}


//Type in message, click the add button, that starts the saveMessageToFirebase method which saves it and then takes you to back to MessagesAcvity
//Then you should be able to see the message on MessagesActivity

