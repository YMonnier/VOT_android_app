package pm12016g3.tln.univ.fr.vot.utilities.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.utilities.views.
 * File ViewWrapper.java.
 * Created by Ysee on 15/05/2017 - 16:13.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {
    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }
}
