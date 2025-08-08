import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class BSTTest {
    public static BST GreatBSTTree ()
    {
        BSTNode<Integer> nod8 = new BSTNode<>(8, 8, null);
        BST BinTree = new BST<>(nod8);
        BSTNode<Integer> nod4 = new BSTNode(4, 4, nod8);
        BSTNode<Integer> nod12 = new BSTNode(12, 12, nod8);

        BSTNode<Integer> nod2 = new BSTNode(2, 2, nod4);
        BSTNode<Integer> nod6 = new BSTNode(6, 6, nod4);

        BSTNode<Integer> nod10 = new BSTNode(10, 10, nod12);
        BSTNode<Integer> nod14 = new BSTNode(14, 14, nod12);

        BSTNode<Integer> nod1 = new BSTNode(1, 1, nod2);
        BSTNode<Integer> nod3 = new BSTNode(3, 3, nod2);

        BSTNode<Integer> nod5 = new BSTNode(5, 5, nod6);
        BSTNode<Integer> nod7 = new BSTNode(7, 7, nod6);
        BSTNode<Integer> nod9 = new BSTNode(9, 9, nod10);
        BSTNode<Integer> nod11 = new BSTNode(11, 11, nod10);

        BSTNode<Integer> nod13 = new BSTNode(13, 13, nod14);
        BSTNode<Integer> nod15 = new BSTNode(15, 15, nod14);
        nod8.  LeftChild = nod4;
        nod8.RightChild = nod12;
        nod4.LeftChild = nod2;
        nod4.RightChild = nod6;

        nod12.LeftChild = nod10;
        nod12.RightChild = nod14;

        nod10.LeftChild = nod9;
        nod10.RightChild = nod11;

        nod2.LeftChild = nod1;
        nod2.RightChild = nod3;

        return BinTree;
    }

    @Test
    public void tesPassLevels() {
        BST BiTree = GreatBSTTree();
        ArrayList <BSTNode> listlevels =  BiTree.WideAllNodes() ;

        int s = listlevels.size();
        assertTrue (s == 11);

        BSTNode n1 = listlevels.get(0);
        assertTrue (n1.NodeKey == 8);

        n1 = listlevels.get(1);
        assertTrue (n1.NodeKey == 4);
        n1 = listlevels.get(2);
        assertTrue (n1.NodeKey == 12);
        n1 = listlevels.get(3);
        assertTrue (n1.NodeKey == 2);
        n1 = listlevels.get(4);
        assertTrue (n1.NodeKey == 6);
        n1 = listlevels.get(5);
        assertTrue (n1.NodeKey == 10);
        n1 = listlevels.get(6);
        assertTrue (n1.NodeKey == 14);
        n1 = listlevels.get(7);
        assertTrue (n1.NodeKey == 1);
        n1 = listlevels.get(8);
        assertTrue (n1.NodeKey == 3);
        n1 = listlevels.get(9);
        assertTrue (n1.NodeKey == 9);
        n1 = listlevels.get(10);
        assertTrue (n1.NodeKey == 11);

        BSTNode<Integer> nod18 = new BSTNode<>(8, 8, null);
        BST BinTree2 = new BST (nod18);
        ArrayList <BSTNode> listlevels2 =  BinTree2.WideAllNodes() ;

        s = listlevels2.size();
        assertTrue (s == 1);
        n1 = listlevels2.get(0);
        assertTrue (n1.NodeKey == 8);

        BST BinTree3 = new BST (null);
        ArrayList <BSTNode> listlevels3 =  BinTree3.WideAllNodes() ;
        s = listlevels3.size();
        assertTrue (s == 0);
    }

    @Test
    public void testDeepAllNodes() {
        BST BiTree = GreatBSTTree ();

        // pre
        ArrayList <BSTNode> L0 = BiTree.DeepAllNodes (2);
        int n = L0.size();
        assertTrue (n == 11);
        BSTNode n0 = L0.get(0);
        assertTrue (n0.NodeKey == 8);
        n0 = L0.get(1);
        assertTrue (n0.NodeKey == 4);
        n0 = L0.get(2);
        assertTrue (n0.NodeKey == 2);
        n0 = L0.get(3);
        assertTrue (n0.NodeKey == 1);
        n0 = L0.get(4);
        assertTrue (n0.NodeKey == 3);
        n0 = L0.get(5);
        assertTrue (n0.NodeKey == 6);
        n0 = L0.get(6);
        assertTrue (n0.NodeKey == 12);
        n0 = L0.get(7);
        assertTrue (n0.NodeKey == 10);
        n0 = L0.get(8);
        assertTrue (n0.NodeKey == 9);

        // in
        ArrayList <BSTNode> L1 = BiTree.DeepAllNodes (0);
        n = L1.size();
        assertTrue (n == 11);
        n0 = L1.get(5);
        assertTrue (n0.NodeKey == 8);
        n0 = L1.get(0);
        assertTrue (n0.NodeKey == 4);
        n0 = L1.get(1);
        assertTrue (n0.NodeKey == 2);
        n0 = L1.get(2);
        assertTrue (n0.NodeKey == 1);
        n0 = L1.get(3);
        assertTrue (n0.NodeKey == 3);
        n0 = L1.get(4);
        assertTrue (n0.NodeKey == 6);
        n0 = L1.get(5);
        assertTrue (n0.NodeKey == 8);
        n0 = L1.get(6);
        assertTrue (n0.NodeKey == 12);
        n0 = L1.get(8);
        assertTrue (n0.NodeKey == 9);

        // post
        ArrayList <BSTNode> L2 = BiTree.DeepAllNodes (1);
        n = L2.size();
        assertTrue (n == 11);
        n0 = L2.get(10);
        assertTrue (n0.NodeKey == 8);
        n0 = L2.get(0);
        assertTrue (n0.NodeKey == 4);
        n0 = L2.get(1);
        assertTrue (n0.NodeKey == 2);
        n0 = L2.get(2);
        assertTrue (n0.NodeKey == 1);
        n0 = L2.get(3);
        assertTrue (n0.NodeKey == 3);
        n0 = L2.get(4);
        assertTrue (n0.NodeKey == 6);
        n0 = L2.get(5);
        assertTrue (n0.NodeKey == 12);
        n0 = L2.get(6);
        assertTrue (n0.NodeKey == 10);
        n0 = L2.get(7);
        assertTrue (n0.NodeKey == 9);

        ArrayList <BSTNode> L31 = BiTree.DeepAllNodes (5);
        n = L31.size();
        assertTrue (n == 0);
        BSTNode<Integer> nod10 = new BSTNode(10, 10,null);

        BST BiTree21 = new BST<> (nod10);
        ArrayList <BSTNode> L21 = BiTree21.DeepAllNodes (1);
        n = L21.size();
        assertTrue (n == 1);
        n0 = L21.get(0);
        assertTrue (n0.NodeKey == 10);

        ArrayList <BSTNode> L20 = BiTree21.DeepAllNodes (0);
        n = L20.size();
        assertTrue (n == 1);
        n0 = L20.get(0);
        assertTrue (n0.NodeKey == 10);

        ArrayList <BSTNode> L22 = BiTree21.DeepAllNodes (2);
        n = L22.size();
        assertTrue (n == 1);
        n0 = L22.get(0);
        assertTrue (n0.NodeKey == 10);


        BST BiTree51 = new BST<> (null);
        ArrayList <BSTNode> L51 = BiTree51.DeepAllNodes (1);
        n = L51.size();
        assertTrue (n ==0);

        ArrayList <BSTNode> L50 = BiTree51.DeepAllNodes (0);
        n = L50.size();
        assertTrue (n ==0);

        ArrayList <BSTNode> L52 = BiTree51.DeepAllNodes (2);
        n = L52.size();
        assertTrue (n ==0);

    }
}
