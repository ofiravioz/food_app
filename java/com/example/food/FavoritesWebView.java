package com.example.food;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class FavoritesWebView extends AppCompatActivity {
    private WebView webView;
    private Button save;
    private String URL,baseurl;
    EditText txt_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        save = findViewById(R.id.Save);
        webView= findViewById(R.id.webview);
        Bundle mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            baseurl= mBundle.getString("URL");
        }
        else
        {
            baseurl="https://www.google.com";
        }
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(baseurl);
        txt_name= findViewById(R.id.search);

        save.setOnClickListener(v -> {
            URL = webView.getUrl();
            Save();
        });


    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    public void Save(){
        FavoriteData favoritedata=new FavoriteData(txt_name.getText().toString(),URL);

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Favorites")
                .child(myCurrentDateTime).setValue(favoritedata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(FavoritesWebView.this, "Recipe Uploaded", Toast.LENGTH_SHORT).show();

                    finish();

                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FavoritesWebView.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}