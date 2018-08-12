package harshtheory.com.pathways;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import harshtheory.com.pathways.models.Project;
import harshtheory.com.pathways.util.PathwayAppConstants;

public class ProjectDetailActivity extends AppCompatActivity {

    public static final String TAG = "ProjectDetailActivity";

    private Project selectedProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Toolbar toolbar = findViewById(R.id.apd_toolbar_main);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectDetailActivity.super.onBackPressed();
            }
        });

        selectedProject = (Project) getIntent().getSerializableExtra(PathwayAppConstants.PROJECT_OBJ_EXTRA);

        populateProjectDetail();
    }

    public void populateProjectDetail()
    {
        TextView tv_projectTitle = findViewById(R.id.apd_tv_project_title);
        TextView tv_projectDescription = findViewById(R.id.apd_tv_project_desc);
        TextView tv_projectPurpose = findViewById(R.id.apd_tv_project_purpose);
        TextView tv_projectOverview = findViewById(R.id.apd_tv_project_overview);
        TextView tv_projectIncludes = findViewById(R.id.apd_tv_project_includes);

        String projectTitle = getResources().getString(
                getResources().getIdentifier(selectedProject.getTitle(), "string", getPackageName()));
        String projectDescription = getResources().getString(
                getResources().getIdentifier(selectedProject.getDescription(), "string", getPackageName()));
        String projectPurpose = getResources().getString(
                getResources().getIdentifier(selectedProject.getPurpose(), "string", getPackageName()));
        String projectOverview = getResources().getString(
                getResources().getIdentifier(selectedProject.getOverview(), "string", getPackageName()));

        String[] projectIncludes = getResources().getStringArray(
                getResources().getIdentifier(selectedProject.getIncludes(), "array", getPackageName()));

        tv_projectTitle.setText(projectTitle);
        tv_projectDescription.setText(projectDescription);
        tv_projectPurpose.setText(projectPurpose);
        tv_projectOverview.setText(projectOverview);

        String includeItem = "";
        for(String string: projectIncludes)
        {
            includeItem = "• " + string + "\n";
            tv_projectIncludes.append(includeItem);
        }
    }
}
