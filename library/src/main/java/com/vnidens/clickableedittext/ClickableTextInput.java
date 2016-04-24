package com.vnidens.clickableedittext;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * ClickableTextViewSample
 * <p/>
 * Created by Victor Nidens
 * Date: 21.04.2016
 * <p/>
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016 Victor Nidens
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the ClickableTextViewSample), to deal
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
public interface ClickableTextInput{
    /**
     * @param drawable {@link Drawable} to show as a start button
     */
    void setStartButtonDrawable(Drawable drawable);

    /**
     * @param drawable Drawable resource Id to show as a start button
     */
    void setStartButtonDrawable(@DrawableRes int drawable);

    /**
     * @return {@link Drawable} which is used for the start button
     */
    @Nullable
    Drawable getStartButtonDrawable();

    /**
     * Specifies tint color for the start button drawable.
     * @param tintList {@link ColorStateList} to apply. <code>null</code> to clear the tint.
     */
    void setStartButtonTintList(ColorStateList tintList);

    /**
     * Specifies tint color for the start button drawable.
     * @param colorRes <code>int</code> identifier of the color resource to apply
     */
    void setStartButtonTintRes(@ColorRes int colorRes);

    /**
     * Specifies tint color for the start button drawable.
     * @param color <code>int</code> color value.
     */
    void setStartButtonTint(@ColorInt int color);

    /**
     * @return {@link ColorStateList} the tint applied to the start button drawable
     */
    ColorStateList getStartButtonTintList();

    /**
     * @param drawable {@link Drawable} to show as an end button
     */
    void setEndButtonDrawable(Drawable drawable);

    /**
     * @param drawable Drawable resource Id to show as an end button
     */
    void setEndButtonDrawable(@DrawableRes int drawable);

    /**
     * @return {@link Drawable} which is used for the end button
     */
    @Nullable
    Drawable getEndButtonDrawable();

    /**
     * Specifies tint color for the end button drawable.
     * @param tintList {@link ColorStateList} to apply. <code>null</code> to clear the tint.
     */
    void setEndButtonTintList(ColorStateList tintList);

    /**
     * Specifies tint color for the end button drawable.
     * @param colorRes <code>int</code> identifier of the color resource to apply
     */
    void setEndButtonTintRes(@ColorRes int colorRes);

    /**
     * Specifies tint color for the end button drawable.
     * @param color <code>int</code> color value.
     */
    void setEndButtonTint(@ColorInt int color);

    /**
     * @return {@link ColorStateList} the tint applied to the start button drawable
     */
    ColorStateList getEndButtonTintList();

    /**
     * Sets a special listener to be called when the start button is clicked.
     * @param listener {@link OnCompoundButtonClickListener} to be called
     */
    void setOnStartButtonClickListener(@NonNull OnCompoundButtonClickListener listener);

    /**
     * Removes a special listener to be called when the start button is clicked. Listener will be
     * removed only if the provided object is registered as listener
     * @param listener {@link OnCompoundButtonClickListener} to remove
     */
    void removeOnStartButtonClickListener(OnCompoundButtonClickListener listener);

    /**
     * Sets a special listener to be called when the end button is clicked.
     * @param listener {@link OnCompoundButtonClickListener} to be called
     */
    void setOnEndButtonClickListener(@NonNull OnCompoundButtonClickListener listener);

    /**
     * Removes a special listener to be called when the end button is clicked. Listener will be
     * removed only if the provided object is registered as listener
     * @param listener {@link OnCompoundButtonClickListener} to remove
     */
    void removeOnEndButtonClickListener(OnCompoundButtonClickListener listener);
}
