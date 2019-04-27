 package com.example.bear.programmerdaily.activity;

import android.app.FragmentTransaction;
import android.app.Service;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bear.programmerdaily.R;
import com.example.bear.programmerdaily.adapter.AppInfoAdapter;
import com.example.bear.programmerdaily.data.db.AppInfoEntity;
import com.example.bear.programmerdaily.data.viewmodel.AppInfoViewModel;
import com.example.bear.programmerdaily.fragment.AppInfoFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Executors;

 public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private AppInfoViewModel appInfoViewModel;
     private AppInfoAdapter appInfoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        appInfoViewModel = ViewModelProviders.of(this).get(AppInfoViewModel.class);
        appInfoViewModel.getAllAppInfos().observe(this, new Observer<List<AppInfoEntity>>() {
            @Override
            public void onChanged( List<AppInfoEntity> appInfoEntities) {
                appInfoAdapter.setAppInfoEntitys(appInfoEntities);
            }
        });

//        if (savedInstanceState == null) {
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            AppInfoFragment fragment = new AppInfoFragment();
//            transaction.replace(R.id.main, fragment);
//            transaction.commit();
//        }
        initView();

        // Example of a call to a native method

    }
     private void initRecyclerView() {
         RecyclerView recyclerView = findViewById(R.id.recycler_view);
         appInfoAdapter = new AppInfoAdapter(this);
         final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
         linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         recyclerView.setLayoutManager(linearLayoutManager);
         recyclerView.setAdapter(appInfoAdapter);
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
                Intent intent = new Intent(MainActivity.this,AddAppItemActivity.class);
                startActivityForResult(intent,100);
            }
        });
        initRecyclerView();

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

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == 100 && resultCode == RESULT_OK) {
             if (data != null) {
                 String appName = data.getStringExtra("appName");
                 String appKey = data.getStringExtra("appKey");
                 String appDeveloper = data.getStringExtra("appDeveloper");
                 AppInfoEntity appInfoEntity = new AppInfoEntity();
                 appInfoEntity.setAppName(appName);
                 appInfoEntity.setKeyWord(appKey);
                 appInfoEntity.setAppDeveloper(appDeveloper);
                 appInfoViewModel.insert(appInfoEntity);
             }
         }
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
