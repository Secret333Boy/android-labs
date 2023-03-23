package edu.kpi.android.labs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    private RadioButton fontSizeSmall;
    private RadioButton fontSizeMedium;
    private RadioButton fontSizeLarge;
    private EditText messageInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.input, container, false);

        fontSizeSmall = rootView.findViewById(R.id.font_size_small);
        fontSizeMedium = rootView.findViewById(R.id.font_size_medium);
        fontSizeLarge = rootView.findViewById(R.id.font_size_large);
        messageInput = rootView.findViewById(R.id.message_input);

        fontSizeMedium.setChecked(true);

        return rootView;
    }

    public int getSelectedFontSize() {
        if (fontSizeSmall.isChecked()) return 16;
        else if (fontSizeMedium.isChecked()) return 32;
        else if (fontSizeLarge.isChecked()) return 64;
        else return 0;
    }

    public String getMessageText() {
        return messageInput.getText().toString();
    }

    public void clearText() {
        messageInput.setText("");
    }
}

