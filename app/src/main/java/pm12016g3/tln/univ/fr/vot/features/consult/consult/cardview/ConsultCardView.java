package pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pm12016g3.tln.univ.fr.vot.R;
import pm12016g3.tln.univ.fr.vot.models.SocialChoice;
import pm12016g3.tln.univ.fr.vot.utilities.views.Bindable;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.consult.cardview.
 * File ConsultCardView.java.
 * Created by Ysee on 18/05/2017 - 11:22.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EViewGroup(R.layout.consult_consult_card_item)
public class ConsultCardView extends LinearLayout
        implements Bindable<SocialChoice> {

    @ViewById(R.id.image)
    ImageView imageView;

    @ViewById(R.id.text)
    TextView title;

    @ViewById(R.id.is_closed_tv)
    TextView closed;

    public ConsultCardView(Context context) {
        super(context);
    }

    @Override
    public void bind(SocialChoice object) {
        imageView.setImageResource(object.getDrawableImage());
        title.setText(object.getTitle());
        if (object.isClosed()) {
            closed.setVisibility(VISIBLE);
        } else {
            closed.setVisibility(INVISIBLE);
        }
    }
}
