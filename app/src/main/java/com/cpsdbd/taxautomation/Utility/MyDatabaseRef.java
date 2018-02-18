package com.cpsdbd.taxautomation.Utility;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sohel on 2/17/2018.
 */

public class MyDatabaseRef {
    private static final String ENTITY_REF = "Entities";
    private static final String ENTITY_IMAGE = "EntitiesImages";



    private FirebaseDatabase database;

    public MyDatabaseRef() {
        this.database = database = FirebaseDatabase.getInstance();
    }

    public DatabaseReference getEntityRef(){
        return database.getReference(ENTITY_REF);
    }

    public DatabaseReference getEntityImageRef(){
        return database.getReference(ENTITY_IMAGE);
    }
}
