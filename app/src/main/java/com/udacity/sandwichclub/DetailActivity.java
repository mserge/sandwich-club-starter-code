package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final ImageView ingredientsIv = findViewById(R.id.image_iv);
        final ProgressBar progressBar = findViewById(R.id.homeprogress);

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
                .into(ingredientsIv, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        //DONE: show some error
                        Toast.makeText(DetailActivity.this, R.string.error_show_image, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        //DONE: DetailActivity shows all Sandwich details correctly
        TextView also_known_tv = findViewById(R.id.also_known_tv);
        final List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        if(alsoKnownAs !=null) {
            also_known_tv.setText(TextUtils.join(", ", alsoKnownAs));
        }
        final TextView ingredients_tv = findViewById(R.id.ingredients_tv);
        final List<String> ingredients = sandwich.getIngredients();
        if(ingredients !=null){
            ingredients_tv.setText(TextUtils.join(", ", ingredients));
        }
        final TextView origin_tv = findViewById(R.id.origin_tv);
        origin_tv.setText(sandwich.getPlaceOfOrigin());

        final TextView description_tv = findViewById(R.id.description_tv);
        description_tv.setText(sandwich.getDescription());
    }
}
