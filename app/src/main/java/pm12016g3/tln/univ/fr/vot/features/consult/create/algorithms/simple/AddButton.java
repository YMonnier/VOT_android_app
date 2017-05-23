package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;

import org.androidannotations.annotations.EView;

/**
 * Project android.
 * Package pm12016g3.tln.univ.fr.vot.features.consult.create.algorithms.simple.
 * File AddButton.java.
 * Created by Ysee on 23/05/2017 - 17:01.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */

@EView
public class AddButton extends android.support.v7.widget.AppCompatButton {

    public AddButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AddButton(Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
        anim.setDuration(500);
        anim.setFillAfter(true);
        this.startAnimation(anim);
    }
}
