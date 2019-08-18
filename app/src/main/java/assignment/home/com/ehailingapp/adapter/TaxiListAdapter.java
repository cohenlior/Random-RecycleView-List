package assignment.home.com.ehailingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import assignment.home.com.ehailingapp.R;
import assignment.home.com.ehailingapp.UtilityHelper;
import assignment.home.com.ehailingapp.model.Taxi;

public class TaxiListAdapter extends RecyclerView.Adapter<TaxiListAdapter.MyViewHolder> {
    private ArrayList<Taxi> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTaxiName, mArrivalTime;

        MyViewHolder(View v) {
            super(v);
            mTaxiName = v.findViewById(R.id.taxi_name);
            mArrivalTime = v.findViewById(R.id.arrival_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaxiListAdapter(ArrayList<Taxi> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TaxiListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemi_taxi_list, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Taxi item = mDataset.get(position);
        holder.mTaxiName.setText(item.getStationName());
        holder.mArrivalTime.setText(UtilityHelper.timeFormatter(item.getArrivalTime()));

    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateList(ArrayList<Taxi> list){
        this.mDataset = list;
    }
}
