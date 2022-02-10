package com.example.food;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class UpdateRecipeActivity extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_description,txt_price,txt_category, txt_ingredient;
    TextView txt_error;
    String imageUrl;
    String key,oldImageUrl;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String recipeName,recipeDescription,recipePrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);

        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);
        txt_name = (EditText)findViewById(R.id.txt_recipe_name);
        txt_description = (EditText)findViewById(R.id.text_description);
        txt_category = findViewById(R.id.text_category);
        txt_ingredient=findViewById(R.id.text_ingredient);
        txt_error=findViewById(R.id.error);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            Glide.with(UpdateRecipeActivity.this)
                    .load(bundle.getString("oldImageUrl"))
                    .into(recipeImage);
            txt_name.setText(bundle.getString("recipeNameKey"));
            txt_description.setText(bundle.getString("descriptionKey"));
            txt_ingredient.setText(bundle.getString("IngredientKey"));
            txt_category.setText(bundle.getString("CategoryKey"));
            key = bundle.getString("key");
            oldImageUrl = bundle.getString("oldImageUrl");
        }


        databaseReference = FirebaseDatabase.getInstance().getReference(txt_category.toString()).child(key);



    }

    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            uri = data.getData();
            recipeImage.setImageURI(uri);

        }
        else Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();

    }


    public void btnUpdateRecipe(View view) {

         recipeName = txt_name.getText().toString().trim();
         recipeDescription = txt_description.getText().toString().trim();
         recipePrice = txt_price.getText().toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading....");
        progressDialog.show();
        storageReference = FirebaseStorage.getInstance()
                .getReference().child("RecipeImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });


    }

    public void uploadRecipe(){
        FoodData foodData = new FoodData(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                txt_ingredient.getText().toString(),imageUrl, txt_category.getText().toString()
        );
        String check = txt_category.getText().toString();
        List<String> string = Arrays.asList("Breakfast","Starter","Side","Meat",
                "Pasta","Pizza","Salad","Vegetarian","Vegan","Seafood","Dessert","Miscellaneous");

        if(string.contains(check))
        {
            String myCurrentDateTime = DateFormat.getDateTimeInstance()
                    .format(Calendar.getInstance().getTime());

            FirebaseDatabase.getInstance().getReference(txt_category.getText().toString())
                    .child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        Toast.makeText(UpdateRecipeActivity.this, "Recipe Uploaded", Toast.LENGTH_SHORT).show();

                        finish();

                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateRecipeActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{txt_error.setText("please write category starting with capital letter");}

    }
}
