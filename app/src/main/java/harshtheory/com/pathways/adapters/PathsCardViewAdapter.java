package harshtheory.com.pathways.adapters;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import harshtheory.com.pathways.R;

public class PathsCardViewAdapter extends RecyclerView.Adapter<PathsCardViewAdapter.ViewHolder> {

    public static final String TAG = "PathsCardViewAdapter";

    private String[] strArr_pathTitles;
    private String[] strArr_pathDescs;
    private String[] strArr_pathLogoContDescs;
    private TypedArray typArr_pathLogos;

    public PathsCardViewAdapter(String[] pathTitles, String[] pathDescs, String[] pathLogoContDescs, TypedArray pathLogos)
    {
        this.strArr_pathTitles = pathTitles;
        this.strArr_pathDescs = pathDescs;
        this.strArr_pathLogoContDescs = pathLogoContDescs;
        this.typArr_pathLogos = pathLogos;
        //Log.e(TAG, "Came into Adapter");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        CardView cardView_pathCards = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.paths_main_cardview, viewGroup, false);
        //Log.e(TAG, "Came into Adapter onCreateViewHolder");
        return new ViewHolder(cardView_pathCards);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CardView cardView_pathwayPathCards = viewHolder.cardView;

        TextView tv_pathTitle = cardView_pathwayPathCards.findViewById(R.id.pmcv_tv_path_title);
        TextView tv_pathDesc = cardView_pathwayPathCards.findViewById(R.id.pmcv_tv_path_desc);

        ImageView iv_pathLogo = cardView_pathwayPathCards.findViewById(R.id.pmcv_iv_path_logo);

        tv_pathTitle.setText(strArr_pathTitles[i]);
        tv_pathDesc.setText(strArr_pathDescs[i]);
        iv_pathLogo.setImageResource(typArr_pathLogos.getResourceId(i, -1));
        iv_pathLogo.setContentDescription(strArr_pathLogoContDescs[i]);

        //Log.e(TAG, "Came into Adapter onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return strArr_pathTitles.length;
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
