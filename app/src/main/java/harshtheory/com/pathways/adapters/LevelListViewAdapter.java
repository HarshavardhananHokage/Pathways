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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import harshtheory.com.pathways.R;
import harshtheory.com.pathways.interfaces.OnSelectProject;
import harshtheory.com.pathways.models.Project;

public class LevelListViewAdapter extends RecyclerView.Adapter<LevelListViewAdapter.ViewHolder> implements Filterable
{
    public static final String TAG = "LevelListViewAdapter";

    private Context context;
    private ArrayList<Project> availableProjects;
    private ArrayList<Project> originalDataList;
    private boolean isElective;

    private OnSelectProject onSelectProject;
    private ProjectFilter projectFilter;

    public LevelListViewAdapter(Context context, ArrayList<Project> projects, boolean isElective)
    {
        this.context = context;
        this.availableProjects = projects;
        this.originalDataList = projects;
        this.isElective = isElective;
        projectFilter = new ProjectFilter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        CardView cv_projectCards = (CardView) LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.view_list_paths_level_items, viewGroup, false);
        return new ViewHolder(cv_projectCards);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

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

        final Project project = availableProjects.get(i);

        String packageName = context.getPackageName();

        String projectTitle =
                context.getString(context.getResources().getIdentifier(project.getTitle(), "string", packageName));
        String projectDescription =
                context.getString(context.getResources().getIdentifier(project.getDescription(), "string", packageName));

        tv_projectTitle.setText(projectTitle);
        tv_projectDesc.setText(projectDescription);

        cv_projectsCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof OnSelectProject)
                {
                    ((OnSelectProject) context).loadProjectDetails(project);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return availableProjects.size();
    }

    @Override
    public Filter getFilter() {
        return projectFilter;
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

    private class ProjectFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            String filterString = constraint.toString().toLowerCase();
            ArrayList<Project> searchList = new ArrayList<>();
            ArrayList<Project> orginalList = originalDataList;

            int count = orginalList.size();

            if(constraint != null && constraint.length() > 0)
            {
                for(int i = 0; i < count; i++)
                {
                    String name = orginalList.get(i).getName();
                    if(name.toLowerCase().contains(filterString))
                    {
                        searchList.add(orginalList.get(i));
                    }
                }
                results.values = searchList;
                results.count = searchList.size();
            }
            else
            {
                results.values = orginalList;
                results.count = orginalList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            availableProjects = (ArrayList<Project>) filterResults.values;
            notifyDataSetChanged();

        }
    }
}
