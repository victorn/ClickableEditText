package com.vnidens.clickableedittext;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.util.AttributeSet;

/**
 * KeeDroid
 * <p/>
 * Created by Victor Nidens
 * Date: 10.04.2016
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
/**
 * The class extends the {@link AppCompatMultiAutoCompleteTextView} with a functionality to treat the start and end compound
 * drawables as buttons.
 *
 * <br/><br/>
 *
 * You can apply the {@link OnCompoundButtonClickListener} for the start or
 * the end drawable via {@link #setOnStartButtonClickListener(OnCompoundButtonClickListener)} and
 * {@link #setOnEndButtonClickListener(OnCompoundButtonClickListener)} respectively.
 *
 * <br/><br/>
 *
 * You can set drawables for each button either in code via {@link #setStartButtonDrawable}
 * and {@link #setEndButtonDrawable} or in XML using <code>cet_drawableStart</code> and
 * <code>cet_drawableEnd</code>.
 *
 * <br/><br/>
 *
 * You can also add tinting for each button's drawable in code via {@link #setStartButtonTintRes(int)},
 * {@link #setStartButtonTintList(ColorStateList)}, {@link #setEndButtonTintRes(int)},
 * {@link #setEndButtonTintList(ColorStateList)} or in XML using <code>cet_drawableStartTint</code>
 * and <code>cet_drawableEndTint</code>.
 */
public class ClickableMultiAutoCompleteTextView extends AppCompatMultiAutoCompleteTextView
        implements ClickableTextInput{

    private final ClickableEditTextHelper helper;

    public ClickableMultiAutoCompleteTextView(@NonNull Context context){
        this(context, null);
    }

    public ClickableMultiAutoCompleteTextView(@NonNull Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        helper = new ClickableEditTextHelper(this, attrs, defStyleAttr);
    }

    public ClickableMultiAutoCompleteTextView(@NonNull Context context, AttributeSet attrs){
        super(context, attrs);
        helper = new ClickableEditTextHelper(this, attrs, 0);
    }

    @Override
    public void setStartButtonDrawable(Drawable drawable){
        helper.setStartButtonDrawable(drawable);
    }

    @Override
    public void setStartButtonDrawable(@DrawableRes int drawable){
        helper.setStartButtonDrawable(drawable);
    }

    @Nullable
    @Override
    public Drawable getStartButtonDrawable(){
        return helper.getStartButtonDrawable();
    }

    @Override
    public void setStartButtonTintList(ColorStateList tintList){
        helper.setStartButtonTintList(tintList);
    }

    @Override
    public void setStartButtonTintRes(@ColorRes int colorRes){
        helper.setStartButtonTintRes(colorRes);
    }

    @Override
    public void setStartButtonTint(@ColorInt int color){
        helper.setStartButtonTint(color);
    }

    @Override
    public ColorStateList getStartButtonTintList(){
        return helper.getStartButtonTintList();
    }

    @Override
    public void setEndButtonDrawable(Drawable drawable){
        helper.setEndButtonDrawable(drawable);
    }

    @Override
    public void setEndButtonDrawable(@DrawableRes int drawable){
        helper.setEndButtonDrawable(drawable);
    }

    @Nullable
    @Override
    public Drawable getEndButtonDrawable(){
        return helper.getEndButtonDrawable();
    }

    @Override
    public void setEndButtonTintList(ColorStateList tintList){
        helper.setEndButtonTintList(tintList);
    }

    @Override
    public void setEndButtonTintRes(@ColorRes int colorRes){
        helper.setEndButtonTintRes(colorRes);
    }

    @Override
    public void setEndButtonTint(@ColorInt int color){
        helper.setEndButtonTint(color);
    }

    @Override
    public ColorStateList getEndButtonTintList(){
        return helper.getEndButtonTintList();
    }

    @Override
    public void setOnStartButtonClickListener(@NonNull OnCompoundButtonClickListener listener){
        helper.setOnStartButtonClickListener(listener);
    }

    @Override
    public void removeOnStartButtonClickListener(OnCompoundButtonClickListener listener){
        helper.removeOnStartButtonClickListener(listener);
    }

    @Override
    public void setOnEndButtonClickListener(@NonNull OnCompoundButtonClickListener listener){
        helper.setOnEndButtonClickListener(listener);
    }

    @Override
    public void removeOnEndButtonClickListener(OnCompoundButtonClickListener listener){
        helper.removeOnEndButtonClickListener(listener);
    }

    @Override
    protected void drawableStateChanged(){
        super.drawableStateChanged();

        if(helper.getStartButtonDrawable() != null){
            helper.getStartButtonDrawable().setState(ClickableEditTextHelper.STATE_DEFAULT);
        }

        if(helper.getEndButtonDrawable() != null){
            helper.getEndButtonDrawable().setState(ClickableEditTextHelper.STATE_DEFAULT);
        }
    }


}
