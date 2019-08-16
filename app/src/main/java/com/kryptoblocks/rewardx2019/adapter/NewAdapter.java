package com.kryptoblocks.rewardx2019.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.kryptoblocks.rewardx2019.AddProgramActivity.business_vendor_name;


public class NewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<GetAllVendorsData> modellist;
    ArrayList<GetAllVendorsData> arrayList;
    public static String vendor_selected;

    NewAdapter newAdapter;


    public NewAdapter(Context context, List<GetAllVendorsData> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<GetAllVendorsData>();
        this.arrayList.addAll(modellist);
    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_vendor_names, null);

            //locate the views in row.xml
            holder.mTitleTv = view.findViewById(R.id.vendor_names_list);
            holder.img_view = view.findViewById(R.id.vendor_image);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        //set the results into textviews
        holder.mTitleTv.setText(modellist.get(i).getVendorName());
        //String img_url = modellist.get(i).getHostUrl();
        Glide.with(mContext).load(modellist.get(i).getLogoLink()).error(R.drawable.ic_launcher_foreground).into(holder.img_view);
        //System.out.print("vendor url image-----------" + img_url);




        //listview item clicks
       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later
                vendor_selected = modellist.get(i).getVendorName();
                business_vendor_name.setText(vendor_selected);
              //  vendor_id = modellist.get(i).getVendorUuid();

               // holder.img_view.setImageDrawable(modellist.get(i).getHostUrl());
                //Picasso.with(mContext).load(modellist.get(i).getHostUrl()).into(holder.img_view);
                //business_vendor_id.setFocusable(false);
                modellist.clear();
                System.out.println("vendor name-----------" + vendor_selected);

                //Toast.makeText(mContext, "Clicked on item--------" + vendor_selected, Toast.LENGTH_SHORT).show();
            }
        });*/

        return view;
    }


    public class ViewHolder{
        TextView mTitleTv;
        ImageView img_view;

    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayList);
        }
        else {
            for (GetAllVendorsData model : arrayList){
                if (model.getVendorName().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


}
