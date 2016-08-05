package github.hellocsl.ucmainpager.behavior.helper;


import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewParent;

/**
 * Copy from Android design library
 * <p>
 * Utility helper for moving a {@link android.view.View} around using
 * {@link android.view.View#offsetLeftAndRight(int)} and
 * {@link android.view.View#offsetTopAndBottom(int)}.
 * <p>
 * Also the setting of absolute offsets (similar to translationX/Y), rather than additive
 * offsets.
 */
public class ViewOffsetHelper {

    private final View mView;

    private int mLayoutTop;
    private int mLayoutLeft;
    private int mOffsetTop;
    private int mOffsetLeft;

    public ViewOffsetHelper(View view) {
        mView = view;
    }

    public void onViewLayout() {
        // Now grab the intended top
        mLayoutTop = mView.getTop();
        mLayoutLeft = mView.getLeft();

        // And offset it as needed
        updateOffsets();
    }

    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(mView, mOffsetTop - (mView.getTop() - mLayoutTop));
        ViewCompat.offsetLeftAndRight(mView, mOffsetLeft - (mView.getLeft() - mLayoutLeft));

        // Manually invalidate the view and parent to make sure we get drawn pre-M
        if (Build.VERSION.SDK_INT < 23) {
            tickleInvalidationFlag(mView);
            final ViewParent vp = mView.getParent();
            if (vp instanceof View) {
                tickleInvalidationFlag((View) vp);
            }
        }
    }

    private static void tickleInvalidationFlag(View view) {
        final float y = ViewCompat.getTranslationY(view);
        ViewCompat.setTranslationY(view, y + 1);
        ViewCompat.setTranslationY(view, y);
    }

    /**
     * Set the top and bottom offset for this {@link ViewOffsetHelper}'s view.
     *
     * @param offset the offset in px.
     * @return true if the offset has changed
     */
    public boolean setTopAndBottomOffset(int offset) {
        if (mOffsetTop != offset) {
            mOffsetTop = offset;
            updateOffsets();
            return true;
        }
        return false;
    }

    /**
     * Set the left and right offset for this {@link ViewOffsetHelper}'s view.
     *
     * @param offset the offset in px.
     * @return true if the offset has changed
     */
    public boolean setLeftAndRightOffset(int offset) {
        if (mOffsetLeft != offset) {
            mOffsetLeft = offset;
            updateOffsets();
            return true;
        }
        return false;
    }

    public int getTopAndBottomOffset() {
        return mOffsetTop;
    }

    public int getLeftAndRightOffset() {
        return mOffsetLeft;
    }
}
