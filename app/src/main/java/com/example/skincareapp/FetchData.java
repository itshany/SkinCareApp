package com.example.skincareapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FetchData {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseRef;
    List<SkinCareInfo> data = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<SkinCareInfo> data, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FetchData() {
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference("RecyclerView");
    }

    public void readData(final DataStatus dataStatus){
         mDatabaseRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 data.clear();
                 List<String> keys = new ArrayList<>();
                 for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                     keys.add(keyNode.getKey());
                     SkinCareInfo info = keyNode.getValue(SkinCareInfo.class);
                     data.add(info);
                 }
                 dataStatus.DataIsLoaded(data, keys);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
    }
}
