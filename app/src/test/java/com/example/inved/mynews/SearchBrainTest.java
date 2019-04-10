package com.example.inved.mynews;

import android.widget.CheckBox;
import android.widget.EditText;

import com.example.inved.mynews.brain.SearchBrain;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class SearchBrainTest {


//test unitaire ne prend jamais param


    private SearchBrain spySearchBrain = Mockito.spy(new SearchBrain()); //Utiliser cette notation


    @Test
    public void should_Return_Lucene_With_Good_UserInput(){
        //Given
    /*    List<String> userInput ="J'aime les patates";

        //When
        List<String> lucene = spySearchBrain.getLucene(userInput);

        //Then
        Assert.assertEquals("title:\"J'aime les patates\" OR body:\"J'aime les patates\"",lucene);*/


        //    Mockito.verify(spySearch,times(1)).getOperand();
        //  Mockito.verify(spySearch).getOperand(); /**Quand on veut vérifier 1 fois c'est pareil que la ligne du haut*/
        //  Mockito.verifyNoMoreInteractions(spySearch); //On vérifie qu'aucune autre mth n'a été appelée
    }



}
