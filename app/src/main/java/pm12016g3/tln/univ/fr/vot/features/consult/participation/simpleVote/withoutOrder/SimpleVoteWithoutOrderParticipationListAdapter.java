package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withoutOrder;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.woxthebox.draglistview.DragItemAdapter;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;

/**
 * Created by wenlixing on 22/05/2017.
 */

public class SimpleVoteWithoutOrderParticipationListAdapter
        extends DragItemAdapter<SimpleVoteWithoutOrderParticipationItem, SimpleVoteWithoutOrderParticipationListAdapter.ViewHolder> {

    private final static String TAG = SimpleVoteWithoutOrderParticipationListAdapter.class.getSimpleName();

    /**
     * Id of the item view to be inflated
     */
    private int mLayoutId;

    /**
     * Id of the view that should respond to a drag
     */
    private int mGrabHandleId;

    /**
     * The drag to happen on long press or directly when touching the item
     */
    private boolean mDragOnLongPress;

    public SimpleVoteWithoutOrderParticipationListAdapter(List<SimpleVoteWithoutOrderParticipationItem> list, int layoutId,
                                                          int grabHandleId, boolean dragOnLongPress) {
        this.mLayoutId = layoutId;
        this.mGrabHandleId = grabHandleId;
        this.mDragOnLongPress = dragOnLongPress;
        setHasStableIds(true);
        setItemList(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getChoice_title().hashCode();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SimpleVoteWithoutOrderParticipationItem item = mItemList.get(position);
        holder.choice_title.setText(item.getChoice_title());
        holder.check.setChecked(item.isChecked());
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {

        TextView choice_title;
        CheckedTextView check;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            choice_title = (TextView) itemView.findViewById(R.id.sv_participation_choice_title);
            check = (CheckedTextView) itemView.findViewById(R.id.sv_participation_choice_check_tv);
        }

        /**
         * Respond to clicks events on the item View root layout
         *
         * @param view
         */
        @Override
        public void onItemClicked(View view) {

            //Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
            check.setChecked(!check.isChecked());
            SimpleVoteWithoutOrderParticipationItem clickedItem = mItemList.get(getAdapterPosition());
            if (check.isChecked()) {
                clickedItem.setChecked(true);
            } else {
                clickedItem.setChecked(false);
                changeItemPosition(getAdapterPosition(), getItemCount() - 1);
                Log.d("TAG", mItemList.toString());
                notifyDataSetChanged();
            }
        }
    }

}
