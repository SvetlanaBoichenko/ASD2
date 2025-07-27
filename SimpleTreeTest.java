import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class SimpleTreeTest {

    SimpleTreeNode<Integer>  myRoot = new SimpleTreeNode(1, null);
    SimpleTree<Integer> myTree = new SimpleTree<Integer> (myRoot);

    SimpleTreeNode<Integer> ch2 = new SimpleTreeNode<Integer>(2, myRoot);
    SimpleTreeNode<Integer> ch3 = new SimpleTreeNode<Integer>(3, myRoot);
    SimpleTreeNode<Integer> ch4 = new SimpleTreeNode<Integer>(4, myRoot);
    SimpleTreeNode<Integer> ch5 = new SimpleTreeNode<Integer>(5, myRoot);
    SimpleTreeNode<Integer> ch6 = new SimpleTreeNode<Integer>(6, myRoot);
    SimpleTreeNode<Integer> ch7 = new SimpleTreeNode<Integer>(7, myRoot);

    SimpleTreeNode<Integer> ch8 = new SimpleTreeNode<Integer>(8, myRoot);
    SimpleTreeNode<Integer> ch9 = new SimpleTreeNode<Integer>(9, myRoot);
    SimpleTreeNode<Integer> ch10 = new SimpleTreeNode<Integer>(10, myRoot);
    SimpleTreeNode<Integer> ch11 = new SimpleTreeNode<Integer>(11, myRoot);


    @Test
    public void testAddChild() {
        myTree.Root = null;
        assertTrue (myTree.Count() == 0);

        myTree.Root = myRoot;
        myTree.AddChild(myRoot, ch2);
        myTree.AddChild(myRoot, ch3);

        assertTrue (myTree.Root.Children.size() == 2);
        SimpleTreeNode<Integer> tempNode = myTree.Root.Children.getFirst();
        assertTrue (tempNode.NodeValue == 2);
        tempNode = myTree.Root.Children.getLast();
        assertTrue (tempNode.NodeValue == 3);

        myTree.AddChild(ch2, ch4);
        myTree.AddChild(ch2, ch5);

        int tmpsize =  ch2.Children.size();
        assertTrue (tmpsize == 2);

        tempNode = ch2.Children.getFirst();
        assertTrue (tempNode.NodeValue == 4);
        tempNode = ch2.Children.getLast();
        assertTrue (tempNode.NodeValue == 5);
    }

    @Test
    public void testRemoveNode() {
        myTree.AddChild(myRoot, ch2);
        myTree.AddChild(myRoot, ch3);
        myTree.AddChild(ch2, ch4);
        myTree.AddChild(ch2, ch5);

        myTree.AddChild(ch4, ch8);
        myTree.DeleteNode(ch8);
        assertTrue (ch4.Children == null);

        myTree.AddChild(ch4, ch8);
        myTree.AddChild(ch4, ch9);
        myTree.DeleteNode(ch8);
        assertTrue (ch4.Children.size() == 1);
        assertTrue (ch4.Children.get(0) == ch9);

        myTree.AddChild(ch4, ch8);
        myTree.AddChild(ch3, ch6);
        myTree.AddChild(ch3, ch7);

        myTree.AddChild(ch5, ch10);
        myTree.AddChild(ch10, ch11);

        myTree.DeleteNode(ch11);
        assertTrue (ch10.Children == null);

        myTree.DeleteNode(ch4);
        assertTrue (ch2.Children.size() == 3);
        assertTrue (ch2.Children.get(0) == ch5);
        assertTrue (ch2.Children.get(1) == ch9);
        assertTrue (ch2.Children.get(2) == ch8);

        myTree.DeleteNode(myTree.Root);
        assertTrue ( myTree.Root == null);

    }
    @Test
    public void GetAllNodes() {
        myTree.Root = null;

        int cnt = myTree.Count();
        assertTrue (cnt == 0);
        List<SimpleTreeNode<Integer>> allNodes = myTree.GetAllNodes();
        assertTrue (allNodes.isEmpty());

        myTree.Root = myRoot;
        allNodes = myTree.GetAllNodes();
        assertTrue (allNodes.size() == 1);

        myTree.AddChild(myRoot, ch2);
        myTree.AddChild(myRoot, ch3);

        myTree.AddChild(ch2, ch4);
        myTree.AddChild(ch2, ch5);

        allNodes = myTree.GetAllNodes();
        assertTrue ( allNodes.size() == 5);

        myTree.AddChild(ch4, ch8);
        myTree.AddChild(ch4, ch9);

        myTree.AddChild(ch3, ch6);
        myTree.AddChild(ch3, ch7);

        myTree.AddChild(ch5, ch10);
        myTree.AddChild(ch10, ch11);
        allNodes = myTree.GetAllNodes();
        assertTrue ( allNodes.size() == 11);
    }

    @Test
    public void GetAllValues() {
        int cnt = myTree.Count();
        assertTrue (cnt == 1);

        int leafCount = myTree.LeafCount();
        assertTrue (leafCount == 0);

        myTree.AddChild(myRoot, ch2);
        myTree.AddChild(myRoot, ch3);
        myTree.AddChild(ch2, ch4);
        myTree.AddChild(ch2, ch5);

        cnt = myTree.Count();
        assertTrue (cnt == 5);

        leafCount = myTree.LeafCount();
        assertTrue (leafCount == 3);

        List<SimpleTreeNode<Integer>> allVal = myTree.FindNodesByValue(11);
        assertTrue (allVal.isEmpty());

        allVal = myTree.FindNodesByValue(4);
        assertTrue (allVal.size() == 1);
        assertTrue (allVal.get(0).NodeValue == 4);

        cnt = myTree.Count();
        assertTrue (cnt == 5);

        myTree.AddChild(ch4, ch8);
        myTree.AddChild(ch4, ch9);

        myTree.AddChild(ch3, ch6);
        myTree.AddChild(ch3, ch7);

        myTree.AddChild(ch5, ch10);
        myTree.AddChild(ch10, ch11);

        leafCount = myTree.LeafCount();
        assertTrue (leafCount == 5);

        SimpleTreeNode<Integer> ch12 = new SimpleTreeNode<Integer>(2, myRoot);
        SimpleTreeNode<Integer> ch13 = new SimpleTreeNode<Integer>(2, myRoot);
        myTree.AddChild(ch3, ch13);
        myTree.AddChild(ch2, ch12);

        leafCount = myTree.LeafCount();
        assertTrue (leafCount == 7);

        allVal = myTree.FindNodesByValue(2);
        assertTrue (allVal.size() == 3);
        assertTrue (allVal.get(0).NodeValue == 2);
        assertTrue (allVal.get(1).NodeValue == 2);
        assertTrue (allVal.get(2).NodeValue == 2);

        cnt = myTree.Count();
        assertTrue (cnt == 13);

    }
}
