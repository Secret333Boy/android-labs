package edu.kpi.android.labs;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CancelFragment extends Fragment {

    private Button cancelButton;
    private final InputFragment inputFragment;
    private final OutputFragment outputFragment;

    public CancelFragment(InputFragment inputFragment, OutputFragment outputFragment) {
        this.inputFragment = inputFragment;
        this.outputFragment = outputFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cancel, container, false);
        rootView.setVisibility(View.GONE);
        cancelButton = rootView.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(v -> {
            inputFragment.clearText();
            outputFragment.setMessageOutput("");
        });

        inputFragment.onInputTextChange(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    rootView.setVisibility(View.VISIBLE);
                } else {
                    rootView.setVisibility(View.GONE);
                }
            }
        });

        return rootView;
    }
}
