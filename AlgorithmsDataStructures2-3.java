import static org.junit.jupiter.api.Assertions.*;

class GenerateBBSTArrayTest {

    @org.junit.jupiter.api.Test
    void generateBBSTArray() {
        int A [] = {7,5,3,1,6,2,4};
        AlgorithmsDataStructures2 AD1 = new AlgorithmsDataStructures2();
       int [] res = AD1.GenerateBBSTArray(A);

        int waitres []  = {4,2,6,1,3,5,7};
        assertArrayEquals(res,waitres);

    }

    @org.junit.jupiter.api.Test
    void makeBSTArray()
    {
        int A [] = {1,2,3,4,5,6,7};   //,9,10,11,12,13,14,15};
        int B [] = new int [A.length];
        AlgorithmsDataStructures2 AD = new AlgorithmsDataStructures2();

        int res  [] = AD.makeBSTArray ( 0, A.length-1,   0 ,A,  B);

        int waitres []  = {4,2,6,1,3,5,7};
        assertArrayEquals(res,waitres);
//-----------------------------------------
        int A1 [] = {1,2,3,};   //,9,10,11,12,13,14,15};
        int B1 [] = new int [A1.length];

        int res2  [] = AD.makeBSTArray
                ( 0, A1.length-1,   0 ,A1,  B1);

        int waitres2 []  = {2,1,3};
        assertArrayEquals(waitres2,res2);
        //------------------------------------------------------------
        int A3[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int B3 [] = new int [A3.length];

        int res3  [] = AD.makeBSTArray
                ( 0, A3.length-1,   0 ,A3,  B3);

        int waitres3 []  = {8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
        assertArrayEquals(waitres3,res3);
        //-------------------------------------------------------------
        int A4[] = {13};
        int B4 [] = new int [A4.length];

        int res4  [] = AD.makeBSTArray
                ( 0, A4.length-1,   0 ,A4,  B4);

        int waitres4 []  = {13};
        assertArrayEquals(waitres4,res4);
        //-------------------------------------------------------------
        int A5[] = {1,2};
        int B5[] = new int [2];

        int res5  [] = AD.makeBSTArray
                ( 0, A5.length-1,   0 ,A5, B5);

        int waitres5 []  = {1,0};
        assertArrayEquals(waitres5,res5);
    }
}
