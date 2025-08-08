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
    //=====================================================================
    public BSTFind<T> FindNodeByKey(int key)
    {
        BSTNode2 curnode = this.Root;
        BSTFind<T> bstResult = new BSTFind<T>();

        if (curnode == null) {
            return bstResult;
        }

        while (curnode != null) {
            if (curnode.NodeKey == key) {
                bstResult.Node = curnode;
                bstResult.NodeHasKey = true;
                return bstResult;
            }

            if (key < curnode.NodeKey)
                bstResult.ToLeft = true;
            else
                bstResult.ToLeft = false;

            bstResult.Node = curnode;

            if (bstResult.ToLeft == true)
                curnode = curnode.LeftChild;
            else
                curnode = curnode.RightChild;

            bstResult.NodeHasKey = false;
        }
        return bstResult;
    }


    public boolean AddKeyValue(int key, T val) {// добавляем ключ-значение в дерево
        BSTFind<T> bstObject = FindNodeByKey(key);

        if (bstObject.NodeHasKey == true) {
            return false;
        }
        if (bstObject.Node == null) {
            this.Root = new BSTNode2(key, val, null);
            return true;
        }
        BSTNode2<T> parent = bstObject.Node;
        BSTNode2<T> nodeToAdd = new BSTNode2<T>(key, val, parent);
        if (bstObject.ToLeft == true)
            parent.LeftChild = nodeToAdd;
        else
            parent.RightChild = nodeToAdd;

        return true; // если ключ уже есть
    }

    //=====================================================================
    //
    // Добавьте метод, который находит все пути от корня к листьям,
    // чтобы сумма значений узлов на этом пути была максимальной.
   /// обход с суммой узлов..на 1 цикле
    // Добавьте метод проверки, симметрично ли дерево относительно своего корня.
    // 2 списка???
 // ----------------------------------------------------
    public int Count()
    {
        return CountNodes (this.Root, 1);
    }
//-----------------------------------------------------
    int CountNodes (BSTNode2<T> node, int n) {
        if (node == null)
            return 0;

        BSTNode2<T> tmpnode1 = null;
        BSTNode2<T> tmpnode2 = null;

        if (node.LeftChild != null) {
            tmpnode1 = node.LeftChild;
            System.out.printf(" % d \n", tmpnode1.NodeValue);
            n = CountNodes(tmpnode1, n+1);
        }
        if (node.RightChild != null) {
            tmpnode2 = node.RightChild;
            System.out.printf(" % d \n", tmpnode2.NodeValue);
            n = CountNodes(tmpnode2, n+1);
        }

        return n;
    }

//--------------------------------------------------------------------------
    int CountNodes2 (BSTNode2<T> rNode, BSTNode2<T> lNode, int n1, int n2)
    {
        if (rNode == null && lNode == null)
            return n1+n2;
        int m1 = 0;
        int m2 = 0;

        if (lNode != null) {
            System.out.printf(" \n lNode =  %d", lNode.NodeKey);
            m1 = n1+1;
        }

        if (rNode != null) {
            System.out.printf(" \n rNode. = %d", rNode.NodeKey);
            m2 =  n2+1;
        }

        return CountNodes2(lNode.LeftChild, lNode.RightChild, m1, 0)
                + CountNodes2(rNode.LeftChild, rNode.RightChild, 0,m2);
    }
    //========== в ответ=============
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

    // Добавьте метод, который нахождит все пути от корня к листьям, длина которых равна заданной величине.
    // 2. обход с подсчетом длин и записями их в список или при сравнении  -
    // накопление счетчика
    //====
      int CountWayLens ( int wayLen)
      {
          List<BSTNode2<T>> leafArray = GetLeafs (this.Root);
          int nodeCounter = 0;

          for (int i = 0; i< leafArray.size(); i++) {
              BSTNode2 leaf = (BSTNode2)leafArray.get(i);

              for (int j = 0; j < wayLen; j++) {
                  if (leaf == null)
                      break;
                  leaf = leaf.Parent;
              }
              if (leaf == this.Root)
                 nodeCounter++;
          }
          return nodeCounter;
      }

//--------Подсчет листьев - вспомогательная----------------------------------------------------
    List<BSTNode2<T>> GetLeafs (BSTNode2 node)
    {
        List<BSTNode2<T>> leafList = new ArrayList<>();

        if (node == null)
            return leafList;

        if (this.isLeaf(node)) {
            leafList.add(node);
        }

        leafList.addAll(GetLeafs(node.LeftChild));
        leafList.addAll(GetLeafs(node.RightChild));
        return leafList;
    }
    //------------------------------------------------
    public boolean isLeaf (BSTNode2 node) {
        if (node != null && node.RightChild == null && node.RightChild == null)
            return true;

        return false;
    }
    // Добавьте метод, который нахождит все пути от корня к листьям,
    // длина которых равна заданной величине.
    // 2. обход с подсчетом длин и записями их в список или при сравнении  -
    // накопление счетчика
    //----------------------------------------------------------
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
//===============В ответ====================
//   Добавьте метод, который находит все пути от корня к листьям,
//   чтобы сумма значений узлов на этом пути была максимальной.
    List<Integer> sumNodeWays (BSTNode2 node, int k) {
        List<Integer> sList = new ArrayList<>();

        if (node == null) {
            return sList;
        }

        sList.add(k);
        k = k + node.NodeKey;
        System.out.printf(" k = %d \n",  k);

        // если конечный узел найден, то все
        if (isLeaf (node)) {
            return sList;
        }
        // для левого а потом правого
        sList.addAll(sumNodeWays(node.LeftChild,  k));
        sList.addAll(sumNodeWays(node.RightChild,  k));
        return sList;
    }

    // --- проба - Левая - Левая - левая ------------
    List<BSTNode2<T>> GetRightLeafs (BSTNode2 node)
    {
        List<BSTNode2<T>> rLeafList = new ArrayList<>();

        if (node == null)
            return rLeafList;

        if (isLeaf (node) && node == node.Parent.RightChild) {
            rLeafList.add(node);
        }

        rLeafList.addAll(GetLeafs(node.LeftChild));
        rLeafList.addAll(GetLeafs(node.RightChild));
        return rLeafList;
    }

    //----------------------------------------------------
    public static void main (String[] args) {
        List<Integer> stepList2 = new ArrayList<>();


        System.out.printf("Hello and welcome!");
    }

}
