package Menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.me.bandar.R;

import java.util.HashMap;
import java.util.List;

import MyGoogleMap.Markers;

/**
 * Created by me on 12/15/2016.
 */

public class RecyclerAdapterRightMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HashMap<String, String>> data;
    private LayoutInflater inflater;

    public RecyclerAdapterRightMenu(Context context, List<HashMap<String, String>> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderCategory(inflater.inflate(R.layout.menu_right, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolderCategory view = (ViewHolderCategory)holder;
        HashMap tmphash = this.data.get(position);
        final String id = String.valueOf(tmphash.get("category_id"));
        String name = String.valueOf(tmphash.get("category_name"));
        view.txtName.setText(name);
        view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Markers().change_category_marker(context, id);
            }
        });

    }

    public void setData(List<HashMap<String, String>> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ViewHolderCategory extends RecyclerView.ViewHolder {

        private TextView txtName;

        ViewHolderCategory(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
