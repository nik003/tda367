package gruppnan.timeline.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.widget.CalendarView;

/**
 * Created by Hannes on 16/05/2017.
 */

public class CalendarView2 extends CalendarView {

    public CalendarView2(@NonNull Context context) {
        super(context);
    }



    @Override
    protected void onDraw(Canvas canvas){
        //drawBackground(canvas);
        //drawWeekNumbersAndDates(canvas);
        //drawWeekSeparators(canvas);
       // drawSelectedDateVerticalBars(canvas);
    }

}
