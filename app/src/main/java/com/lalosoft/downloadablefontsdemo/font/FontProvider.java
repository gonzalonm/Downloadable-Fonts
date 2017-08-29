package com.lalosoft.downloadablefontsdemo.font;

import android.content.Context;
import android.graphics.Typeface;

public interface FontProvider {
    Typeface getTypeface(Context context, String fontStyleName);
}
