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
        fragment_selector frag2 = (fragment_selector) fragmentManager.findFragmentById(R.id.selectorLayout);
        if(frag == null)
        {
            frag = new fragment_map();
            fragmentManager.beginTransaction().add(R.id.mapLayout, frag).commit();
        }
        if(frag2 == null)
        {
            frag2 = new fragment_selector();
            fragmentManager.beginTransaction().add(R.id.selectorLayout, frag2).commit();
        }
        frag.setSelector(frag2);
    }
}