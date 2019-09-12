package com.example.inved.mynews;

import com.example.inved.mynews.brain.SearchBrain;

import org.junit.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SearchBrainTest {

    private SearchBrain spySearchBrain = Mockito.spy(new SearchBrain());


    @Test
    public void should_Return_Lucene_With_Two_Good_Checkboxes_Checked() {
        //Given
        List<String> userInput = new ArrayList<>();
        userInput.add("Technology");
        userInput.add("World");

        //When
        String lucene = spySearchBrain.getLucene(userInput);

        //Then
        Assert.assertEquals("section_name:(\"Technology\"\"World\")", lucene);

    }


}
