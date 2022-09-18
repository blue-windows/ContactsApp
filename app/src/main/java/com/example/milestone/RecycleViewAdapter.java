package com.example.milestone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    List<BaseContact> contactList;
    List<BaseContact> removedFromContactList = new ArrayList<>();
    Context context;

    public RecycleViewAdapter(List<BaseContact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_one_line_contact , parent , false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_contactNumber.setText(contactList.get(position).getPhoneNumber());
        holder.tv_contactName.setText(contactList.get(position).getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send control to either the business or personal contact add/edit activity
                if (contactList.get(position).getIsBusinessContact() == true) {
                    Intent intent = new Intent(context, view_business_contact.class);
                    intent.putExtra("id", position);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, view_personal_contact.class);
                    intent.putExtra("id", position);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_contactName, tv_contactNumber;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_contactName = itemView.findViewById(R.id.tv_contactName);
            tv_contactNumber = itemView.findViewById(R.id.tv_contactNumber);
            parentLayout = itemView.findViewById(R.id.oneLineContactLayout);
        }
    }


    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<BaseContact> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(contactList);
                filteredList.addAll(removedFromContactList);
                removedFromContactList.clear();
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (BaseContact item : contactList) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    else {
                        removedFromContactList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contactList.clear();
            contactList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
