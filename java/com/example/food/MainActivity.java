package com.example.food;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "category";
    EditText editText;
    ImageView btnSearch;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.search_button);
        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritesWebView.class);
            MainActivity.this.startActivity(intent);
        });

    }
    public void Breakfast(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"0");
        MainActivity.this.startActivity(intent);
        
    }
    public void Starter(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"1");
        MainActivity.this.startActivity(intent);

    }
    public void Side(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"2");
        MainActivity.this.startActivity(intent);

    }
    public void Meat(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"3");
        MainActivity.this.startActivity(intent);

    }
    public void Pasta(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"4");
        MainActivity.this.startActivity(intent);

    }
    public void Pizza(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"5");
        MainActivity.this.startActivity(intent);

    }
    public void Salad(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"6");
        MainActivity.this.startActivity(intent);

    }
    public void Vegetarian(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"7");
        MainActivity.this.startActivity(intent);

    }
    public void Vegan(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"8");
        MainActivity.this.startActivity(intent);

    }
    public void SeaFood(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"9");
        MainActivity.this.startActivity(intent);

    }
    public void Dessert(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"10");
        MainActivity.this.startActivity(intent);

    }
    public void Miscellaneous(View view){
        Intent intent = new Intent(MainActivity.this,category_activity.class);
        intent.putExtra(EXTRA_CATEGORY,"11");
        MainActivity.this.startActivity(intent);

    }
    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this,Upload_Recipe.class));
    }
    public void favorites(View view)
    {
        Intent intent = new Intent(MainActivity.this,FavoriteList.class);
        MainActivity.this.startActivity(intent);
    }


}