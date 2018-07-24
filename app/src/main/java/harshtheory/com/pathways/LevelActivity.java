package harshtheory.com.pathways;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.util.PathwayAppConstants;

public class LevelActivity extends AppCompatActivity implements GetPathForLevel {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Toolbar toolbarMain = findViewById(R.id.al_toolbar);
        setSupportActionBar(toolbarMain);

        LevelFragmentPager levelFragmentPager = new LevelFragmentPager(getSupportFragmentManager());
        ViewPager viewPagerMain = findViewById(R.id.al_viewpager);
        viewPagerMain.setAdapter(levelFragmentPager);

        TabLayout tabLayoutMain = findViewById(R.id.al_tablayout);
        tabLayoutMain.setupWithViewPager(viewPagerMain);
    }

    @Override
    public int getPathID() {
        return 1;
    }

    public class LevelFragmentPager extends FragmentPagerAdapter
    {
        LevelFragmentPager(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new LevelOneFragment();
                case 1:
                    return new LevelTwoFragment();
                case 2:
                    return new LevelThreeFragment();
                case 3:
                    return new LevelFourFragment();
                case 4:
                    return new LevelFiveFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return PathwayAppConstants.PATHWAY_LEVEL_COUNT;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return getResources().getString(R.string.level_1);
                case 1:
                    return getResources().getString(R.string.level_2);
                case 2:
                    return getResources().getString(R.string.level_3);
                case 3:
                    return getResources().getString(R.string.level_4);
                case 4:
                    return getResources().getString(R.string.level_5);
            }
            return super.getPageTitle(position);
        }
    }
}