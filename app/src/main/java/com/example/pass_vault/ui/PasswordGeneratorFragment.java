package com.example.pass_vault.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pass_vault.R;

public class PasswordGeneratorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_generator, container, false);

        if (this.getActivity() != null) {
            ((AppCompatActivity) this.getActivity()).getSupportActionBar().setTitle("Password Generator");
        }

        return view;
    }
}
