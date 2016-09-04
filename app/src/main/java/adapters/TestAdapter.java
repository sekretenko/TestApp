package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.sekretenko.testapp.R;
import java.util.ArrayList;
import java.util.List;

import api.models.TestItem;

/**
 * Created by vsekr_000 on 04.09.2016.
 * Адаптер
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<TestItem> data;
    private Context context;
    private int expandedPosition = -1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View expandableArea;
        public TextView id;
        public TextView name;
        public TextView version;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.info_id);
            name = (TextView) itemView.findViewById(R.id.info_name);
            version = (TextView) itemView.findViewById(R.id.info_version);
            image = (ImageView) itemView.findViewById(R.id.info_image);
            expandableArea = itemView.findViewById(R.id.info_expandable);
        }
    }

    public TestAdapter(List<TestItem> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TestItem currentData = data.get(position);
        holder.id.setText(String.valueOf(currentData.id));
        holder.name.setText(currentData.name);
        holder.version.setText(String.valueOf(currentData.version));

        if (position == expandedPosition) {
            holder.expandableArea.setVisibility(View.VISIBLE);
        } else {
            holder.expandableArea.setVisibility(View.GONE);
        }

        Picasso.with(context)
                .load(currentData.image)
                .resize(50, 50)
                .centerCrop()
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            int prev = expandedPosition;
            expandedPosition = holder.getLayoutPosition();

            if (expandedPosition >= 0 && prev != expandedPosition) {
                notifyItemChanged(prev);
            }

            notifyItemChanged(expandedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<TestItem> getData() {
        return data;
    }
}
