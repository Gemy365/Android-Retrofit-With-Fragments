package com.example.android.retrofitwithfragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Init views.
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by IDs.
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Send getSupportFragmentManager() as param to constructor of ViewPagerAdapter.
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Create addFragment method take (Constructor of fragments, Title).
        adapter.addFragment(new GetFragment(), "Get Response Server");
        adapter.addFragment(new PostFragment(), "Post Response Server");
        adapter.addFragment(new UpdateFragment(), "Update Response Server");
        adapter.addFragment(new DeleteFragment(), "Delete Response Server");

        // 0 means .. Open on first fragment.
        viewPager.setCurrentItem(0);

        // SetAdapter.
        viewPager.setAdapter(adapter);

        // Connect tabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);

        // Make Icon for every tabLayout.
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_launcher_background);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_launcher_background);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_launcher_background);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_launcher_background);

        // Remove Line of ActionBar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }
}
