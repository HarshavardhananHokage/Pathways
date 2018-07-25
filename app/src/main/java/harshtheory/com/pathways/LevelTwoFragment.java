package harshtheory.com.pathways;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import harshtheory.com.pathways.interfaces.GetPathForLevel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelTwoFragment extends Fragment {

    public static final String TAG = "LevelTwoFragment";

    private int pathID = -1;

    private GetPathForLevel getPathForLevel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPathForLevel = (GetPathForLevel) context;
    }


    public LevelTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pathID = getPathForLevel.getPathID();
        return inflater.inflate(R.layout.fragment_level_two, container, false);
    }

}
