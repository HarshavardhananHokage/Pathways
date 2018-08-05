package harshtheory.com.pathways.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.database.PathwaysDBManager;
import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.models.Path;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelOneFragment extends Fragment {

    public static final String TAG = "LevelOneFragment";

    private int pathID = -1;

    private GetPathForLevel getPathForLevel;

    private PathwaysDBManager pathwaysDBManager;
    private Path selectedPath;

    LinearLayout lvlOneLinearLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPathForLevel = (GetPathForLevel) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lvlOneView = inflater.inflate(R.layout.fragment_level_one, container, false);
        pathID = getPathForLevel.getPathID();
        lvlOneLinearLayout = lvlOneView.findViewById(R.id.flone_ll_project_list);

        populateList();
        return lvlOneView;
    }

    private void populateList()
    {
        pathwaysDBManager = new PathwaysDBManager(getContext());
        selectedPath = pathwaysDBManager.getIndividualPath(pathID);
        int[] lvlOneProjectIds = selectedPath.getLevelOne();

        List<Project> projectsLevelOne = pathwaysDBManager.getSelectedProjects(lvlOneProjectIds);
        int i = 1;
        for(Project project : projectsLevelOne)
        {
            String packageName = getActivity() == null ? null : getActivity().getPackageName();
            int resID = getResources().getIdentifier(project.getTitle(), "string", packageName);

            TextView textView = new TextView(getActivity());
            textView.setText(getResources().getString(resID));
            textView.setId(i);
            i++;
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            lvlOneLinearLayout.addView(textView);

        }
    }

}
