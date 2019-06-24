package com.example.viewpagert2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Tab2 extends Fragment{

    private RecyclerView myrecyclerview;
    private List<Contact> lstContact;
    private RecyclerViewAdapter recycleradapter;
    View v;

    //contact tab
    public Tab2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.tab2, container,false);
        setHasOptionsMenu(true);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.contact_recycler);
        recycleradapter = new RecyclerViewAdapter(getContext(),lstContact);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleradapter);
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        createdata();
    }

    public void createdata(){
        lstContact = new ArrayList<>();
        lstContact.add(new Contact("tan1","+601912931629",R.drawable.image_1));
        lstContact.add(new Contact("tan2","+601123213213",R.drawable.image_2));
        lstContact.add(new Contact("Userchen1","+601345435345",R.drawable.image_3));
        lstContact.add(new Contact("Userchen2","+601456546546",R.drawable.image_4));
        lstContact.add(new Contact("Usernick1","+601678768768",R.drawable.image_1));
        lstContact.add(new Contact("Usernick2","+601238726523",R.drawable.image_2));
        lstContact.add(new Contact("test7","+601273657265",R.drawable.image_3));
        lstContact.add(new Contact("test8","+601766574532",R.drawable.image_4));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        inflater.inflate(R.menu.searchbar, menu);
//        super.onCreateOptionsMenu(menu, inflater);

                    //MenuItem searchitem = ()menu.findItem(R.id.search_bar);
                    Log.d("inmou fcker","onCreateOptionsMenu: ");
            SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();

            //searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
            public boolean onQueryTextChange(String newText) {
                recycleradapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
