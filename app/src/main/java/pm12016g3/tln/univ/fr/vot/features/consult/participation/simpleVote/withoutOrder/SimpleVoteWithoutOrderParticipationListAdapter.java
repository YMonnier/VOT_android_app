package pm12016g3.tln.univ.fr.vot.features.consult.participation.simpleVote.withoutOrder;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.Candidat;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.models.shared.SCSMajorityBallot;
import pm12016g3.tln.univ.fr.vot.utilities.json.GsonDeserializer;


/**
 * Created by wenlixing on 22/05/2017.
 */

public class SimpleVoteWithoutOrderParticipationListAdapter
        extends DragItemAdapter<Candidat, SimpleVoteWithoutOrderParticipationListAdapter.ViewHolder> {

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

    private SocialChoice<SCSMajorityBallot> socialChoice;

    private int countTrue;

    static int  countFalse = 0;

    public SimpleVoteWithoutOrderParticipationListAdapter(int layoutId,
                                                          int grabHandleId,
                                                          boolean dragOnLongPress,
                                                          SocialChoice<SCSMajorityBallot> socialChoice) {
        this.mLayoutId = layoutId;
        this.mGrabHandleId = grabHandleId;
        this.mDragOnLongPress = dragOnLongPress;
        this.socialChoice = socialChoice;
        setHasStableIds(true);
        setItemList(socialChoice.getCandidats());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).getName().hashCode();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Candidat item = mItemList.get(position);
        holder.choice_title.setText(item.getName());
        holder.check.setChecked(item.isSelected());
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

            System.out.println("size choice : "+socialChoice.getData().getNbChoice());

            int currentPosition = getAdapterPosition();
            int lastPosition = getItemCount()-1;
            Candidat clickedItem = mItemList.get(getAdapterPosition());

            if (check.isChecked()) {
                countTrue--;
                System.out.println(" ^^ coutn = "+countTrue);
                check.setChecked(!check.isChecked());
                clickedItem.setSelected(false);
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
                clickedItem.setSelected(true);
                if(currentPosition != 0){
                    changeItemPosition(currentPosition, 0);
                    notifyDataSetChanged();
                }
            }


        }
    }

}
