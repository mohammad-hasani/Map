package Menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.me.bandar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by me on 12/15/2016.
 */

public class RecyclerAdapterProductDetails extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Objects> data;
    private LayoutInflater inflater;

    public RecyclerAdapterProductDetails(Context context, List<Objects> data) {
//        inflater = LayoutInflater.from(context);
//        this.data = data;
//        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolderBooksList(inflater.inflate(R.layout.adapter_books_list, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

//        if (holder instanceof ViewHolderBooksList) {
//
//        } else if (holder instanceof LoadingViewHolder) {
//            LoadingViewHolder currentHolder = (LoadingViewHolder) holder;
//            currentHolder.progressBarLoading.setIndeterminate(true);
//        }
    }


    public void setData(ArrayList<Objects> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ViewHolderBooksList extends RecyclerView.ViewHolder {

        private TextView txtBookName, txtAuthor, txtPrice;
        private RatingBar ratingBar;
        private ImageView imgBook;
        private ProgressBar progressBar;

        ViewHolderBooksList(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBarLoading;

        LoadingViewHolder(View itemView) {
            super(itemView);
            progressBarLoading = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
