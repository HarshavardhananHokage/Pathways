package harshtheory.com.pathways.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.interfaces.OnSelectDesiredPath;
import harshtheory.com.pathways.models.Path;

public class PathsCardViewAdapter extends RecyclerView.Adapter<PathsCardViewAdapter.ViewHolder> {

    public static final String TAG = "PathsCardViewAdapter";

    private Context context;
    private ArrayList<Path> allPaths;

    public PathsCardViewAdapter(Context context, ArrayList<Path> paths)
    {
        this.context = context;
        this.allPaths = paths;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        CardView cardView_pathCards = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_card_paths_main, viewGroup, false);
        //Log.e(TAG, "Came into Adapter onCreateViewHolder");
        return new ViewHolder(cardView_pathCards);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        CardView cardView_pathwayPathCards = viewHolder.cardView;

        TextView tv_pathTitle = cardView_pathwayPathCards.findViewById(R.id.pmcv_tv_path_title);
        TextView tv_pathDesc = cardView_pathwayPathCards.findViewById(R.id.pmcv_tv_path_desc);
        ImageView iv_pathLogo = cardView_pathwayPathCards.findViewById(R.id.pmcv_iv_path_logo);
        Button bt_explorePath = cardView_pathwayPathCards.findViewById(R.id.pmcv_bt_explore_path);

        Path path = allPaths.get(i);

        String packageName = context.getPackageName();
        String pathTitle = context.getResources().
                getString(context.getResources().getIdentifier(path.getName(), "string", packageName));
        String pathDescription = context.getResources().
                getString(context.getResources().getIdentifier(path.getDescription(), "string", packageName));
        Drawable pathLogo = ContextCompat.getDrawable(context,
                context.getResources().getIdentifier(path.getLogo(), "drawable", packageName));

        tv_pathTitle.setText(pathTitle);
        tv_pathDesc.setText(pathDescription);
        iv_pathLogo.setImageDrawable(pathLogo);
        iv_pathLogo.setContentDescription(path.getImageContDesc());

        bt_explorePath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof OnSelectDesiredPath)
                {
                    ((OnSelectDesiredPath) context).loadPathDetail(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allPaths.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;

        public ViewHolder(CardView cv)
        {
            super(cv);
            cardView = cv;
        }
    }
}
