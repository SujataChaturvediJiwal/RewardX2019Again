package com.kryptoblocks.rewardx2019.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kryptoblocks.rewardx2019.R;
import com.kryptoblocks.rewardx2019.pojo.ActivitiesPojo;
import com.kryptoblocks.rewardx2019.pojo.GetRecentActivitiesData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.MyViewHolder>{

    Context mContext;
   List<GetRecentActivitiesData> activitiesPojoList;
   List<GetRecentActivitiesData> pojoListAct;
    public String activities_date, activities_converted_date;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView number_tokens, company_name, activity_type, act_date;


        public MyViewHolder(View view) {
            super(view);

            number_tokens = view.findViewById(R.id.activity_token_value);
            company_name = view.findViewById(R.id.activity_company_name);
           // activity_type = view.findViewById(R.id.activity_type);
            act_date = view.findViewById(R.id.activity_date);

        }
    }


    public ActivitiesAdapter(Context mContext, List<GetRecentActivitiesData> activitiesPojos) {
        this.mContext = mContext;
        this.activitiesPojoList = activitiesPojos;
    }
    @Override
    public ActivitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_activities_layout, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ActivitiesAdapter.MyViewHolder holder, int position) {
        GetRecentActivitiesData list = activitiesPojoList.get(position);
//       GetRecentActivitiesData ss = pojoListAct.get(position);

        if(list.getRedeemPoints()!=null) {
            activities_date = String.valueOf(list.getDateTimeAllocated());
            dateConverter();
            holder.number_tokens.setText(String.valueOf(list.getRedeemPoints()));
            //holder.number_tokens.setText(String.valueOf(list.getRedeemPoints()));
            // holder.company_name.setText(String.valueOf(list.get(position).getLoginName()));
            holder.company_name.setText("You redeemed");
           // holder.activity_type.setText(String.valueOf(list.getRemarks()));
            holder.act_date.setText(activities_converted_date);
        }
        else if(list.getRewardPoints()!=null)
        {
            activities_date = String.valueOf(list.getDateTimeAllocated());
            dateConverter();
            holder.number_tokens.setText(String.valueOf(list.getRewardPoints()));
            //holder.number_tokens.setText(String.valueOf(list.getRedeemPoints()));
            // holder.company_name.setText(String.valueOf(list.get(position).getLoginName()));
            holder.company_name.setText("You have been rewarded with");
           // holder.activity_type.setText(String.valueOf(list.getRemarks()));
            holder.act_date.setText(activities_converted_date);
        }

        //System.out.println("Size of activities adapter------"+pojoListAct.size());

        }

    @Override
    public int getItemCount() {
        return activitiesPojoList.size();
    }


    void dateConverter() {
            DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

            long milliSeconds= Long.parseLong(activities_date);
            System.out.println(milliSeconds);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            activities_converted_date = formatter.format(calendar.getTime());
            System.out.println("Date----"+activities_converted_date);
        }


   /* void dateConverter() {
        // Toast.makeText(mContext, "date function called", Toast.LENGTH_SHORT).show();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            Date d = sdf.parse(activities_date);
            // Toast.makeText(mContext, "date-========="+d, Toast.LENGTH_SHORT).show();
            sdf = new SimpleDateFormat("dd MMM yyyy");
            Integer timeInMilliseconds = d.getDate();
            System.out.println("date-----"+timeInMilliseconds);
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
    }*/


}
