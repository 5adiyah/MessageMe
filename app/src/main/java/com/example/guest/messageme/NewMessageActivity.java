package com.example.guest.messageme;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
                .getReference();

        Query q = mMessageReference.orderByKey();

        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Message newMessage = ds.getValue(Message.class);
                    String userKey = ds.getKey();
                    Log.d("ksjhgjdfhg", userKey);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




//                addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
//                    String message = messageSnapshot.getValue().toString();
//                    Log.d("Message", message); //Right now I'm just logging, I need to write it to my app tho
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        ButterKnife.bind(this);
        mSendMessage.setOnClickListener(this);
        mMessageText.setOnClickListener(this);
        mLogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mSendMessage){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userId = user.getUid();
            DatabaseReference messageRef = FirebaseDatabase
                    .getInstance()
                    .getReference("messages")
                    .child(userId);


            String messageText = mMessageText.getText().toString();
            String sender = userId;
            String recipient = "dunno yet";

            Message message = new Message(messageText, sender, recipient);
            messageRef.child("messages");
            messageRef.push().setValue(message);

            Intent intent = new Intent(NewMessageActivity.this, MainActivity.class);
            intent.putExtra("message", messageText);
            startActivity(intent);


        } else if(v==mLogo){
            Intent intent = new Intent(NewMessageActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        mMessageReference.removeEventListener(mMessageReferenceListener);
    }
}


//Type in message, click the add button, that starts the saveMessageToFirebase method which saves it and then takes you to back to MessagesAcvity
//Then you should be able to see the message on Messages activity

