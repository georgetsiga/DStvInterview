package com.sample.dstvinterview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.sample.dstvinterview.ui.login.LoginActivity;
import com.sample.dstvinterview.ui.login.LoginViewModel;
import com.sample.dstvinterview.ui.login.LoginViewModelFactory;
import com.sample.dstvinterview.util.Constants;
import com.sample.dstvinterview.util.SharedPreferenceManagers;

public class HomeActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setDisplayName();
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);
    }

    private void setDisplayName() {
        if (getSupportActionBar() != null) {
            SharedPreferenceManagers sharedPreferenceManagers = new SharedPreferenceManagers(getApplicationContext());
            getSupportActionBar().setTitle(sharedPreferenceManagers.getString(Constants.DISPLAY_NAME_KEY));
        }
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.dstvmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            loginViewModel.logout(getApplicationContext());
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}