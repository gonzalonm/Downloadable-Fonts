package com.lalosoft.downloadablefontsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lalosoft.downloadablefontsdemo.font.CustomDownloadableFontProvider;
import com.lalosoft.downloadablefontsdemo.font.FontHandler;
import com.lalosoft.downloadablefontsdemo.font.LocalFontProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView title = findViewById(R.id.title);
        final TextView text = findViewById(R.id.text);
        Button applyLocalFont = findViewById(R.id.apply_local_font_button);
        Button applyRemoteFont = findViewById(R.id.apply_remote_font_button);

        final FontHandler.FontHandlerCallback callback = new FontHandler.FontHandlerCallback() {
            @Override
            public void onFontApplySuccess() {
                Toast.makeText(MainActivity.this,
                        R.string.applied_font,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFontApplyError(Throwable e) {
                Toast.makeText(MainActivity.this,
                        R.string.font_not_applied,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        };

        applyLocalFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FontHandler fontHandler = new FontHandler.Builder()
                        .provider(new LocalFontProvider(LocalFontProvider.DROID_SERIF))
                        .addTextView(title, FontHandler.FontStyle.BOLD)
                        .addTextView(text)
                        .build();

                fontHandler.applyFont(callback);
            }
        });

        applyRemoteFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FontHandler fontHandler = new FontHandler.Builder()
                        .provider(new CustomDownloadableFontProvider(CustomDownloadableFontProvider.MONTSERRAT_FONT))
                        .addTextView(title, FontHandler.FontStyle.BOLD)
                        .addTextView(text)
                        .build();

                fontHandler.applyFont(callback);
            }
        });

    }
}
