package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        //DONE: JSON data is parsed correctly to a Sandwich object in JsonUtils
        Sandwich sandwich = new Sandwich();
        try {
            // DONE: JSON is parsed without using 3rd party libraries
            JSONObject sandwitchJson = new JSONObject(json);
            JSONObject nameJson = sandwitchJson.getJSONObject("name");
            if(nameJson != null){
                sandwich.setMainName(nameJson.getString("mainName"));
                sandwich.setAlsoKnownAs(extractArray( nameJson,"alsoKnownAs"));
            }
            sandwich.setImage(sandwitchJson.getString("image"));
            sandwich.setDescription(sandwitchJson.getString("description"));
            sandwich.setPlaceOfOrigin(sandwitchJson.getString("placeOfOrigin"));
            sandwich.setIngredients(extractArray(sandwitchJson, "ingredients"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return sandwich;


    }

    private static List<String> extractArray(JSONObject nameJson, String name) throws JSONException {
        JSONArray arrJson = nameJson.getJSONArray(name);
        if(arrJson != null){

            List<String> strArr = new ArrayList<String>();
            final int knownLength = arrJson.length();
            for (int i = 0; i < knownLength; i++) {
                strArr.add(arrJson.getString(i));
            }
            return strArr;
        }
        return null;
    }

}
