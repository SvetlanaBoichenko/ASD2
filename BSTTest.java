import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class BSTTest {
    BSTNode <Integer> nod8 = new  BSTNode<>(8, 8, null);

    BST<Integer> biTree = new BST (nod8);

    BSTNode <Integer> nod4 = new  BSTNode(4, 4, nod8);
    BSTNode <Integer> nod12 = new  BSTNode(12, 12, nod8);
  //  nod8.LeftNode =  nod4;

    BSTNode <Integer> nod2 = new  BSTNode(2, 2, nod4);
    BSTNode <Integer> nod6 = new  BSTNode(6, 6, nod4);

    BSTNode <Integer> nod10 = new  BSTNode(10, 10, nod12);
    BSTNode <Integer> nod14 = new  BSTNode(14, 14, nod12);

    BSTNode <Integer> nod1 = new  BSTNode(1, 1, nod2);
    BSTNode <Integer> nod3 = new  BSTNode(3, 3, nod2);

    BSTNode <Integer> nod5 = new  BSTNode(5, 5, nod6);
    BSTNode <Integer> nod7 = new  BSTNode(7, 7, nod6);
    BSTNode <Integer> nod9 = new  BSTNode(9, 9, nod10);
    BSTNode <Integer> nod11 = new  BSTNode(11, 11, nod10);

    BSTNode <Integer> nod13 = new  BSTNode(13, 13, nod14);
    BSTNode <Integer> nod15 = new  BSTNode(15, 15, nod14);

    @Test
    public void findNodeByKey() {
        BSTNode<Integer> nod8 = new BSTNode<>(8, 8, null);
        BST<Integer> biTree = new BST(nod8);
        BSTFind<Integer> bstResult = biTree.FindNodeByKey(1);
        assertTrue(bstResult.NodeHasKey == false);
        bstResult = biTree.FindNodeByKey(8);
        assertTrue(bstResult.NodeHasKey == true);

        BSTNode<Integer> max = biTree.FinMinMax(biTree.Root, true);
        assertTrue(max.NodeValue == 8);
        BSTNode<Integer> min = biTree.FinMinMax(biTree.Root, false);
        assertTrue(min.NodeValue == 8);

        BSTNode<Integer> nod4 = new BSTNode(4, 4, nod8);
        BSTNode<Integer> nod12 = new BSTNode(12, 12, nod8);
        nod8.LeftChild = nod4;
        nod8.RightChild = nod12;
         max = biTree.FinMinMax(biTree.Root, true);
        assertTrue(max.NodeValue == 12);
         min = biTree.FinMinMax(biTree.Root, false);
        assertTrue(min.NodeValue == 4);

        BSTNode<Integer> nod2 = new BSTNode(2, 2, nod4);
        BSTNode<Integer> nod6 = new BSTNode(6, 6, nod4);
        nod4.LeftChild = nod2;
        nod4.RightChild = nod6;

        BSTNode<Integer> nod10 = new BSTNode(10, 10, nod12);
        BSTNode<Integer> nod14 = new BSTNode(14, 14, nod12);
        nod12.LeftChild = nod10;
        nod12.RightChild = nod14;

        BSTNode<Integer> nod1 = new BSTNode(1, 1, nod2);
        BSTNode<Integer> nod3 = new BSTNode(3, 3, nod2);
        nod2.LeftChild = nod1;
        nod2.RightChild = nod3;

        BSTNode<Integer> nod5 = new BSTNode(5, 5, nod6);
        BSTNode<Integer> nod7 = new BSTNode(7, 7, nod6);
        nod6.LeftChild = nod5;
        nod6.RightChild = nod7;

        BSTNode<Integer> nod9 = new BSTNode(9, 9, nod10);
        BSTNode<Integer> nod11 = new BSTNode(11, 11, nod10);
        nod10.LeftChild = nod9;
        nod10.RightChild = nod11;

         max = biTree.FinMinMax(biTree.Root, true);
        assertTrue(max.NodeValue == 14);
         min = biTree.FinMinMax(biTree.Root, false);
        assertTrue(min.NodeValue == 1);

        BSTNode<Integer> nod13 = new BSTNode(13, 13, nod14);
        BSTNode<Integer> nod15 = new BSTNode(15, 15, nod14);
        nod14.LeftChild = nod13;
        nod14.RightChild = nod15;

        bstResult = biTree.FindNodeByKey(1);
        assertTrue(bstResult.NodeHasKey == true);

        bstResult = biTree.FindNodeByKey(8);
        assertTrue(bstResult.NodeHasKey == true);

        bstResult = biTree.FindNodeByKey(15);
        assertTrue(bstResult.NodeHasKey == true);

         max = biTree.FinMinMax(biTree.Root, true);
        assertTrue(max.NodeValue == 15);
        min = biTree.FinMinMax(biTree.Root, false);
        assertTrue(min.NodeValue == 1);
    }
   // @Test
    //public void finMinMax() {
   // }

    @Test
    public void deleteNodeByKey() {
        BSTNode<Integer> nod8 = new BSTNode<>(8, 8, null);
        BST<Integer> biTree = new BST(nod8);

        BSTFind<Integer> bstResult = biTree.FindNodeByKey(1);
        assertTrue(bstResult.NodeHasKey == false);
        bstResult = biTree.FindNodeByKey(8);
        assertTrue(bstResult.NodeHasKey == true);

        BSTNode<Integer> max = biTree.FinMinMax(biTree.Root, true);
        assertTrue(max.NodeValue == 8);
        BSTNode<Integer> min = biTree.FinMinMax(biTree.Root, false);
        assertTrue(min.NodeValue == 8);

        BSTNode<Integer> nod4 = new BSTNode(4, 4, nod8);
        BSTNode<Integer> nod12 = new BSTNode(12, 12, nod8);
        nod8.LeftChild = nod4;
        nod8.RightChild = nod12;
        max = biTree.FinMinMax(biTree.Root, true);
        assertTrue(max.NodeValue == 12);
        min = biTree.FinMinMax(biTree.Root, false);
        assertTrue(min.NodeValue == 4);

        int N = biTree.Count();
        assertTrue(N == 3);

        BSTNode<Integer> nod2 = new BSTNode(2, 2, nod4);
        BSTNode<Integer> nod6 = new BSTNode(6, 6, nod4);
        nod4.LeftChild = nod2;
        nod4.RightChild = nod6;

        BSTNode<Integer> nod10 = new BSTNode(10, 10, nod12);
        BSTNode<Integer> nod14 = new BSTNode(14, 14, nod12);
        nod12.LeftChild = nod10;
        nod12.RightChild = nod14;

        BSTNode<Integer> nod1 = new BSTNode(1, 1, nod2);
        BSTNode<Integer> nod3 = new BSTNode(3, 3, nod2);
        nod2.LeftChild = nod1;
        nod2.RightChild = nod3;

        N = biTree.Count();
        assertTrue(N == 9);

        BSTNode<Integer> nod5 = new BSTNode(5, 5, nod6);
        BSTNode<Integer> nod7 = new BSTNode(7, 7, nod6);
        nod6.LeftChild = nod5;
        nod6.RightChild = nod7;

        BSTNode<Integer> nod9 = new BSTNode(9, 9, nod10);
        BSTNode<Integer> nod11 = new BSTNode(11, 11, nod10);
        nod10.LeftChild = nod9;
        nod10.RightChild = nod11;
        N = biTree.Count();
        assertTrue(N == 13);


        biTree.DeleteNodeByKey(1);
        BSTFind<Integer> findnode = biTree.FindNodeByKey(1);
        assertTrue(findnode.NodeHasKey == false);

        boolean ret = biTree.AddKeyValue(1,1);
        findnode = biTree.FindNodeByKey(1);
        assertTrue(findnode.Node.NodeKey == 1);

        findnode = biTree.FindNodeByKey(111);
        assertTrue(findnode.NodeHasKey == false);

        biTree.DeleteNodeByKey(5);
        findnode = biTree.FindNodeByKey(5);
        assertTrue(findnode.NodeHasKey == false);

        findnode = biTree.FindNodeByKey(6);
        assertTrue(findnode.NodeHasKey == true);

        biTree.DeleteNodeByKey(8);
        findnode = biTree.FindNodeByKey(8);
        assertTrue(findnode.NodeHasKey == false);
        assertTrue(biTree.Root.NodeValue == 9);

        ret = biTree.AddKeyValue(6,6);
        findnode = biTree.FindNodeByKey(6);
        assertTrue(findnode.Node.NodeKey == 6);

    }



    @Test
    public void count() {
    }
}