package cc.lkme.databindingsample;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import cc.lkme.databindingsample.databinding.ActivityCrecyclerViewBinding;


public class CRecyclerView extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crecycler_view);
        ActivityCrecyclerViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_crecycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.myRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        binding.myRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        myDataset.add("aa");
        myDataset.add("bb");
        myDataset.add("cc");
        myDataset.add("dd");
        mAdapter = new MyAdapter(myDataset);
        binding.myRecyclerView.setAdapter(mAdapter);
    }


    static class MyAdapter<E> extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<E> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private ViewDataBinding binding;

            public ViewHolder(ViewDataBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public ViewDataBinding getBinding() {
                return binding;
            }


        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<E> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
//            View v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_recycler_view, parent, false);

            //data binding
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recycler_view, parent, false);

            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(binding);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            final E item = mDataset.get(position);
            holder.getBinding().setVariable(cc.lkme.databindingsample.BR.item, item);
            holder.getBinding().executePendingBindings();
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}
