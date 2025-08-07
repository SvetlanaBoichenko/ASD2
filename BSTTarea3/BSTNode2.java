import java.util.ArrayList;
import java.util.List;

public class BSTNode2<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode2<T> Parent; // родитель или null для корня
    public BSTNode2<T> LeftChild; // левый потомок
    public BSTNode2<T> RightChild; // правый потомок

    public BSTNode2(int key, T val, BSTNode2<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нет узлов
    public BSTNode2<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() { Node = null; }
}

class BST<T>
{
    BSTNode2<T> Root; // корень дерева, или null

    public BST(BSTNode2<T> node)
    {
        Root = node;
    }

    
//=======Метод который проверяет идентичность двух бинарных деревьев================
 
    public boolean CompareBinTrees (BST binTree2)
    {
        return CompareAllNodes ( binTree2.Root, this.Root);
    }
    
    //--------------------------
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


//=====метод, который нахождит все пути от корня к листьям, заданной длины.

    List<Integer> waysofLen (BSTNode2 node, int len, int n) {
        List<Integer> sList = new ArrayList<>();

        if (node == null) {
            return sList;
        }

        if (n >= len ||  isLeaf(node)) {
            sList.add(n);
            return sList;
        }

        System.out.printf(" n = %d , key =  % d \n",  n, node.NodeKey);

        // для левого а потом правого
        int m = n+1;
        sList.addAll(waysofLen (node.LeftChild, len, m));
        sList.addAll(waysofLen (node.RightChild, len, m));
        return sList;
    } 

//------------------------------------------------
    public boolean isLeaf (BSTNode2 node) {
        if (node != null && node.RightChild == null && node.RightChild == null)
            return true;

        return false;
    }


    //=====метод, который находит все пути от корня к листьям, с мах суммой значений узлов
    
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
        // для левого а потом правого
        sList.addAll (sumNodeWays(node.LeftChild,  k));
        sList.addAll (sumNodeWays(node.RightChild, k));
        return sList;
    }


}
