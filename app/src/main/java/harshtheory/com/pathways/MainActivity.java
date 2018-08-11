package harshtheory.com.pathways;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import harshtheory.com.pathways.adapters.PathsCardViewAdapter;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.OnSelectDesiredPath;
import harshtheory.com.pathways.models.Path;
import harshtheory.com.pathways.util.PathwayAppConstants;

public class MainActivity extends AppCompatActivity implements OnSelectDesiredPath {

    public static final String TAG = "MainActivity";



    private ArrayList<Path> allPathsList = new ArrayList<>();
    private PathwaysDBManager pathwaysDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
