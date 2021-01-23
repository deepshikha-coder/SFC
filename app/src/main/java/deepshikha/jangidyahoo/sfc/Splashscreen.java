package deepshikha.jangidyahoo.sfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(Splashscreen.this,mainActivity.class);
                startActivity(i);
                finish();
            }}, 3000);
    }

}
