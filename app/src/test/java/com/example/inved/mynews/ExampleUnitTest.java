package com.example.inved.mynews;

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

    private SearchActivity spySearch = Mockito.spy(new SearchActivity()); //Utiliser cette notation

    @Test
    public void givenParam1AndParam2_whenAddition_thenResult(){ //Test classique
        //    Given
        SearchActivity activity_search = new SearchActivity();
        int param1 = 1;
        int param2 = 2;
        //When
        int result = activity_search.addition(param1,param2);
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