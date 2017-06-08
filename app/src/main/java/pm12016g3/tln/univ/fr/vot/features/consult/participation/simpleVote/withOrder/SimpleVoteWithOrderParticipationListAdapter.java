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
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;

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

    private int countTrue;

    private SocialChoice<SCSMajorityBallot> socialChoice;

    public SimpleVoteWithOrderParticipationListAdapter(List<SimpleVoteWithOrderParticipationItem> list, int layoutId,
                                                       int grabHandleId, boolean dragOnLongPress, SocialChoice<SCSMajorityBallot> socialChoice) {
        this.mLayoutId = layoutId;
        this.mGrabHandleId = grabHandleId;
        this.mDragOnLongPress = dragOnLongPress;
        this.socialChoice = socialChoice;
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
        if (item.isChecked()){
            holder.choice_id.setText(String.valueOf(position + 1));
            item.setOrder(position + 1);
        }
        else {
            holder.choice_id.setText(" ");
            item.setOrder(0);
        }
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

            int currentPosition = getAdapterPosition();
            int lastPosition = getItemCount()-1;


            System.out.println("je suis là");

            SimpleVoteWithOrderParticipationItem clickedItem = mItemList.get(getAdapterPosition());
            if (check.isChecked()) {
                countTrue--;
                clickedItem.setChecked(false);
                choice_id.setText(" ");
                //clickedItem.setOrder(0);
                if(currentPosition != lastPosition){
                    changeItemPosition(currentPosition, lastPosition);
                    notifyDataSetChanged();
                }
            } else {
                System.out.println(" et là aussi ");
                if (countTrue >= socialChoice.getData().getNbChoice())
                    return;
                countTrue++;
                clickedItem.setChecked(true);
                choice_id.setText(String.valueOf(getAdapterPosition() + 1));
                //clickedItem.setOrder(getAdapterPosition() + 1);
                if(currentPosition != 0){
                    changeItemPosition(currentPosition, 0);
                    notifyDataSetChanged();
                }
            }


            /*int currentPosition = getAdapterPosition();
            int lastPosition = getItemCount()-1;

            System.out.println("je suis là");

            JMLabelItemView clickedItem = mItemList.get(getAdapterPosition());

            if (check.isChecked()) {
                countTrue--;
                System.out.println(" ^^ coutn = "+countTrue);
                check.setChecked(!check.isChecked());
                clickedItem.setChecked(false);
                if(currentPosition != lastPosition){
                    changeItemPosition(currentPosition, lastPosition);
                    notifyDataSetChanged();
                }
            } else {
                if (countTrue >= socialChoice.getData().getNbChoice())
                    return;
                countTrue++;
                System.out.println(" ^^ coutn = "+countTrue);
                check.setChecked(!check.isChecked());
                clickedItem.setChecked(true);
                if(currentPosition != 0){
                    changeItemPosition(currentPosition, 0);
                    notifyDataSetChanged();
                }
            }*/
        }
    }

}
