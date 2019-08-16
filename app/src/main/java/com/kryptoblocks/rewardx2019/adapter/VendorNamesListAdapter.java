package com.kryptoblocks.rewardx2019.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.GetAllVendorsData;

import java.util.ArrayList;
import java.util.List;

import static com.kryptoblocks.rewardx2019.AddProgramActivity.business_vendor_name;


/*public class VendorNamesListAdapter extends ArrayAdapter<GetAllVendorsData> implements View.OnClickListener {
    private  Context context;
    private ArrayList<GetAllVendorsData> getAllVendorsDataList;
    int layoutResourceId;
    TextView list_text;
    public static String vendor_selected;
    public static String vendor_business_uuid;
    ArrayList<GetAllVendorsData> filteredProductResults;

    public VendorNamesListAdapter(Context mContext, ArrayList<GetAllVendorsData> getAllVendorsData) {

        super(mContext, R.layout.list_vendor_names, getAllVendorsData);

        this.context = mContext;
        this.getAllVendorsDataList = getAllVendorsData;
        this.filteredProductResults = getAllVendorsData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
       final GetAllVendorsData dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
       // ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

           // viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_vendor_names, parent, false);

            list_text = convertView.findViewById(R.id.vendor_names_list);

            result=convertView;

            //convertView.setTag(viewHolder);
        } else {
           // viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        list_text.setText(dataModel.getVendorName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // int position=(Integer) view.getTag();
                // GetAllVendorsData dataModel= getAllVendorsDataList.get(position);
                vendor_selected = dataModel.getVendorName();
                business_vendor_id.setText(vendor_selected);
                business_vendor_id.setFocusable(false);
                vendor_business_uuid = dataModel.getVendorUuid();
                System.out.print("vendor name-----------" + vendor_selected);
                System.out.print("vendor id business adapter-----------" + vendor_business_uuid);
                Toast.makeText(context, "Clicked on item--------" + vendor_selected, Toast.LENGTH_SHORT).show();
            }
        });
        //viewHolder.info.setOnClickListener(this);

        // Return the completed view to render on screen
        return convertView;

    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredProductResults = (ArrayList<GetAllVendorsData>) filterResults.values; // has the filtered values
                notifyDataSetChanged();  // not
            }

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                final FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                filteredProductResults = new ArrayList<GetAllVendorsData>();

                if (getAllVendorsDataList == null) {
                    getAllVendorsDataList = new ArrayList<GetAllVendorsData>(filteredProductResults); // saves the original data in mOriginalValues
                }

                *//********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********//*
                if (charSequence == null || charSequence.length() == 0) {

                    // set the Original result to return
                    results.count = getAllVendorsDataList.size();
                    results.values = getAllVendorsDataList;
                } else {
                    charSequence = charSequence.toString().toLowerCase();
                    for (int i = 0; i < getAllVendorsDataList.size(); i++) {
                        String data = getAllVendorsDataList.get(i).getVendorName();
                        if (data.toLowerCase().startsWith(charSequence.toString())) {
                            filteredProductResults.add(new GetAllVendorsData(getAllVendorsDataList.get(i).getVendorName().toString()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = filteredProductResults.size();
                    results.values = filteredProductResults;
                }
                return results;
            }
        };
        return filter;
    }

    public int getCount() {
        return filteredProductResults.size();
    }
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onClick(View view) {

    }
    }*/

