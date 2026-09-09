package com.example.crimeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Date;

public class CrimeFragment extends Fragment {

    private static final int REQUEST_DATE = 0;

    private Button mDateButton;
    private Date mDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mDate = new Date();

        mDateButton = view.findViewById(R.id.date_button);
        updateDate();

        mDateButton.setOnClickListener(v -> {

            DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);

            dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
            dialog.show(getParentFragmentManager(), "DateDialog");
        });

        return view;
    }

    private void updateDate() {
        mDateButton.setText(mDate.toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == REQUEST_DATE) {
            mDate = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            updateDate();
        }
    }
}