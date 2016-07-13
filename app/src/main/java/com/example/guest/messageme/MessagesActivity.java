package com.example.guest.messageme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessagesActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mMessageReference;
    private ValueEventListener mMessageReferenceListener;
    private FirebaseRecyclerAdapter mAdapter;

    View mView;
    Context mContext;
    ArrayList<Message> array = new ArrayList<>();

    @Bind(R.id.newMessage) ImageView mNewMessage;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ButterKnife.bind(this);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MESSAGE);

        mAdapter = new FirebaseRecyclerAdapter<Message, FirebaseViewHolder>
                (Message.class, R.layout.activity_messages_list, FirebaseViewHolder.class, ref) {

            @Override
            protected void populateViewHolder(FirebaseViewHolder viewHolder, Message model, int position) {
                viewHolder.bindMessage(model);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        Query q = ref.orderByKey();


        mNewMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mNewMessage){
            Intent intent = new Intent(MessagesActivity.this, NewMessageActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAdapter.cleanup();
    }

}

