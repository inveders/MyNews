package com.example.inved.mynews;

import com.example.inved.mynews.controller.Search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchTest {

    @Test
    public void createWallet() throws Exception {
        Search wallet = new Search(42);

        assertEquals(42, wallet.getBalance(), 0.001);
    }
}
