package com.example.android.articlegureassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ImageButton likeButton;

    //Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListner;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageRefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Firebase Components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child("messages");
        mStorageRefrence = mFirebaseStorage.getReference().child("photos");

        // Initialize references to views
        mMessageListView = (ListView)findViewById(R.id.messageListView);
        likeButton = (ImageButton)findViewById(R.id.likeView);

        // Initialize message ListView and its adapter
        final List<NewMessage> newMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, newMessages);
        mMessageListView.setAdapter(mMessageAdapter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        detachDatabaseReadListner();
        mMessageAdapter.clear();
    }

    @Override
    protected void onResume(){
        super.onResume();
        attachDatabaseReadListner();
    }

    private void attachDatabaseReadListner(){
        if(mChildEventListner == null) {
            mChildEventListner = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    NewMessage newMessage = dataSnapshot.getValue(NewMessage.class);
                    mMessageAdapter.add(newMessage);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
                public void onChildRemoved(DataSnapshot dataSnapshot) { }
                public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
                public void onCancelled(DatabaseError databaseError) { }
            };
            mDatabaseReference.addChildEventListener(mChildEventListner);
        }
    }

    private void detachDatabaseReadListner(){
        if(mChildEventListner != null) {
            mDatabaseReference.removeEventListener(mChildEventListner);
            mChildEventListner = null;
        }
    }
}
