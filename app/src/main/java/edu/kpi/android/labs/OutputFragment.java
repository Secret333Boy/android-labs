package edu.kpi.android.labs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OutputFragment extends Fragment {

    private TextView messageOutput;

    public OutputFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.output, container, false);

        messageOutput = rootView.findViewById(R.id.message_output);

        return rootView;
    }

    public void setMessageOutput(String string) {
        messageOutput.setText(string);
    }

    public void setOutputTextSize(int fontSize) {
        messageOutput.setTextSize(fontSize);
    }

    public void setOutputTypeface(int typeface) {
        messageOutput.setTypeface(null, typeface);
    }
}
