package com.lalosoft.downloadablefontsdemo.font;

import android.graphics.Typeface;
import android.os.Handler;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FontHandler {

    private FontProvider provider;
    private List<FontItem> fontItemList;

    public enum FontStyle {
        BOLD, ITALIC, REGULAR
    }

    public interface FontHandlerCallback {
        void onFontApplySuccess();

        void onFontApplyError(Throwable e);
    }

    private FontHandler(List<FontItem> fontItemList, FontProvider provider) {
        this.fontItemList = fontItemList;
        this.provider = provider;
    }

    public void applyFont(final FontHandlerCallback callback) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    for (FontItem item : fontItemList) {
                        Typeface typeface = provider.getTypeface(item.textView.getContext(),
                                getStyleName(item.fontStyle));
                        item.textView.setTypeface(typeface);
                    }
                    callback.onFontApplySuccess();
                } catch (Exception e) {
                    callback.onFontApplyError(e);
                }
            }
        });
    }

    private String getStyleName(FontStyle fontStyle) {
        return fontStyle.name().toLowerCase();
    }

    public static class Builder {
        private FontProvider provider;
        private List<FontItem> fontItemList;

        public Builder() {
            this.fontItemList = new ArrayList<>();
        }

        public Builder provider(FontProvider provider) {
            this.provider = provider;
            return this;
        }

        public Builder addTextView(TextView textView, FontStyle fontStyle) {
            fontItemList.add(new FontItem(textView, fontStyle));
            return this;
        }

        public Builder addTextView(TextView textView) {
            fontItemList.add(new FontItem(textView));
            return this;
        }

        public FontHandler build() {
            return new FontHandler(fontItemList, provider);
        }

    }

    private static class FontItem {
        public TextView textView;
        public FontStyle fontStyle;

        public FontItem(TextView textView, FontStyle fontStyle) {
            this.textView = textView;
            this.fontStyle = fontStyle;
        }

        public FontItem(TextView textView) {
            this.textView = textView;
            this.fontStyle = FontStyle.REGULAR;
        }
    }
}
