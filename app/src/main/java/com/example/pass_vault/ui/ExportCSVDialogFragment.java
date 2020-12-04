package com.example.pass_vault.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pass_vault.R;
import com.example.pass_vault.data.AccountItem;
import com.example.pass_vault.data.AccountsList;
import com.example.pass_vault.utilities.CSVUtility;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

public class ExportCSVDialogFragment extends DialogFragment {

    private static final String TAG = "ExportCSVDialogFragment";

    private EditText editTextFileName;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_export_csv_dialog, null);

        TextView message = (TextView) view.findViewById(R.id.text_dialog_message);
        message.setText("Directory: " + getActivity().getExternalFilesDir(null).toString());

        editTextFileName = (EditText) view.findViewById(R.id.edit_dialog_file_name);

        builder.setTitle("Export CSV")
                .setView(view)
                .setPositiveButton("Export CSV", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (!editTextFileName.getText().toString().isEmpty()) {
                            saveCSV();
                        }
                    }
                });

        return builder.create();
    }

    private void saveCSV() {
        new Thread(() -> {
            String root = Objects.requireNonNull(getActivity()).getExternalFilesDir(null).toString();
            Log.d(TAG, "saveCSV: " + root);
            File directory = new File(root);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = editTextFileName.getText().toString() + ".csv";
            File csvFile = new File(directory, fileName);

            LinkedBlockingDeque<AccountItem> accounts = AccountsList.getAccountsSaved(getContext());

            CSVUtility.write(csvFile, getContext(), accounts);
        }).start();
    }
}
