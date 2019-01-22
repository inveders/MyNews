package com.example.inved.mynews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.inved.mynews.controller.MainActivity;
import com.example.inved.mynews.controller.Search;

import org.junit.Test;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.assertEquals;

public class SearchTest {

   /* @Test
    public void createWallet() throws Exception {
        Search wallet = new Search(42);

        assertEquals(42, wallet.getBalance(), 0.001);
    }*/


    private CheckBox checkboxTechnology;

        @Test
        public void isCheckboxTechnologyChecked(){
           // CheckBox checkboxTechnology = (CheckBox)v;
            if (checkboxTechnology.isChecked()){
                Log.d("DEBAGoa","OK");
            }
        }




}
