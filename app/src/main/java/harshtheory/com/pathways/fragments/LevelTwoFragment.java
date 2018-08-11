package harshtheory.com.pathways.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import harshtheory.com.pathways.adapters.LevelListViewAdapter;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.R;
import harshtheory.com.pathways.models.Path;
import harshtheory.com.pathways.models.Project;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelTwoFragment extends Fragment {

    public static final String TAG = "LevelTwoFragment";

    private GetPathForLevel getPathForLevel;

    private Path selectedPath;
    private RecyclerView rv_projectsList;
    private PathwaysDBManager pathwaysDBManager;

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
        View lvlTwoView = inflater.inflate(R.layout.fragment_level_two, container, false);
        selectedPath = getPathForLevel.getSelectedPath();

        rv_projectsList = lvlTwoView.findViewById(R.id.fltwo_rv_project_list);

        populateList();
        return lvlTwoView;
    }

    public void populateList()
    {
        pathwaysDBManager = new PathwaysDBManager(getContext());
        int[] lvlTwoProjectIds = selectedPath.getLevelTwo();
        ArrayList<Project> projectsLevelTwo = pathwaysDBManager.getSelectedProjects(lvlTwoProjectIds);

        LevelListViewAdapter levelListViewAdapter = new LevelListViewAdapter(getContext(), projectsLevelTwo, false);
        rv_projectsList.setAdapter(levelListViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_projectsList.setLayoutManager(linearLayoutManager);
    }

}
