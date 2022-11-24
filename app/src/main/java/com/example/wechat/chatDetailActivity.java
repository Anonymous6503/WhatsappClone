package com.example.wechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wechat.Adapters.chatAdapter;
import com.example.wechat.databinding.ActivityChatDetailBinding;
import com.example.wechat.model.MessagesModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        fauth = FirebaseAuth.getInstance();

        final String senderId = fauth.getUid();
        String receiverId = getIntent().getStringExtra("UserID");
        String userName = getIntent().getStringExtra("UserName");
        String profilepic = getIntent().getStringExtra("Profile Pic");

        binding.userName.setText(userName);
        Picasso.get().load(profilepic).placeholder(R.drawable.ic_default_profile_picture).into(binding.profileImage);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatDetailActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        final ArrayList<MessagesModel> messagesModel = new ArrayList<>();

        final chatAdapter ChatAdapter = new chatAdapter(messagesModel,this);
        binding.chatRecyclerView.setAdapter(ChatAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(linearLayoutManager);

        final String senderRoom=senderId+receiverId;
        final String receiverRoom = receiverId+senderId;

        database.getReference().child("Chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesModel.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    MessagesModel model = dataSnapshot.getValue(MessagesModel.class);

                    messagesModel.add(model);
                }
                ChatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.messageBox.getText().toString();

                final MessagesModel model = new MessagesModel(senderId,message);
                model.setTimeStamp(new Date().getTime());
                binding.messageBox.setText("");

                database .getReference().child("Chats").child(senderRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.getReference().child("Chats").child(receiverRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                    }
                });
            }
        });
    }
}