package com.example.inved.mynews.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;

import com.example.inved.mynews.R;

import org.joda.time.LocalDateTime;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private SearchActivity myActivity ;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (SearchActivity) context; //on garde une référence vers l'activité
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(),R.style.MyDatePickerStyle, this, localDateTimeNow.getYear(), localDateTimeNow.getMonthOfYear()-1, localDateTimeNow.getDayOfMonth());
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        myActivity.onDatePicked(new LocalDateTime(year,month+1,day,0,0), (SearchActivity.WhatDatePickerTyped) getArguments().getSerializable(SearchActivity.KEY));

    }

}
