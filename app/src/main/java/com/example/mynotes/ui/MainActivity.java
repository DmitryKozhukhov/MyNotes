package com.example.mynotes.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentResultListener;

import com.example.mynotes.R;
import com.example.mynotes.domain.Note;
import com.example.mynotes.ui.details.NoteDetailsFragment;
import com.example.mynotes.ui.info.NoteInfoFragment;
import com.example.mynotes.ui.list.NotesListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavDrawable {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new NotesListFragment())
                    .commit();

        }

        drawerLayout = findViewById(R.id.drawer);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_list:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new NotesListFragment())
                                .commit();

                        return true;

                    case R.id.action_info:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, new NoteInfoFragment())
                                .commit();

                        return true;
                }
                return false;
            }
        });

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_notes) {

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new NotesListFragment())
                            .commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }

                if (item.getItemId() == R.id.action_info) {

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new NoteInfoFragment())
                            .commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

        getSupportFragmentManager().setFragmentResultListener(NotesListFragment.NOTE_SELECTED, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Note note = result.getParcelable(NotesListFragment.SELECTED_NOTE_BUNDLE);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, NoteDetailsFragment.newInstance(note))
                        .addToBackStack("")
                        .commit();

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
            new AlertDialog.Builder(MainActivity.this)
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setTitle(R.string.exit_confirmation)
                    .setMessage(R.string.exit_question)
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(MainActivity.this, "Отмена", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Toast.makeText(MainActivity.this, R.string.exit, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setAppBar(Toolbar toolbar) {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

    }
}