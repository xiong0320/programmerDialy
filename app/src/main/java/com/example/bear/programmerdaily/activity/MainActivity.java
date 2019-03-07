 package com.example.bear.programmerdaily.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bear.programmerdaily.R;
import com.example.bear.programmerdaily.fragment.AppInfoFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

 public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            AppInfoFragment fragment = new AppInfoFragment();
            transaction.replace(R.id.main, fragment);
            transaction.commit();
        }
        initView();

        // Example of a call to a native method

    }
    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        TextView prepare = (TextView) findViewById(R.id.prepare);
//        TextView teaching = (TextView) findViewById(R.id.teaching);
//        TextView error_note = (TextView) findViewById(R.id.error_note);
//        TextView person_profile = (TextView) findViewById(R.id.person_profile);
//        TextView after_class = (TextView) findViewById(R.id.after_class);
//        prepare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toPrepare();
//            }
//        });
//        teaching.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toTeacheing();
//            }
//        });
//        error_note.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toErrorNote();
//            }
//        });
//        person_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toPersonProfile();
//            }
//        });
//        after_class.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toAfterClass();
//            }
//        });
    }
    private void addItem() {

    }

    private void toPrepare(){

    }
     private void toTeacheing(){

     }
     private void toErrorNote(){

     }
     private void toPersonProfile(){

     }
     private void toAfterClass(){

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
