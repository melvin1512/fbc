package com.example.viewpagert2;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.lang.ref.Reference;
import java.sql.Ref;

import static android.app.Activity.RESULT_OK;

public class Tab3 extends Fragment {

    //choose % upload btn
    Button ch,up;
    ImageView img;
    ImgData data;

    //FB Lib
    StorageReference mStorageRef;
    DatabaseReference mDBRef;

    public Uri imguri;
    public StorageTask uploadtask;
    EditText txtname, txtdesc1, txtdesc2;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.tab3, container,false);

        mStorageRef = FirebaseStorage.getInstance().getReference("Images");
        mDBRef = FirebaseDatabase.getInstance().getReference().child("ImgData");

        ch = (Button) v.findViewById(R.id.selectimg);
        up = (Button) v.findViewById(R.id.saveimg);
        img = (ImageView) v.findViewById(R.id.imgview);
        txtname = (EditText) v.findViewById(R.id.imgname);
        txtdesc1 = (EditText) v.findViewById(R.id.imgdesc);
        txtdesc2 = (EditText) v.findViewById(R.id.imgdesc2);
        data = new ImgData();

        ch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Filechooser();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uploadtask != null && uploadtask.isInProgress()){
                    Toast.makeText(getActivity(),"Uploading",Toast.LENGTH_LONG).show();

                }
                else{

                }
                Fileuploader();
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private String getExtension(Uri uri){
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void Fileuploader(){
        if(imguri != null && txtname != null && txtdesc1 != null && txtdesc2 != null) {
            String imgid;
            imgid = System.currentTimeMillis() + "." + getExtension(imguri);
            data.setName(txtname.getText().toString().trim());
            data.setDesc1(txtdesc1.getText().toString().trim());
            data.setDesc2(txtdesc2.getText().toString().trim());
            data.setImgid(imgid);

            mDBRef.push().setValue(data);

            StorageReference storageReference = mStorageRef.child(imgid);

            uploadtask = storageReference.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Toast.makeText(getActivity(), "Image Upload Success", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getActivity(), "Image Upload Failed, Pls try agn", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        else {
            Toast.makeText(getActivity(),"Pls fill in the details",Toast.LENGTH_LONG).show();
        }

    }

    private void Filechooser(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData()!=null){
            imguri = data.getData();
            img.setImageURI(imguri);

        }
    }
}
