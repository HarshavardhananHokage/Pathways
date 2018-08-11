package harshtheory.com.pathways.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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

    private OnSelectProject onSelectProject;

    public LevelListViewAdapter(Context context, ArrayList<Project> projects)
    {
        this.context = context;
        this.availableProjects = projects;
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

        TextView tv_projectTitle = cv_projectsCards.findViewById(R.id.vlpli_tv_project_title);
        TextView tv_projectDesc = cv_projectsCards.findViewById(R.id.vlpli_tv_project_desc);

        Project project = availableProjects.get(i);

        String packageName = context.getPackageName();

        String projectTitle =
                context.getString(context.getResources().getIdentifier(project.getTitle(), "string", packageName));
        String projectDescription =
                context.getString(context.getResources().getIdentifier(project.getDescription(), "string", packageName));

        Log.e(TAG, "Project Title: " +projectTitle);
        Log.e(TAG, "Project Description: " +projectDescription);
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
