package com.example.guest.messageme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        mMessageReference.addValueEventListener(new ValueEventListener() {
            @Override //called whenever data at the specified node changes.
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
                    String message = messageSnapshot.getValue().toString();
                    Log.d("Message", message);
                }
            }

            @Override //called if the listener is unsuccessful for any reason.
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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


