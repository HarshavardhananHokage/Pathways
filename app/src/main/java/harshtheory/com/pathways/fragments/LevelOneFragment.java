package harshtheory.com.pathways.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.adapters.LevelListViewAdapter;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.interfaces.OnSelectDesiredPath;
import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.models.Path;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelOneFragment extends Fragment {

    public static final String TAG = "LevelOneFragment";

    private GetPathForLevel getPathForLevel;

    private PathwaysDBManager pathwaysDBManager;
    private Path selectedPath;

    private RecyclerView rv_projectsList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof GetPathForLevel)
        {
            getPathForLevel = (GetPathForLevel) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lvlOneView = inflater.inflate(R.layout.fragment_level_one, container, false);
        selectedPath = getPathForLevel.getSelectedPath();

        rv_projectsList = lvlOneView.findViewById(R.id.flone_rv_project_list);

        populateList();
        return lvlOneView;
    }

    private void populateList()
    {
        pathwaysDBManager = new PathwaysDBManager(getContext());
        int[] lvlOneProjectIds = selectedPath.getLevelOne();
        ArrayList<Project> projectsLevelOne = pathwaysDBManager.getSelectedProjects(lvlOneProjectIds);

        LevelListViewAdapter levelListViewAdapter = new LevelListViewAdapter(getContext(), projectsLevelOne, false);
        rv_projectsList.setAdapter(levelListViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_projectsList.setLayoutManager(linearLayoutManager);


    }

}
