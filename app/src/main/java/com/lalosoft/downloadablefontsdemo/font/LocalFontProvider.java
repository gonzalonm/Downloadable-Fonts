package com.lalosoft.downloadablefontsdemo.font;

import android.content.Context;
import android.graphics.Typeface;

public class LocalFontProvider implements FontProvider {

    // Droid Serif Font
    private static final String DROID_SERIF_FOLDER = "fonts/droid_serif";
    public static final String DROID_SERIF = DROID_SERIF_FOLDER + "/droid_serif-";

    // Droid Sans Font
    private static final String DROID_SANS_FOLDER = "fonts/droid_sans";
    public static final String DROID_SANS = DROID_SANS_FOLDER + "/droid_sans-";

    private String fontName;

    public LocalFontProvider(String fontName) {
        this.fontName = fontName;
    }

    @Override
    public Typeface getTypeface(Context context, String fontStyleName) {
        String name = fontName + fontStyleName + ".ttf";
        return Typeface.createFromAsset(context.getAssets(), name);
    }
}
