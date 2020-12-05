package com.example.pass_vault.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pass_vault.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ImportCSVDialogFragment extends DialogFragment {

    private static final String TAG = "ImportCSVDialogFragment";

    private ListView listViewCSVFiles;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_import_csv_dialog, null);

        String root = Objects.requireNonNull(getActivity()).getExternalFilesDir(null).toString();
        File directory = new File(root);

        TextView textDialogMessage = (TextView) view.findViewById(R.id.text_import_dialog_msg);
        textDialogMessage.setText(String.format(Locale.getDefault(), "Place CSV files in %s",
                directory.toString()));

        String[] csvFiles = extractFileNames(directory);
        ArrayAdapter<String> csvFilesAdapater = new ArrayAdapter<String>(getContext(), R.layout.list_view_item,
                csvFiles);
        listViewCSVFiles = (ListView) view.findViewById(R.id.list_view_csv_files);
        listViewCSVFiles.setAdapter(csvFilesAdapater);
        listViewCSVFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + csvFiles[position]);
                dismiss();
            }
        });

        builder.setTitle("Import CSV")
                .setView(view);

        return builder.create();
    }

    private String[] extractFileNames(File directory) {
        File[] files = directory.listFiles();
        ArrayList<String> csvFiles = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                String[] paths = file.toString().split("/");
                csvFiles.add(paths[paths.length -1]);
            }
        }

        return csvFiles.toArray(new String[0]);
    }
}
