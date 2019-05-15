package com.kryptoblocks.rewardx2019.utils;

import com.kryptoblocks.rewardx2019.pojo.GetAllIncentives;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static String toJSon(GetAllIncentives getAllIncentives) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
           /* jsonObj.put("name", getAllIncentives.getData().get); // Set the first name/pair
            jsonObj.put("surname", person.getSurname());

            JSONObject jsonAdd = new JSONObject(); // we need another object to store the address
            jsonAdd.put("address", person.getAddress().getAddress());
            jsonAdd.put("city", person.getAddress().getCity());
            jsonAdd.put("state", person.getAddress().getState());

            // We add the object to the main object
            jsonObj.put("address", jsonAdd);*/

            // and finally we add the phone number
            // In this case we need a json array to hold the java list
            JSONArray jsonArr = new JSONArray();


            for (int i = 0; i < jsonArr.length(); i++) {
               // JSONObject pnObj = new JSONObject();
                JSONObject pnObj = jsonArr.getJSONObject(i);
                pnObj.put("name", getAllIncentives.getData().get(i).getName());
                pnObj.put("points", getAllIncentives.getData().get(i).getProductSpecificPoints());
                jsonArr.put(pnObj);
            }

            jsonObj.put("rewardDetails", jsonArr);

            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
