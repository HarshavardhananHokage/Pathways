package harshtheory.com.pathways.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.interfaces.OnSelectProject;
import harshtheory.com.pathways.models.Project;

public class LevelListViewAdapter extends RecyclerView.Adapter<LevelListViewAdapter.ViewHolder>
{
    public static final String TAG = "LevelListViewAdapter";

    private Context context;
    private ArrayList<Project> availableProjects;
    private boolean isElective;

    private OnSelectProject onSelectProject;

    public LevelListViewAdapter(Context context, ArrayList<Project> projects, boolean isElective)
    {
        this.context = context;
        this.availableProjects = projects;
        this.isElective = isElective;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        CardView cv_projectCards = (CardView) LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.view_list_paths_level_items, viewGroup, false);
        return new ViewHolder(cv_projectCards);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CardView cv_projectsCards = viewHolder.cardView;

        if(isElective)
        {
            Log.e(TAG, "Hello");
            cv_projectsCards.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blueFifty));
        }

        TextView tv_projectTitle = cv_projectsCards.findViewById(R.id.vlpli_tv_project_title);
        TextView tv_projectDesc = cv_projectsCards.findViewById(R.id.vlpli_tv_project_desc);

        ConstraintLayout constraintLayout = cv_projectsCards.findViewById(R.id.vlpli_constraint_layout);

        //constraintLayout.setBackgroundColor(Color.RED);

        Project project = availableProjects.get(i);

        String packageName = context.getPackageName();

        String projectTitle =
                context.getString(context.getResources().getIdentifier(project.getTitle(), "string", packageName));
        String projectDescription =
                context.getString(context.getResources().getIdentifier(project.getDescription(), "string", packageName));

        tv_projectTitle.setText(projectTitle);
        tv_projectDesc.setText(projectDescription);
    }

    @Override
    public int getItemCount() {
        return availableProjects.size();
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
