package assignment.home.com.ehailingapp.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.Collections;
import assignment.home.com.ehailingapp.R;
import assignment.home.com.ehailingapp.UtilityHelper;
import assignment.home.com.ehailingapp.adapter.TaxiListAdapter;
import assignment.home.com.ehailingapp.model.Taxi;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_START = 1; //The starting range number
    private static final int TIME_END = 180; //The end of range
    private static final int REFRESH_TIME = 5000; //1000 milliseconds = 1 sec

    private TaxiListAdapter mAdapter;
    private Handler myHandler;
    private Runnable myRunnable;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();

        myRunnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.updateList(getSortedTaxisList()); // Update list with random ETA's
                myHandler.postDelayed(this, REFRESH_TIME); //Starts a periodically timer
                runLayoutAnimation(mRecyclerView);

            }
        };
    }

    protected void onStart() {
        super.onStart();
        myHandler = new Handler();
        myHandler.post(myRunnable);

    }

    protected void onStop() {
        super.onStop();
        myHandler.removeCallbacks(myRunnable); //Dismiss handler
    }

    // Sets all properties need for displaying the taxis list
    private void setRecyclerView(){
        mRecyclerView = findViewById(R.id.taxi_recycler_view);

        // Using this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Using a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TaxiListAdapter(getSortedTaxisList());
        mRecyclerView.setAdapter(mAdapter);
    }

    //Creates ArrayList of taxis
    private ArrayList<Taxi> getSortedTaxisList(){
        final ArrayList<Taxi> taxis = new ArrayList<>();
        taxis.add(new Taxi(getString(R.string.castle), UtilityHelper.getRandomETA(TIME_START,TIME_END)));
        taxis.add(new Taxi(getString(R.string.shekem), UtilityHelper.getRandomETA(TIME_START,TIME_END)));
        taxis.add(new Taxi(getString(R.string.habima), UtilityHelper.getRandomETA(TIME_START,TIME_END)));
        taxis.add(new Taxi(getString(R.string.gordon), UtilityHelper.getRandomETA(TIME_START,TIME_END)));
        taxis.add(new Taxi(getString(R.string.azrieli), UtilityHelper.getRandomETA(TIME_START,TIME_END)));
        taxis.add(new Taxi(getString(R.string.hadera), UtilityHelper.getRandomETA(TIME_START,TIME_END)));

        //Sorting the list in ascending order
        Collections.sort(taxis, (t1, t2) -> t1.getArrivalTime()-t2.getArrivalTime());

        return taxis;
    }

    //Animation fall down to the list
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
