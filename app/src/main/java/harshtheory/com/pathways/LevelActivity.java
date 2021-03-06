package harshtheory.com.pathways;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import harshtheory.com.pathways.fragments.LevelFiveFragment;
import harshtheory.com.pathways.fragments.LevelFourFragment;
import harshtheory.com.pathways.fragments.LevelOneFragment;
import harshtheory.com.pathways.fragments.LevelThreeFragment;
import harshtheory.com.pathways.fragments.LevelTwoFragment;
import harshtheory.com.pathways.fragments.LevelZeroFragment;
import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.interfaces.OnSelectProject;
import harshtheory.com.pathways.models.Path;
import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.util.PathwayAppConstants;

public class LevelActivity extends AppCompatActivity implements GetPathForLevel, OnSelectProject {

    public static final String TAG = "LevelActivity";

    private Path selectedPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        selectedPath = (Path) getIntent().getSerializableExtra(PathwayAppConstants.PATH_OBJ_EXTRA);
        int pathTitleID = getResources().getIdentifier(selectedPath.getName(), "string", getPackageName());

        Toolbar toolbarMain = findViewById(R.id.al_toolbar);
        toolbarMain.setTitle(pathTitleID);
        setSupportActionBar(toolbarMain);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LevelActivity.super.onBackPressed();
            }
        });

        LevelFragmentPager levelFragmentPager = new LevelFragmentPager(getSupportFragmentManager());
        ViewPager viewPagerMain = findViewById(R.id.al_viewpager);
        viewPagerMain.setAdapter(levelFragmentPager);

        TabLayout tabLayoutMain = findViewById(R.id.al_tablayout);
        tabLayoutMain.setupWithViewPager(viewPagerMain);
    }

    @Override
    public Path getSelectedPath() {
        return selectedPath;
    }

    @Override
    public void loadProjectDetails(Project project) {

        Intent loadProjectDetail = new Intent(this, ProjectDetailActivity.class);
        loadProjectDetail.putExtra(PathwayAppConstants.PROJECT_OBJ_EXTRA, project);
        startActivity(loadProjectDetail);
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
                    return new LevelZeroFragment();
                case 1:
                    return new LevelOneFragment();
                case 2:
                    return new LevelTwoFragment();
                case 3:
                    return new LevelThreeFragment();
                case 4:
                    return new LevelFourFragment();
                case 5:
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
                    return getResources().getString(R.string.level_0);
                case 1:
                    return getResources().getString(R.string.level_1);
                case 2:
                    return getResources().getString(R.string.level_2);
                case 3:
                    return getResources().getString(R.string.level_3);
                case 4:
                    return getResources().getString(R.string.level_4);
                case 5:
                    return getResources().getString(R.string.level_5);
            }
            return super.getPageTitle(position);
        }
    }
}
