package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withOrder;


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

public class SimpleVoteWithOrderParticipationListAdapter
        extends DragItemAdapter<SimpleVoteWithOrderParticipationItem, SimpleVoteWithOrderParticipationListAdapter.ViewHolder> {

    private final static String TAG = SimpleVoteWithOrderParticipationListAdapter.class.getSimpleName();

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

    public SimpleVoteWithOrderParticipationListAdapter(List<SimpleVoteWithOrderParticipationItem> list, int layoutId,
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
        SimpleVoteWithOrderParticipationItem item = mItemList.get(position);
        if (item.isChecked())
            holder.choice_id.setText(String.valueOf(position + 1));
        else
            holder.choice_id.setText(" ");
        holder.choice_title.setText(item.getChoice_title());
        holder.check.setChecked(item.isChecked());
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {

        TextView choice_id;
        TextView choice_title;
        CheckedTextView check;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            choice_id = (TextView) itemView.findViewById(R.id.sv_participation_choice_id);
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
            SimpleVoteWithOrderParticipationItem clickedItem = mItemList.get(getAdapterPosition());
            if (check.isChecked()) {
                clickedItem.setChecked(true);
                choice_id.setText(String.valueOf(getAdapterPosition() + 1));
            } else {
                clickedItem.setChecked(false);
                choice_id.setText(" ");
                changeItemPosition(getAdapterPosition(), getItemCount() - 1);
                Log.d("TAG", mItemList.toString());
                notifyDataSetChanged();
            }
        }
    }

}
