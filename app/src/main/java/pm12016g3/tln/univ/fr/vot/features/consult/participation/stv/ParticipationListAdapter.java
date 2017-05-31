package pm12016g3.tln.univ.fr.vot.features.consult.participation.stv;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemAdapter;

import java.util.List;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.features.consult.participation.stv.ParticipationListAdapter.ViewHolder;

/**
 * Created by wenlixing on 22/05/2017.
 */

public class ParticipationListAdapter
        extends  DragItemAdapter<ParticipationItem,ParticipationListAdapter.ViewHolder>{

    private final static String TAG = ParticipationListAdapter.class.getSimpleName();
    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;

    public ParticipationListAdapter(List<ParticipationItem> list, int layoutId,
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
        return mItemList.get(position).getId();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ParticipationItem item = mItemList.get(position);
        if(item.isChecked())
            holder.choice_id.setText(String.valueOf(position + 1));
        else
            holder.choice_id.setText(" ");
        holder.choice_title.setText(item.getChoice_title());
        holder.check.setChecked(item.isChecked());
    }

    class ViewHolder extends DragItemAdapter.ViewHolder{

        TextView choice_id;
        TextView choice_title;
        CheckedTextView check;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            choice_id = (TextView) itemView.findViewById(R.id.participation_choice_id);
            choice_title = (TextView) itemView.findViewById(R.id.participation_choice_title);
            check = (CheckedTextView) itemView.findViewById(R.id.participation_choice_check_tv);
        }

        @Override
        public void onItemClicked(View view) {


            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
            check.setChecked(!check.isChecked());
            ParticipationItem clickedItem = mItemList.get(getAdapterPosition());
            if(check.isChecked()){
                clickedItem.setChecked(true);
                choice_id.setText(String.valueOf(getAdapterPosition()+1));
            }else {
                clickedItem.setChecked(false);
                choice_id.setText(" ");
                changeItemPosition(getAdapterPosition(),getItemCount()-1);
                Log.d("TAG",mItemList.toString());
                notifyDataSetChanged();
            }

        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
