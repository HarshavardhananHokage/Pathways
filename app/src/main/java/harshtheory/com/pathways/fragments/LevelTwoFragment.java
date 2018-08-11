package harshtheory.com.pathways.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.R;
import harshtheory.com.pathways.models.Path;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelTwoFragment extends Fragment {

    public static final String TAG = "LevelTwoFragment";

    private GetPathForLevel getPathForLevel;

    private Path selectedPath;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof GetPathForLevel)
        {
            getPathForLevel = (GetPathForLevel) context;
        }
    }


    public LevelTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selectedPath = getPathForLevel.getSelectedPath();
        return inflater.inflate(R.layout.fragment_level_two, container, false);
    }

}
