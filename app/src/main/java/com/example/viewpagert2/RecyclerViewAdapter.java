package com.example.viewpagert2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    Context mContext;
    List<Contact> mData;
    Dialog myDialog;
    List<Contact> fullmdata;

    public RecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
        fullmdata = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog initialization
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_contact);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView dialogname = (TextView) myDialog.findViewById(R.id.dialog_nameid);
                TextView dialogphoneno = (TextView) myDialog.findViewById(R.id.dialog_phoneid);
                ImageView dialogimg = (ImageView) myDialog.findViewById(R.id.dialog_img);
                dialogname.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialogphoneno.setText(mData.get(vHolder.getAdapterPosition()).getPhone());
                dialogimg.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                //Toast.makeText(mContext,"Test Click" + String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_name.setText(mData.get(position).getName());
        holder.txt_phone.setText(mData.get(position).getPhone());
        holder.img.setImageResource(mData.get(position).getPhoto());

    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredlist = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredlist.addAll(fullmdata);
            }
            else{
                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Contact item : fullmdata){
                    if(item.getName().toLowerCase().contains(filterpattern)){
                        filteredlist.add(item); }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_name;
        private TextView txt_phone;
        private ImageView img;

        private LinearLayout item_contact;

        public MyViewHolder(View itemView){
            super(itemView);

            item_contact = (LinearLayout) itemView.findViewById(R.id.contact_item);

            txt_name = (TextView) itemView.findViewById(R.id.namecontact);
            txt_phone = (TextView) itemView.findViewById(R.id.phonecontact);
            img = (ImageView) itemView.findViewById(R.id.imgcontact);
        }
    }
}
