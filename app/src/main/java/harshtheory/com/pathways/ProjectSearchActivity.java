package harshtheory.com.pathways;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

import harshtheory.com.pathways.adapters.LevelListViewAdapter;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.OnSelectProject;
import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.util.PathwayAppConstants;

public class ProjectSearchActivity extends AppCompatActivity implements OnSelectProject {

    public static final String TAG = "ProjectSearchActivity";

    private PathwaysDBManager pathwaysDBManager;
    private LevelListViewAdapter levelListViewAdapter;
    private ArrayList<Project> allProjects;
    private RecyclerView rv_projectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_search);

        rv_projectsList = findViewById(R.id.aps_rv_project_list);

        Toolbar toolbar = findViewById(R.id.aps_toolbar_main);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        pathwaysDBManager = new PathwaysDBManager(this);
        //int[] projectList = new int[]{17,14,45,52,51,20,35,9,54,27,6,2,21,44,25};
        allProjects = pathwaysDBManager.getSelectedProjects(null);

        Log.e(TAG, "Size of projects:"+allProjects.size());

        levelListViewAdapter = new LevelListViewAdapter(this, allProjects, false);
        rv_projectsList.setAdapter(levelListViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_projectsList.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_projects, menu);

        MenuItem search = menu.findItem(R.id.msp_search_projects);
        DrawableCompat.setTint(search.getIcon(), ContextCompat.getColor(this, R.color.white));

        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_view_hint));
        /*EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                levelListViewAdapter.getFilter().filter(s);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void loadProjectDetails(Project project) {
        Intent loadProjectDetail = new Intent(this, ProjectDetailActivity.class);
        loadProjectDetail.putExtra(PathwayAppConstants.PROJECT_OBJ_EXTRA, project);
        startActivity(loadProjectDetail);
    }
}
