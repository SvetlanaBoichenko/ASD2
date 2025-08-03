public class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent)
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
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() { Node = null; }
}

 class BST<T>
{
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node)
    {
        Root = node;
    }
//=====================================================================
    public BSTFind<T> FindNodeByKey(int key)
    {
        BSTNode curnode = this.Root;
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
   //=====================================================================

    public boolean AddKeyValue(int key, T val) {// добавляем ключ-значение в дерево
        BSTFind<T> bstObject = FindNodeByKey(key);

        if (bstObject.NodeHasKey == true) {
            return false;
        }
        if (bstObject.Node == null) {
            this.Root = new BSTNode(key, val, null);
            return true;
        }
        BSTNode<T> parent = bstObject.Node;
        BSTNode<T> nodeToAdd = new BSTNode<T>(key, val, parent);
        if (bstObject.ToLeft == true)
            parent.LeftChild = nodeToAdd;
        else
            parent.RightChild = nodeToAdd;

        return true; // если ключ уже есть
    }
//================================================================

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    { // ищем максимальный/минимальный ключ в поддереве
        BSTNode<T> prevNode = FromNode;

       while (FromNode != null) {
           prevNode = FromNode;
           if (FindMax)
               FromNode = FromNode.RightChild;
           else
               FromNode = FromNode.LeftChild;
       }
       return prevNode;
    }
    //======================================================================
     final int ONE_LEFT_CHILD = 1;
     final int ONE_RIGHT_CHILD = 2;
     final int LEFT_RIGHT_CHILD = 3;
     final int IM_NULL_NODE = -1;
     final int NO_CHILD = 0;

    final int IM_LEFT_CHILD = 1;
    final int IM_RIGHT_CHILD = 2;
    final int IM_ROOT = 3;


    public int NodeChildInfo (BSTNode<T> node) {
        if (node == null) 
            return IM_NULL_NODE;
        
        if (node.LeftChild != null && node.RightChild != null)
            return LEFT_RIGHT_CHILD;

        if (node.LeftChild == null && node.RightChild == null)
            return NO_CHILD;
            
       if (node.LeftChild != node)
           return  ONE_LEFT_CHILD;

       return  ONE_RIGHT_CHILD;
    }

    public int whoIM (BSTNode<T> node) {
        if (node.Parent == null)
            return IM_ROOT;

        if (node == null)
            return IM_NULL_NODE;

        if (node.Parent.LeftChild == node)
            return IM_LEFT_CHILD;

        if (node.Parent.RightChild == node)
            return IM_RIGHT_CHILD;

        if (node.LeftChild == null && node.RightChild == null)
            return NO_CHILD;

        if (node.LeftChild != node)
            return  ONE_LEFT_CHILD;

        return  ONE_RIGHT_CHILD;
    }



    public  boolean DeleteOneLineNode( BSTNode<T> del_node) {
        int childs_type = NodeChildInfo (del_node);

        if (childs_type == LEFT_RIGHT_CHILD) {
            return false;
        }

        if (childs_type == NO_CHILD  && whoIM (del_node)== IM_LEFT_CHILD) {
            del_node.Parent.LeftChild = null;
            return true;
        }
        if (childs_type == NO_CHILD  && whoIM (del_node)== IM_RIGHT_CHILD) {
            del_node.Parent.RightChild = null;
            return true;
        }

        BSTNode<T> del_node_child;

        if (childs_type == ONE_LEFT_CHILD)
            del_node_child = del_node.LeftChild;
         else
            del_node_child = del_node.RightChild;

        if (del_node.Parent == null) {
            this.Root = del_node_child;
            del_node = null;
            return  true;
        }
        // а я кто?
        if (whoIM (del_node)== IM_LEFT_CHILD) {
            del_node.Parent.LeftChild= null;
            del_node.Parent.LeftChild = del_node_child;

            return true;
        }

        if (whoIM(del_node)== IM_RIGHT_CHILD) {
            del_node.Parent.RightChild = null;
            del_node.Parent.RightChild = del_node_child;
            return true;
        }

        del_node_child.Parent = del_node.Parent; // исключение из цепочки
        return true;
    }

    public boolean DeleteNodeByKey(int key)
    {// удаляем узел по ключу
        BSTFind<T> findNode = this.FindNodeByKey(key);
        if (findNode.NodeHasKey == false) {
            return false;
        }


        BSTNode<T> del_node = findNode.Node;
        if (this.DeleteOneLineNode (del_node) == false) {
            BSTNode<T>  minLeft = this.FinMinMax(del_node.RightChild, false);
            del_node.NodeKey = minLeft.NodeKey;
            del_node.NodeValue = minLeft.NodeValue;

            this.DeleteOneLineNode (minLeft);
            return true;
        }
        return true; //
    }

    public int Count()
    {
       return CountNodes (this.Root, 1);

    }

    int CountNodes (BSTNode<T> node, int n) {
        if (node == null)
            return n;

        BSTNode<T> tmpnode1 = null;
        BSTNode<T> tmpnode2 = null;

        if (node.LeftChild != null) {
            tmpnode1 = node.LeftChild;
          n = CountNodes(tmpnode1, n+1);
        }
        if (node.RightChild != null) {
            tmpnode2 = node.RightChild;

          n = CountNodes(tmpnode2, n+1);
        }

        return n;
    }


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

    }
}