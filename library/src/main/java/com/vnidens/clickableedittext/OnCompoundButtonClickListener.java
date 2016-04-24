package com.vnidens.clickableedittext;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.widget.EditText;

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
public interface OnCompoundButtonClickListener{
    /**
     * Called when a compound button has been clicked
     *
     * @param view The {@link EditText} view which contains the clicked compound button
     * @param which Id of the compound button which has been clicked.<br/>
     *              <code>com.vnidens.clickableedittext.R.id.cet_button_start</code> if the start button has been clicked<br/>
     *              <code>com.vnidens.clickableedittext.R.id.cet_button_end</code> if the end button has been clicked<br/>
     */
    void onCompoundButtonClicked(@NonNull final EditText view, @IdRes long which);
}
