package com.example.inved.mynews;

import android.widget.CheckBox;
import android.widget.EditText;

import com.example.inved.mynews.brain.SearchBrain;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class SearchBrainTest {


//test unitaire ne prend jamais param


    private SearchBrain spySearchBrain = Mockito.spy(new SearchBrain()); //Utiliser cette notation


    @Test
    public void should_Return_Lucene_With_Good_UserInput(){
        //Given
        String userInput ="J'aime les patates";

        //When
        String lucene = spySearchBrain.getLucene(userInput);

        //Then
        Assert.assertEquals("title:\"J'aime les patates\" OR body:\"J'aime les patates\"",lucene);


        //    Mockito.verify(spySearch,times(1)).getOperand();
        //  Mockito.verify(spySearch).getOperand(); /**Quand on veut vérifier 1 fois c'est pareil que la ligne du haut*/
        //  Mockito.verifyNoMoreInteractions(spySearch); //On vérifie qu'aucune autre mth n'a été appelée
    }

    @Test
    public void should_Return_Filter_Name_With_CheckBox_Position(){
        //Given
        int position = 1;

        //When
        String filter = spySearchBrain.getFilter(position);

        //Then
        Assert.assertEquals("technology",filter);


        //    Mockito.verify(spySearch,times(1)).getOperand();
        //  Mockito.verify(spySearch).getOperand(); /**Quand on veut vérifier 1 fois c'est pareil que la ligne du haut*/
        //  Mockito.verifyNoMoreInteractions(spySearch); //On vérifie qu'aucune autre mth n'a été appelée
    }


}
