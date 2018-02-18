package com.cpsdbd.taxautomation.Utility;

import android.app.Activity;
import android.util.Log;

import com.cpsdbd.taxautomation.Activities.UploadDataActivity;
import com.cpsdbd.taxautomation.Model.Entity;
import com.cpsdbd.taxautomation.Model.EntityImages;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohel on 2/17/2018.
 */

public class CSVReader {

    private InputStream inputStream;

    private Activity activity;

    UploadDataActivity uploadDataActivity;

    private MyDatabaseRef myDatabaseRef;

    public CSVReader(InputStream inputStream,Activity activity){
        this.inputStream = inputStream;
        this.activity = activity;

        this.myDatabaseRef = new MyDatabaseRef();
    }

    public void read(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {

            if(activity instanceof UploadDataActivity){
                uploadDataActivity = (UploadDataActivity) activity;
            }

            uploadDataActivity.showProgressDialog();


            String csvLine;
            while ((csvLine = reader.readLine()) != null) {

                //Log.d("KKKK",csvLine);
                String[] row = csvLine.trim().split("\\|");

                Log.d("HHHH",row.length+"");


                /*Log.d("HHHH",row[0]);
                Log.d("HHHH",row[1]);
                Log.d("HHHH",row[2]);
                Log.d("HHHH",row[3]);
                Log.d("HHHH",row[4]);
                Log.d("HHHH",row[5]);
                Log.d("HHHH",row[6]);
                Log.d("HHHH",row[7]);
                Log.d("HHHH",row[8]);

                Entity entity = new Entity(row[0],row[1],row[2],row[3],Double.parseDouble(row[4]),Double.parseDouble(row[5]),row[6],Double.parseDouble(row[7]),Integer.parseInt(row[8]));

                resultList.add(entity);*/

                Entity entity = new Entity(row[0],row[1],row[2],row[3],Double.parseDouble(row[4]),Double.parseDouble(row[5]),row[6],Double.parseDouble(row[7]),Integer.parseInt(row[8]));

                myDatabaseRef.getEntityRef().push().setValue(entity, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        databaseReference.child("id").setValue(databaseReference.getKey());
                    }
                });
            }

            uploadDataActivity.hideProgressDialog();
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
    }


    public void readImage(){

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {

            if(activity instanceof UploadDataActivity){
                uploadDataActivity = (UploadDataActivity) activity;
            }

            uploadDataActivity.showProgressDialog();


            String csvLine;
            while ((csvLine = reader.readLine()) != null) {

                //Log.d("KKKK",csvLine);
                String[] row = csvLine.trim().split(",");

                Log.d("HHHH",csvLine);
                Log.d("HHHH",row.length+"");


                EntityImages entityImages = new EntityImages(row[0],row[1]);

                myDatabaseRef.getEntityImageRef().child(entityImages.getEntity_id())
                        .push().setValue(entityImages.getUrl(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                       // databaseReference.child("id").setValue(databaseReference.getKey());
                    }
                });


                /*Log.d("HHHH",row[0]);
                Log.d("HHHH",row[1]);
                Log.d("HHHH",row[2]);
                Log.d("HHHH",row[3]);
                Log.d("HHHH",row[4]);
                Log.d("HHHH",row[5]);
                Log.d("HHHH",row[6]);
                Log.d("HHHH",row[7]);
                Log.d("HHHH",row[8]);

                Entity entity = new Entity(row[0],row[1],row[2],row[3],Double.parseDouble(row[4]),Double.parseDouble(row[5]),row[6],Double.parseDouble(row[7]),Integer.parseInt(row[8]));

                resultList.add(entity);*/

               /* Entity entity = new Entity(row[0],row[1],row[2],row[3],Double.parseDouble(row[4]),Double.parseDouble(row[5]),row[6],Double.parseDouble(row[7]),Integer.parseInt(row[8]));

                myDatabaseRef.getEntityRef().push().setValue(entity, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        databaseReference.child("id").setValue(databaseReference.getKey());
                    }
                });*/
            }

            uploadDataActivity.hideProgressDialog();
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }

    }


}
