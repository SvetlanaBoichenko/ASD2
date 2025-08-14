import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public  class aBSTTest {

    @Test
    public void tesFindKeyIndex() {
        aBST arrTree = new aBST(4);
        int treeLen = arrTree.Tree.length;
        assertTrue (treeLen == 15);
        assertEquals  ((int)(arrTree.FindKeyIndex(119)), 0);

        arrTree.Tree[0] = 8;
        int res = arrTree.FindKeyIndex (8);
        assertTrue (res == 0);

        arrTree.Tree[1] = 4;
        arrTree.Tree[2] = 12;
        arrTree.Tree[3] = 2;
        arrTree.Tree[4] = 6;

        res = arrTree.FindKeyIndex (1);
        assertTrue (res == -7);
        arrTree.Tree[7] = 1;
        res = arrTree.FindKeyIndex (1);
        assertTrue (res == 7);

        arrTree.Tree[5] = 10;
        arrTree.Tree[6] = 14;
        arrTree.Tree[7] = 1;
        arrTree.Tree[8] = 3;
        arrTree.Tree[9] = 5;
        arrTree.Tree[10] = 7;

        res = arrTree.FindKeyIndex (5);
        assertTrue (res == 9);

        res = arrTree.FindKeyIndex (1);
        assertTrue (res == 7);

        res = arrTree.FindKeyIndex (15);
        assertTrue (res == -14);

        arrTree.Tree[11] = 9;
        arrTree.Tree[12] = 11;
        arrTree.Tree[13] = 13;
        arrTree.Tree[14] = 15;

        res = arrTree.FindKeyIndex (15);
        assertTrue (res == 14);

        res = arrTree.FindKeyIndex (11);
        assertTrue (res == 12);

    }

    @Test
    public void TestAddKey() {

        aBST arrTree = new aBST(4);
        arrTree.AddKey(8);

        assertTrue (arrTree.Tree[0] == 8);
        arrTree.AddKey(4);
        assertTrue (arrTree.Tree[1] == 4);

        arrTree.AddKey(12);
        assertTrue (arrTree.Tree[2] == 12);

        arrTree.AddKey(6);
        assertTrue (arrTree.Tree[4] == 6);

        int res = arrTree.AddKey(2);
        assertTrue (res == 3);

        arrTree.AddKey(14);
        assertTrue (arrTree.Tree[6] == 14);

        res = arrTree.AddKey(10);
        assertTrue (res == 5);

        arrTree.AddKey(5);
        assertTrue (arrTree.Tree[9] == 5);

        arrTree.AddKey(11);
        assertTrue (arrTree.Tree[12] == 11);

        arrTree.AddKey(13);
        assertTrue (arrTree.Tree[13] == 13);

        arrTree.AddKey(15);
        assertTrue (arrTree.Tree[14] == 15);



        res = arrTree.AddKey(11);
        assertTrue (res == 12);

        res = arrTree.AddKey(7);
        assertTrue (res == 10);

        res = arrTree.AddKey(3);
        assertTrue (res == 8);



        res = arrTree.AddKey(1);
        assertTrue (res == 7);




    }

}