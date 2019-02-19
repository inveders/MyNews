package com.example.inved.mynews;

import com.example.inved.mynews.controller.Search;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
   /* @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private Search spySearch = Mockito.spy(new Search()); //Utiliser cette notation

    @Test
    public void givenParam1AndParam2_whenAddition_thenResult(){ //Test classique
        //    Given
        Search search = new Search();
        int param1 = 1;
        int param2 = 2;
        //When
        int result = search.addition(param1,param2);
        //Then : on vérifie tout ce qu'il nous faut (assertion)
        Assert.assertEquals(3,result);
    }


    @Test
    public void doOperationAddCorrectly(){
        //Given
        int param1 =20;
        int param2 =30;
        String operand = "+";
        Mockito.doReturn(operand).when(spySearch).getOperand();

        //When
        int result = spySearch.doOperation(param1,param2);

        //Then
        Assert.assertEquals(50,result);

        Mockito.verify(spySearch,times(1)).getOperand();
        //  Mockito.verify(spySearch).getOperand(); /**Quand on veut vérifier 1 fois c'est pareil que la ligne du haut*/
        //  Mockito.verifyNoMoreInteractions(spySearch); //On vérifie qu'aucune autre mth n'a été appelée
 /*   }



  /*  public int addition(int pA, int pB) {
        return pA + pB;
    }

    public String getOperand() {
        switch (new Random().nextInt(4)) {
            case 0:
                return "+";
            case 1:
                return "-";
            default:
                return "*";
        }
    }

    public int doOperation(int a, int b) {
        String operand = getOperand();
        switch (operand) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;

        }
        return 0;
    }*/
}