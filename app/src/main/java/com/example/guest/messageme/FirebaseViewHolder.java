package com.example.guest.messageme;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 7/13/16.
 */
public class FirebaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMessage(Message message){
        TextView messageTextView = (TextView) mView.findViewById(R.id.messageTextView);
        messageTextView.setText(message.getMessage());
    }

    @Override public void onClick(View view){
        final ArrayList<Message> messages = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MESSAGE);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    messages.add(snapshot.getValue(Message.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("posts", Parcels.wrap(messages));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}


