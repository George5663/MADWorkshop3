package com.example.madworkshop3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment_map frag = (fragment_map) fragmentManager.findFragmentById(R.id.mapLayout);
        if(frag == null)
        {
            frag = new fragment_map();
            fragmentManager.beginTransaction().add(R.id.mapLayout, frag).commit();
        }
    }
}