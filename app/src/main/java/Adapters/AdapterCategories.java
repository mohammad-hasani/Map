package Adapters;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.viewHolderCategories> {
//
//    private LayoutInflater inflater;
//    private List<StructCategory> data = Collections.emptyList();
//    private Context context;
//    private View.OnClickListener listener;
//
//    public AdapterCategories(Context context, List<StructCategory> data, View.OnClickListener listener) {
//        inflater = LayoutInflater.from(context);
//        this.data = data;
//        this.context = context;
//        this.listener = listener;
//    }
//
//    public AdapterCategories(Context context, View.OnClickListener listener) {
//        inflater = LayoutInflater.from(context);
//        this.context = context;
//        this.listener = listener;
//        data = new ArrayList<>();
//    }
//
//    @Override
//    public viewHolderCategories onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.adapter_categories, parent, false);
//        return new viewHolderCategories(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final viewHolderCategories holder,final int position) {
//        final StructCategory current = data.get(position);
//
//        holder.itemView.setTag(BooksListFragment.getInstance(current.getId(), AudioBookTypes.CATEGORY_TYPE,listener));
//        holder.itemView.setOnClickListener(listener);
//
//        holder.txtCategoryName.setText(current.getName());
//
//        Glide.with(G.context)
//          .load(G.URL_IMAGES + current.getImageUrl())
//          .centerCrop()
//          .diskCacheStrategy(DiskCacheStrategy.ALL)
//          .placeholder(R.mipmap.ic_launcher)
//          .into(holder.imgCategory);
//
//    }
//
//    public void setData(ArrayList<StructCategory> data) {
//        this.data = data;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return data != null ? data.size() : 0;
//    }
//
//    class viewHolderCategories extends RecyclerView.ViewHolder {
//        private ImageView imgCategory;
//        private TextView txtCategoryName;
//        private LinearLayout container;
//
//        viewHolderCategories(View itemView) {
//            super(itemView);
//            imgCategory = (ImageView) itemView.findViewById(R.id.imgCategory);
//            txtCategoryName = (TextView) itemView.findViewById(R.id.txtCategoryName);
//            container = (LinearLayout) itemView.findViewById(R.id.container);
//        }
//    }
//}
