package com.example.nsucpcadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddJobActivity extends AppCompatActivity {

    private AutoCompleteTextView deptList;
    private EditText companyName;

    Button selecFile, upload;
    TextView notification;
    Uri pdfUri;

    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);


        deptList = findViewById(R.id.departmentListId);
        companyName = findViewById(R.id.addCompanyNameId);

        firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        selecFile = findViewById(R.id.selectButtonId);
        upload = findViewById(R.id.postAddButtonId);
        notification = findViewById(R.id.fileName);

        databaseReference = FirebaseDatabase.getInstance().getReference("Company Information");


        //DropDown list Start

        String[] genderListString = getResources().getStringArray(R.array.dept_list);
        ArrayAdapter genderlListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genderListString);
        deptList.setAdapter(genderlListAdapter);

//DropDown list Ends


        //uploading Pdf Starts

        selecFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddJobActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else {
                    ActivityCompat.requestPermissions(AddJobActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pdfUri != null)
                    uploadFile(pdfUri);
                else
                    Toast.makeText(AddJobActivity.this, "Select a File", Toast.LENGTH_SHORT).show();


            }
        });

    }


    private void uploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(AddJobActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();

        saveData();

        final String fileName = System.currentTimeMillis() + "";
        StorageReference storageReference = firebaseStorage.getReference();


        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();

//                            String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
//

                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String url = uri.toString();
                                DatabaseReference databaseReference = firebaseDatabase.getReference();


                                databaseReference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()) {
                                            Toast.makeText(AddJobActivity.this, "File Upload Successfully", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                        else {
                                            Toast.makeText(AddJobActivity.this, "File Upload Failed", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });


                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddJobActivity.this, "Error! Something Went Wrong.", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                int currentProgress = (int) (100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

        Intent intent = new Intent(getApplicationContext(), WholeInformationActivity.class);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else {
            Toast.makeText(AddJobActivity.this, "Please Provide Permission...", Toast.LENGTH_SHORT).show();
        }

    }

    private void selectPdf() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            notification.setText("A File is Selected: " + data.getData().getLastPathSegment());
        } else {
            Toast.makeText(AddJobActivity.this, "Please Select a File", Toast.LENGTH_SHORT).show();
        }

    }

    //uploading Pdf Ends


    private void saveData() {

        String name = companyName.getText().toString();
        String dept = deptList.getText().toString();

        boolean allow = true;

        if (name.isEmpty()) {
            companyName.setError("Please Enter The Company Name");
            companyName.requestFocus();
            allow = false;
        }


        if (dept.isEmpty()) {
           deptList.setError("Please Enter The Department");
            deptList.requestFocus();
            allow = false;
        }


        if(allow){
            Admin admin;

            try {
                String key = databaseReference.push().getKey();

                admin = new Admin(companyName.getText().toString(),deptList.getText().toString());

                databaseReference.child(key).setValue(admin);

            }
            catch (Exception e){
                Toast.makeText(this, "There Is a Error", Toast.LENGTH_SHORT).show();

            }
        }

    }

}