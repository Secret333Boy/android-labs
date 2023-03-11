package edu.kpi.android.labs;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static Map<String, Integer> fontMap = new HashMap<>();

    static {
        fontMap.put("Small", 8);
        fontMap.put("Medium", 16);
        fontMap.put("Large", 64);
    }

    private EditText messageInput;
    private TextView messageOutput;
    private RadioGroup fontSizesGroup;
    private RadioButton selectedFontButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.message_input);
        messageOutput = findViewById(R.id.message_output);
        fontSizesGroup = findViewById(R.id.font_sizes_group);

        Button okButton = findViewById(R.id.ok_button);
        Button cancelButton = findViewById(R.id.cancel_button);

        okButton.setOnClickListener(v -> {
            int selectedId = fontSizesGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                showToast("Виберіть розмір шрифту");
                return;
            }

            if (messageInput.getText().toString().length() == 0) {
                showToast("Наберіть, будь-ласка, повідомлення");
                return;
            }

            selectedFontButton = findViewById(selectedId);
            try {
                int fontSize = fontMap.get(selectedFontButton.getText().toString());

                String message = messageInput.getText().toString();

                messageOutput.setText(message);
                messageOutput.setTextSize(fontSize);
                messageOutput.setTypeface(null, Typeface.BOLD);
            } catch (NullPointerException e) {
                showToast(e.toString());
            }
        });

        cancelButton.setOnClickListener(v -> {
            messageInput.setText("");
            messageOutput.setText("");
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
