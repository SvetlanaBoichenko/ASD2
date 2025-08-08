//=================================  
// Заданиe 2 задачи 1, 2 и 3
//=================================

import java.io.*;
import java.util.*;

public class BSTNode2<T>
{
    public int NodeKey; 
    public T NodeValue; 
    public BSTNode2<T> Parent; 
    public BSTNode2<T> LeftChild; 
    public BSTNode2<T> RightChild; 

    public BSTNode2(int key, T val, BSTNode2<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}


class BST<T>
{
    BSTNode2<T> Root; // корень дерева, или null

    public BST(BSTNode2<T> node)
    {
        Root = node;
    }

//=========================================================
//  Задание 2   Задача 1
//  Метод который проверяет идентичность двух бинарных деревье
//  сложность O(N) и пространственная и временная 
//==========================================================    

    public boolean CompareBinTrees (BST binTree2)
    {
        return CompareAllNodes ( binTree2.Root, this.Root);
    }
    
    //--
    boolean CompareAllNodes (BSTNode2<T> node1, BSTNode2<T> node2)
    {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null && node2 != null)
            return false;

        if (node2 == null && node1 != null)
            return false;

        if (node1.NodeKey == node2.NodeKey)
            return true;

        return CompareAllNodes(node1.LeftChild, node2.LeftChild) &&
                CompareAllNodes(node1.RightChild, node2.RightChild);
        }

//=====================================================================
//  Задание 2   Задача 2
//  Метод, который нахождит все пути от корня к листьям, заданной длины
//  сложность O(N) и пространственная и временная 
//==========================================================   

    List<Integer> waysofLen (BSTNode2 node, int len, int n) {
        List<Integer> sList = new ArrayList<>();

        if (node == null) {
            return sList;
        }

        if (n >= len ||  isLeaf(node)) {
            sList.add(n);
            return sList;
        }
        // левая а потом правая
        int m = n+1;
        sList.addAll(waysofLen (node.LeftChild, len, m));
        sList.addAll(waysofLen (node.RightChild, len, m));
        return sList;
    } 

//---------------------------------------------------------------------
    public boolean isLeaf (BSTNode2 node) {
        if (node != null && node.RightChild == null && node.RightChild == null)
            return true;
      return false;
    }
//-----------------------------------------------------------------------

//=====================================================================
//  Задание 2   Задача 3
//  Метод, который находит все пути от корня к листьям, с мах суммой значений узлов
//  сложность O(N) и пространственная и временная 
//======================================================================      
 
    List <Integer> sumNodeWays (BSTNode2 node, int k) {
        List<Integer> sList = new ArrayList<>();

        if (node == null) {
            return sList;
        }

        sList.add(k);
        k = k + node.NodeKey;
      
        if (isLeaf (node)) {
            return sList;
        }
        // для левой а потом правой
        sList.addAll (sumNodeWays(node.LeftChild,  k));
        sList.addAll (sumNodeWays(node.RightChild, k));
        return sList;
    }
//=============================================================================
//    Задание 2   Задача 4
//    Метод проверки, симметрично ли дерево относительно своего корня.
 /    сложность O(N) и пространственная и временная
//=============================================================================
    
   boolean CompareTreeParts () 
    {
       if (this.Root == null)
           return false;

        return CheckSymetricNodes (this.Root.LeftChild, this.Root.RightChild);
   }

    boolean CheckSymetricNodes (BSTNode2<T> node1, BSTNode2<T> node2)
    {
        if (node1 == null && node2 == null)
            return true;

        if (node1 == null && node2 != null)
            return false;

        if (node2 == null && node1 != null)
            return false;
        
        return CheckSymetricNodes(node1.LeftChild, node2.LeftChild) &&
                CheckSymetricNodes(node1.RightChild, node2.RightChild);
    }

    
} 



