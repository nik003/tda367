package gruppnan.timeline.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by Melina on 27/05/2017.
 */

public class ItemListAdapterView {

    public ItemListAdapterView() {

    }
    public void setCardListeners(CardView card1, View.OnClickListener oncl1, CardView card2, View.OnClickListener oncl2) {
        card1.setOnClickListener(oncl1);
        card2.setOnClickListener(oncl2);
    }


}
