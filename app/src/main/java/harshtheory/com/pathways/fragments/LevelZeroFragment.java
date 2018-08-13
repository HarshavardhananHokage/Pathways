package harshtheory.com.pathways.fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.interfaces.GetPathForLevel;
import harshtheory.com.pathways.models.Path;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelZeroFragment extends Fragment {

    public static final String TAG = "LevelZeroFragment";

    private Path selectedPath;

    private GetPathForLevel getPathForLevel;

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
        View lvlZeroView = inflater.inflate(R.layout.fragment_level_zero, container, false);
        selectedPath = getPathForLevel.getSelectedPath();
        populateView(lvlZeroView);
        return lvlZeroView;
    }

    public void populateView(View view)
    {
        TextView tv_pathName = view.findViewById(R.id.flzero_tv_path_name);
        TextView tv_pathDesc = view.findViewById(R.id.flzero_tv_path_description);
        TextView tv_pathQuote = view.findViewById(R.id.flzero_tv_path_quote);

        ImageView iv_pathLogo = view.findViewById(R.id.flzero_iv_path_logo);

        String packageName = getActivity().getPackageName();
        String pathTitle = getActivity().getResources().
                getString(getActivity().getResources().getIdentifier(selectedPath.getName(), "string", packageName));
        String pathDescription = getActivity().getResources().
                getString(getActivity().getResources().getIdentifier(selectedPath.getDescription(), "string", packageName));
        Drawable pathLogo = ContextCompat.getDrawable(getActivity(),
                getActivity().getResources().getIdentifier(selectedPath.getLogo(), "drawable", packageName));

        String[] quotes = getActivity().getResources().getStringArray(R.array.path_quotes);

        tv_pathName.setText(pathTitle);
        tv_pathDesc.setText(pathDescription);
        tv_pathQuote.setText(quotes[selectedPath.getId() - 1]);

        iv_pathLogo.setImageDrawable(pathLogo);
    }

}
