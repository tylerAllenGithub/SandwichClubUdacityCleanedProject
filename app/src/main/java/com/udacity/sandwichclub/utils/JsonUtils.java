package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject sandwichJsonName = sandwichJson.getJSONObject("name");

            sandwich.setMainName(sandwichJsonName.optString("mainName"));
            sandwich.setAlsoKnownAs((parseJSONArray(sandwichJsonName.getJSONArray("alsoKnownAs"))));
            sandwich.setPlaceOfOrigin(sandwichJson.optString("placeOfOrigin"));
            sandwich.setDescription(sandwichJson.optString("description"));
            sandwich.setImage(sandwichJson.optString("image"));
            sandwich.setIngredients((parseJSONArray(sandwichJson.getJSONArray("ingredients"))));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
    public static List<String> parseJSONArray(JSONArray jsonArray){

        List<String> retArray = new ArrayList<String>(0);
        if(jsonArray==null){
            return retArray;
        }
        for(int i=0; i<jsonArray.length();i++) {
            try {
                retArray.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            return retArray;
    }
}
