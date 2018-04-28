package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        //ImageView imageView = (ImageView)findViewById(R.id.image_iv);
       // imageView.setImageURI(Uri.parse(sandwich.getImage()));
        TextView mainNameView = findViewById(R.id.main_name_display);
        mainNameView.setText(sandwich.getMainName());
        TextView alsoKnownAsView = (TextView)findViewById(R.id.also_known_as_display);

        if(sandwich.getAlsoKnownAs()!=null){

        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++){
            alsoKnownAsView.append((sandwich.getAlsoKnownAs().get(i)));
            if(!(sandwich.getAlsoKnownAs().size()-1==i)){//if not last item
                alsoKnownAsView.append(", ");
            }
        }

        }
        TextView placeOfOriginView = findViewById(R.id.place_of_origin_display);
        placeOfOriginView.setText(sandwich.getPlaceOfOrigin());
        TextView descriptionView = findViewById(R.id.description_display);
        descriptionView.setText(sandwich.getDescription());

        TextView ingredientsView = (TextView)findViewById(R.id.ingredients_display);
        if(sandwich.getIngredients()!=null){
        for(int i=0;i<sandwich.getIngredients().size();i++){
            ingredientsView.append((sandwich.getIngredients().get(i)));
            if(!(sandwich.getIngredients().size()-1==i)){//if not last item
                ingredientsView.append(", ");
            }
        }
        }

    }
}
