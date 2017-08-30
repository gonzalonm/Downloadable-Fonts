package com.lalosoft.downloadablefontsdemo.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.FontRes;

import com.lalosoft.downloadablefontsdemo.R;

public class CustomDownloadableFontProvider implements FontProvider {

    public static final int MONTSERRAT_FONT = R.font.montserrat;

    private int resourceFont;

    public CustomDownloadableFontProvider(@FontRes int resourceFont) {
        this.resourceFont = resourceFont;
    }

    @Override
    public Typeface getTypeface(Context context, String fontStyleName) {
        return context.getResources().getFont(resourceFont);
    }

}
