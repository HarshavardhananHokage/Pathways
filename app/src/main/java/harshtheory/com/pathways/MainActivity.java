package harshtheory.com.pathways;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import harshtheory.com.pathways.adapters.PathsCardViewAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private TypedArray typArr_pathLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recView_pathwayPathCards = findViewById(R.id.am_recview_pathway_path_cards);

        String[] strArr_pathTitle = getResources().getStringArray(R.array.pathway_title_array);
        String[] strArr_pathDesc = getResources().getStringArray(R.array.pathway_path_desc_array);
        String[] strArr_pathLogoConDesc = getResources().getStringArray(R.array.pathway_icon_cont_desc_array);

        typArr_pathLogo = getResources().obtainTypedArray(R.array.pathways_path_drawables);

        PathsCardViewAdapter pathsCardViewAdapter = new PathsCardViewAdapter(strArr_pathTitle, strArr_pathDesc, strArr_pathLogoConDesc, typArr_pathLogo);
        recView_pathwayPathCards.setAdapter(pathsCardViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recView_pathwayPathCards.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.e(TAG, "Came Here");
        typArr_pathLogo.recycle();
    }
}
