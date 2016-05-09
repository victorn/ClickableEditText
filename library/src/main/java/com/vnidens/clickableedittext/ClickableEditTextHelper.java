package com.vnidens.clickableedittext;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.lang.ref.WeakReference;

/**
 * KeeDroid
 * <p/>
 * Created by Victor Nidens
 * Date: 20.04.2016
 * <p/>
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016 Victor Nidens
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the KeeDroid), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
class ClickableEditTextHelper implements View.OnTouchListener, ClickableTextInput{

    public static final int[] STATE_DEFAULT = new int[]{android.R.attr.state_enabled};
    public static final int[] STATE_PRESSED = new int[]{android.R.attr.state_pressed};

    private static final int EXTRA_TOUCHABLE_AREA           = 8; //dp
    private static final int DRAWABLE_POSITION_START        = 0;
    private static final int DRAWABLE_POSITION_END          = 1;

    private final WeakReference<EditText> viewWeakRef;
    private final int extraTouchableAreaPx;

    private Drawable startButtonDrawable;
    private ColorStateList startButtonTintList;
    private Drawable endButtonDrawable;
    private ColorStateList endButtonTintList;

    private boolean handlingTouchEventStart = false;
    private boolean handlingTouchEventEnd = false;

    private OnCompoundButtonClickListener onStartButtonClickListener;
    private OnCompoundButtonClickListener onEndButtonClickListener;


    public ClickableEditTextHelper(@NonNull EditText view, @Nullable AttributeSet attrs, int defStyleAttrs){
        viewWeakRef = new WeakReference<>(view);
        view.setOnTouchListener(this);

        extraTouchableAreaPx = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                EXTRA_TOUCHABLE_AREA,
                view.getResources().getDisplayMetrics());

        applyViewAttributes(view, attrs, defStyleAttrs);
    }

    public ClickableEditTextHelper(@NonNull EditText view, @Nullable AttributeSet attrs){
        this(view, attrs, 0);
    }

    public ClickableEditTextHelper(@NonNull EditText view){
        this(view, null, 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        if(!(v instanceof EditText)){
            return false;
        } else {
            int xTouch = (int)event.getX();
            int yTouch = (int)event.getY();

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(isTouchInside(v, xTouch, yTouch, DRAWABLE_POSITION_START)){
                        handlingTouchEventStart = true;
                        startButtonDrawable.setState(STATE_PRESSED);
                        startButtonDrawable.invalidateSelf();
                    } else if(isTouchInside(v, xTouch, yTouch, DRAWABLE_POSITION_END)){
                        handlingTouchEventEnd = true;
                        endButtonDrawable.setState(STATE_PRESSED);
                        endButtonDrawable.invalidateSelf();
                    }

                    if(handlingTouchEventStart
                            || handlingTouchEventEnd){
                        event.setAction(MotionEvent.ACTION_CANCEL);
                        return true;
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    boolean insideStart = isTouchInside(v, xTouch, yTouch, DRAWABLE_POSITION_START);
                    boolean insideEnd = isTouchInside(v, xTouch, yTouch, DRAWABLE_POSITION_END);

                    if(handlingTouchEventStart
                            || insideStart
                            || handlingTouchEventEnd
                            || insideEnd){

                        boolean consumed = false;

                        if(handlingTouchEventStart && insideStart){
                            onStartButtonClickListener.onCompoundButtonClicked((EditText)v, R.id.cet_button_start);
                            consumed = true;
                        }

                        if(handlingTouchEventEnd && insideEnd){
                            onEndButtonClickListener.onCompoundButtonClicked((EditText)v, R.id.cet_button_end);
                            consumed = true;
                        }

                        if(startButtonDrawable != null){
                            startButtonDrawable.setState(STATE_DEFAULT);
                            startButtonDrawable.invalidateSelf();
                        }

                        if(endButtonDrawable != null){
                            endButtonDrawable.setState(STATE_DEFAULT);
                            endButtonDrawable.invalidateSelf();
                        }

                        handlingTouchEventStart = false;
                        handlingTouchEventEnd = false;

                        return consumed;
                    }

                    break;

                default:
                    break;
            }
        }

        return false;
    }

    @Override
    public void setStartButtonDrawable(Drawable drawable){
        startButtonDrawable = drawable;
        updateDrawableTintList(startButtonDrawable, startButtonTintList);

        invalidateDrawables();
    }

    @Override
    public void setStartButtonDrawable(@DrawableRes int drawable){
        EditText et = viewWeakRef.get();
        if(et != null){
            setStartButtonDrawable(ResourcesCompat.getDrawable(et.getResources(), drawable, et.getContext().getTheme()));
        }
    }

    @Nullable
    @Override
    public Drawable getStartButtonDrawable(){
        return startButtonDrawable;
    }

    @Override
    public void setStartButtonTintList(ColorStateList tintList){
        startButtonTintList = tintList;

        updateDrawableTintList(startButtonDrawable, startButtonTintList);
    }

    @Override
    public void setStartButtonTintRes(@ColorRes int colorRes){
        EditText et = viewWeakRef.get();
        if(et != null){
            setStartButtonTintList(ContextCompat.getColorStateList(et.getContext(), colorRes));
        }
    }

    @Override
    public void setStartButtonTint(@ColorInt int color){
        startButtonTintList = new ColorStateList(new int[][]{STATE_DEFAULT}, new int[]{color});

        updateDrawableTintList(startButtonDrawable, startButtonTintList);
    }

    @Override
    public ColorStateList getStartButtonTintList(){
        return startButtonTintList;
    }

    @Override
    public void setEndButtonDrawable(Drawable drawable){
        endButtonDrawable = drawable;
        updateDrawableTintList(endButtonDrawable, endButtonTintList);

        invalidateDrawables();
    }

    @Override
    public void setEndButtonDrawable(@DrawableRes int drawable){
        EditText et = viewWeakRef.get();
        if(et != null){
            setEndButtonDrawable(ResourcesCompat.getDrawable(et.getResources(), drawable, et.getContext().getTheme()));
        }
    }

    @Nullable
    @Override
    public Drawable getEndButtonDrawable(){
        return endButtonDrawable;
    }

    @Override
    public void setEndButtonTintList(ColorStateList tintList){
        endButtonTintList = tintList;

        updateDrawableTintList(endButtonDrawable, endButtonTintList);
    }

    @Override
    public void setEndButtonTintRes(@ColorRes int colorRes){
        EditText et = viewWeakRef.get();
        if(et != null){
            setEndButtonTintList(ContextCompat.getColorStateList(et.getContext(), colorRes));
        }
    }

    @Override
    public void setEndButtonTint(@ColorInt int color){
        endButtonTintList = new ColorStateList(new int[][]{STATE_DEFAULT}, new int[]{color});

        updateDrawableTintList(endButtonDrawable, endButtonTintList);
    }

    @Override
    public ColorStateList getEndButtonTintList(){
        return endButtonTintList;
    }

    @Override
    public void setOnStartButtonClickListener(@NonNull OnCompoundButtonClickListener listener){
        onStartButtonClickListener = listener;
    }

    @Override
    public void removeOnStartButtonClickListener(OnCompoundButtonClickListener listener){
        if(onStartButtonClickListener == listener){
            onStartButtonClickListener = null;
        }
    }

    @Override
    public void setOnEndButtonClickListener(@NonNull OnCompoundButtonClickListener listener){
        onEndButtonClickListener = listener;
    }

    @Override
    public void removeOnEndButtonClickListener(OnCompoundButtonClickListener listener){
        if(onEndButtonClickListener == listener){
            onEndButtonClickListener = null;
        }
    }

    private void applyViewAttributes(@NonNull EditText view, @Nullable AttributeSet attrs, int defStyleAttr){
        if(attrs == null){
            return;
        }

        TypedArray ta = view.getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ClickableEditText, defStyleAttr, 0);

        try{
            startButtonDrawable = ta.getDrawable(R.styleable.ClickableEditText_cet_drawableStart);
            endButtonDrawable = ta.getDrawable(R.styleable.ClickableEditText_cet_drawableEnd);

            if(startButtonDrawable != null){
                startButtonDrawable = startButtonDrawable.mutate();
            }

            if(endButtonDrawable != null){
                endButtonDrawable = endButtonDrawable.mutate();
            }

            startButtonTintList = ta.getColorStateList(R.styleable.ClickableEditText_cet_drawableStartTint);
            endButtonTintList = ta.getColorStateList(R.styleable.ClickableEditText_cet_drawableEndTint);

            updateDrawableTintList(startButtonDrawable, startButtonTintList);
            updateDrawableTintList(endButtonDrawable, endButtonTintList);

        } finally {
            ta.recycle();
        }


        invalidateDrawables();
    }

    private boolean isTouchInside(@NonNull View v, int xTouch, int yTouch, int drawablePosition){
        Rect iconRect;

        int viewPaddingStart = ViewCompat.getPaddingStart(v);
        int viewPaddingEnd = ViewCompat.getPaddingEnd(v);
        int ltDirection = ViewCompat.getLayoutDirection(v);

        switch(drawablePosition){
            case DRAWABLE_POSITION_START:
                if(startButtonDrawable == null
                        || onStartButtonClickListener == null){
                    return false;
                }

                iconRect = startButtonDrawable.copyBounds();
                break;

            case DRAWABLE_POSITION_END:
                if(endButtonDrawable == null
                        || onEndButtonClickListener == null){
                    return false;
                }

                iconRect = endButtonDrawable.copyBounds();
                break;

            default:
                return false;
        }

        int viewCenterVertical = v.getPaddingTop() + ((v.getHeight() - v.getPaddingBottom()) - v.getPaddingTop())/2;

        if((drawablePosition == DRAWABLE_POSITION_START && ltDirection == ViewCompat.LAYOUT_DIRECTION_LTR)
                || (drawablePosition == DRAWABLE_POSITION_END && ltDirection == ViewCompat.LAYOUT_DIRECTION_RTL)){

            iconRect.offsetTo(viewPaddingStart, viewCenterVertical - iconRect.centerY());

        } else if((drawablePosition == DRAWABLE_POSITION_START && ltDirection == ViewCompat.LAYOUT_DIRECTION_RTL)
                || (drawablePosition == DRAWABLE_POSITION_END && ltDirection == ViewCompat.LAYOUT_DIRECTION_LTR)){

            iconRect.offsetTo(v.getWidth() - viewPaddingEnd - iconRect.width(), viewCenterVertical - iconRect.centerY());

        } else {
            return false;
        }

        iconRect.left = iconRect.left - extraTouchableAreaPx;
        iconRect.right = iconRect.right + extraTouchableAreaPx;
        iconRect.top = iconRect.top - extraTouchableAreaPx;
        iconRect.bottom = iconRect.bottom + extraTouchableAreaPx;

        return iconRect.contains(xTouch, yTouch);
    }

    private void invalidateDrawables(){
        EditText view = viewWeakRef.get();
        if(view != null){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(startButtonDrawable, null, endButtonDrawable, null);
            } else {
                view.setCompoundDrawablesWithIntrinsicBounds(startButtonDrawable, null, endButtonDrawable, null);
            }
        }
    }

    private void updateDrawableTintList(Drawable drawable, ColorStateList colorStateList){
        if(drawable != null){
            DrawableCompat.setTintList(drawable, colorStateList);
        }
    }
}
