package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.sekretenko.testapp.R;

import java.util.List;

import api.models.TestItem;

/**
 * Created by vsekr_000 on 04.09.2016.
 * Адаптер
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<TestItem> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView name;
        public TextView version;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.info_id);
            name = (TextView) itemView.findViewById(R.id.info_name);
            version = (TextView) itemView.findViewById(R.id.info_version);
        }
    }

    public TestAdapter(List<TestItem> data) {
        this.data = data;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }





}
