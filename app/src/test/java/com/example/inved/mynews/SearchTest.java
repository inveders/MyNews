package com.example.inved.mynews;

import com.example.inved.mynews.controller.Search;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

public class SearchTest {

    /**INSTALLER MOCKITO, VOIR LE SPY*/
   /* @Test
    public void createWallet() throws Exception {
        Search wallet = new Search(42);

        assertEquals(42, wallet.getBalance(), 0.001);
    }*/


   // private CheckBox checkboxTechnology;

  /* @Test
   public void Given_true_When_isCheckboxChecked_Then_LaunchSearch(){
           // CheckBox checkboxTechnology = (CheckBox)v;
         /*   if (checkboxTechnology.isChecked()){
                Log.d("DEBAGoa","OK");
            }*/
//test unitaire ne prend jamais param


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
    }


}
