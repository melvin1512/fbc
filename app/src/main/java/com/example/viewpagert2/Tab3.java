package com.example.viewpagert2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

public class Tab3 extends Fragment {

    Button ch,up;
    ImageView img;
    StorageReference mStorageRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3, container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ch = (Button) ch.findViewById(R.id.selectimg);
        up = (Button) up.findViewById(R.id.saveimg);
        img = (ImageView) img.findViewById(R.id.imgview);

        ch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Filechooser();
            }
        });
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
//        if (requestCode == 1 && resultCode == -1 && data != null && data.getData()){
//
//        }
    }
}
