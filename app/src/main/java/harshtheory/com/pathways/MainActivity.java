package harshtheory.com.pathways;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

import harshtheory.com.pathways.adapters.PathsCardViewAdapter;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.OnSelectDesiredPath;
import harshtheory.com.pathways.models.Path;
import harshtheory.com.pathways.util.PathwayAppConstants;

public class MainActivity extends AppCompatActivity implements OnSelectDesiredPath, NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";

    private ArrayList<Path> allPathsList = new ArrayList<>();
    private PathwaysDBManager pathwaysDBManager;
    private DrawerLayout mainDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDrawerLayout = findViewById(R.id.am_drawer_layout);

        Toolbar toolbar = findViewById(R.id.am_toolbar_main);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mainDrawerLayout, toolbar, R.string.nav_draw_open, R.string.nav_draw_close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        mainDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView mainNavView = findViewById(R.id.am_nav_view);
        mainNavView.setNavigationItemSelectedListener(this);

        RecyclerView recView_pathwayPathCards = findViewById(R.id.am_recview_pathway_path_cards);

        pathwaysDBManager = new PathwaysDBManager(this);
        allPathsList = pathwaysDBManager.getAllPaths();

        PathsCardViewAdapter pathsCardViewAdapter = new PathsCardViewAdapter(this, allPathsList);
        recView_pathwayPathCards.setAdapter(pathsCardViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recView_pathwayPathCards.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void loadPathDetail(int id) {
        Intent startLevelActivityIntent = new Intent(this, LevelActivity.class);
        Path path = allPathsList.get(id);
        startLevelActivityIntent.putExtra(PathwayAppConstants.PATH_OBJ_EXTRA, path);
        startActivity(startLevelActivityIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onBackPressed() {

        if(mainDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mainDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }
}
