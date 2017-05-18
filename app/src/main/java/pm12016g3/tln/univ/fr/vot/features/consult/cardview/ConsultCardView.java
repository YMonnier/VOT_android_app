package pm12016g3.tln.univ.fr.vot.features.consult.cardview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.cardview.
 * File ConsultCardView.java.
 * Created by Ysee on 18/05/2017 - 11:22.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EViewGroup(R.layout.consult_consult_card_item)
public class ConsultCardView extends LinearLayout
implements Bindable<ConsultCardItem> {

    @ViewById(R.id.image)
    ImageView imageView;

    @ViewById(R.id.text)
    TextView title;

    public ConsultCardView(Context context) {
        super(context);
    }

    @Override
    public void bind(ConsultCardItem object) {
        imageView.setImageResource(object.getDrawableImage());
        title.setText(object.getTitle());
    }
}
