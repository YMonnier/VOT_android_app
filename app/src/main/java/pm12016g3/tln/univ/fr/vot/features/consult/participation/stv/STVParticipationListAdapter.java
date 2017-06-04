package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;


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

public class STVParticipationListAdapter
        extends DragItemAdapter<STVParticipationItem, STVParticipationListAdapter.ViewHolder> {

    private final static String TAG = STVParticipationListAdapter.class.getSimpleName();

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

    public STVParticipationListAdapter(List<STVParticipationItem> list, int layoutId,
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
        STVParticipationItem item = mItemList.get(position);
        holder.choice_id.setText(String.valueOf(position + 1));
        holder.choice_title.setText(item.getChoice_title());
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {

        TextView choice_id;
        TextView choice_title;


        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            choice_id = (TextView) itemView.findViewById(R.id.stv_participation_choice_id);
            choice_title = (TextView) itemView.findViewById(R.id.stv_participation_choice_title);
        }
    }

}
