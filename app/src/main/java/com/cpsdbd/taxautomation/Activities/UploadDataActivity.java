package com.cpsdbd.taxautomation.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cpsdbd.taxautomation.Model.Entity;
import com.cpsdbd.taxautomation.R;
import com.cpsdbd.taxautomation.Utility.CSVReader;
import com.cpsdbd.taxautomation.Utility.MyDatabaseRef;

import java.io.InputStream;
import java.util.List;

public class UploadDataActivity extends BaseActivity implements View.OnClickListener {

    private Button btnGetData,btnUploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);

        btnGetData = findViewById(R.id.get_data);
        btnUploadImage = findViewById(R.id.upload_image);
        btnGetData.setOnClickListener(this);
        btnUploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.get_data:
                InputStream inputStream = getResources().openRawResource(R.raw.map);

                CSVReader csvReader = new CSVReader(inputStream,this);

                csvReader.read();
                break;

            case R.id.upload_image:

                InputStream inputStream2 = getResources().openRawResource(R.raw.images);

                CSVReader csvReader2 = new CSVReader(inputStream2,this);

                csvReader2.readImage();
                break;
        }





    }
}
