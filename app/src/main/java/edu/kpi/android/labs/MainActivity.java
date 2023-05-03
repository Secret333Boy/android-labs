package edu.kpi.android.labs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        InputFragment inputFragment = new InputFragment();
        OutputFragment outputFragment = new OutputFragment();
        CancelFragment cancelFragment = new CancelFragment(inputFragment, outputFragment);
        fragmentManager.beginTransaction().add(R.id.input_container, inputFragment).add(R.id.cancel_container, cancelFragment).add(R.id.output_container, outputFragment).commit();

        Button okButton = findViewById(R.id.ok_button);
        Button openButton = findViewById(R.id.open_button);

        okButton.setOnClickListener(v -> {
            String message = inputFragment.getMessageText();
            if (message.length() == 0) {
                showToast("Наберіть, будь-ласка, повідомлення");
                return;
            }

            try {
                int fontSize = inputFragment.getSelectedFontSize();

                outputFragment.setMessageOutput(message);
                outputFragment.setOutputTextSize(fontSize);
                outputFragment.setOutputTypeface(Typeface.BOLD);

                FileOutputStream fileOutputStream = openFileOutput("store.txt", MODE_APPEND);

                fileOutputStream.write((fontSize + ";" + message + "\n").getBytes(StandardCharsets.UTF_8));
                fileOutputStream.close();
                showToast("Successfully saved result!");
            } catch (NullPointerException | IOException e) {
                showToast(e.toString());
            }
        });

        openButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DataActivity.class);
            startActivity(intent);
        });
    }

    private void showToast(String message) {
        TextView toastView = new TextView(this);
        toastView.setTextColor(Color.rgb(255, 255, 255));
        toastView.setText(message);
        toastView.setGravity(Gravity.CENTER);
        toastView.setTextSize(20);
        toastView.setBackgroundColor(Color.rgb(0, 0, 200));

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }
}
