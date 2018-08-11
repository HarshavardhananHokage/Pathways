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

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.adapters.LevelListViewAdapter;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.models.Path;
import harshtheory.com.pathways.models.Project;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelThreeFragment extends Fragment {

    public static final String TAG = "LevelThreeFragment";

    private GetPathForLevel getPathForLevel;

    private Path selectedPath;

    private PathwaysDBManager pathwaysDBManager;
    private RecyclerView rv_selectedProjects;
    private RecyclerView rv_electiveSelectedProjects;

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

        View lvlThreeView = inflater.inflate(R.layout.fragment_level_three, container, false);
        selectedPath = getPathForLevel.getSelectedPath();

        rv_selectedProjects = lvlThreeView.findViewById(R.id.flthree_rv_project_list);
        rv_electiveSelectedProjects = lvlThreeView.findViewById(R.id.flthree_rv_elec_project_list);

        populateList();
        return lvlThreeView;
    }

    public void populateList()
    {
        pathwaysDBManager = new PathwaysDBManager(getContext());

        int lvlThreeProject = selectedPath.getLevelThree();
        ArrayList<Project> projectsLevelThree = pathwaysDBManager.getSelectedProjects(new int[]{lvlThreeProject});

        int[] lvlThreeElecProjects = selectedPath.getLevelThreeElect();
        ArrayList<Project> elecProjectsLevelThree = pathwaysDBManager.getSelectedProjects(lvlThreeElecProjects);

        LevelListViewAdapter projectAdapter = new LevelListViewAdapter(getContext(), projectsLevelThree, false);
        LevelListViewAdapter elecProjectAdapter = new LevelListViewAdapter(getContext(), elecProjectsLevelThree, true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager elecLinearLayoutManager = new LinearLayoutManager(getContext());
        elecLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_selectedProjects.setAdapter(projectAdapter);
        rv_selectedProjects.setLayoutManager(linearLayoutManager);

        rv_electiveSelectedProjects.setAdapter(elecProjectAdapter);
        rv_electiveSelectedProjects.setLayoutManager(elecLinearLayoutManager);
    }

}
