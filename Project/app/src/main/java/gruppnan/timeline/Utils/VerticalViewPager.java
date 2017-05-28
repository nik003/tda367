package gruppnan.timeline.Utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by carlo on 2017-05-16.
 * Custom ViewPager class which scrolls vertically
 */

public class VerticalViewPager extends ViewPager {

    public VerticalViewPager(Context context) {
        super(context);

        setPageTransformer(true, new VerticalViewTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setPageTransformer(true, new VerticalViewTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    /**
     * Swaps x and y coordinates of the motion event
     */
    private MotionEvent swapXandY(MotionEvent motionEvent) {
        float width = getWidth();
        float height = getHeight();

        float newXpos = (motionEvent.getY() / height) * width;
        float newYpos = (motionEvent.getX() / width) * height;

        motionEvent.setLocation(newXpos, newYpos);

        return motionEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent){
        boolean intercepted = super.onInterceptTouchEvent(swapXandY(motionEvent));
        swapXandY(motionEvent);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(swapXandY(motionEvent));
    }


    /**
     * Deals with the swiping of pages
     */
    private static class VerticalViewTransformer implements PageTransformer {

        @Override
        public void transformPage(View page, float position) { //TODO CARLOS

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);

            } else if (position <= 1) {
                page.setAlpha(1);

                page.setTranslationX(page.getWidth() * -position);

                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);

            } else {
                // Pages off to the right
                page.setAlpha(0);
            }
        }
    }
}
