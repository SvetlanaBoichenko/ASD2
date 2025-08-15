//=======================================================================
// Заданиe 3 задачи 1 и 2
//========================================================================

class BSTNode<T>
{
    public int NodeKey; //
    public T NodeValue; //
    public BSTNode<T> Parent; //
    public BSTNode<T> LeftChild; //
    public BSTNode<T> RightChild; //
    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

//------------------------------------
class BSTFind<T>
{
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() { Node = null; }
}
//------------------------------------------
public class BST<T>
{
    BSTNode<T> Root; //

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

  

//=======================================================================
//  Задание 3 задача 1
//  Метод который инвертирует дерево
//  сложность O(N) и пространственная и временная 
//========================================================================

    public void InvertBinTree (BSTNode<T> nextNode) 
  {
        if (nextNode == null)
            return;
        BSTNode<T> tmpnode;

        tmpnode = nextNode.LeftChild;
        nextNode.LeftChild = nextNode.RightChild;
        nextNode.RightChild = tmpnode;

        InvertBinTree(nextNode.LeftChild);
        InvertBinTree(nextNode.RightChild);
        return;
    }

  

//=======================================================================
//  Задание 3 задача 2
//  Метод который находит уровень с максимальной суммой значений узлов
//  сложность O(N) и пространственная и временная 
// как оптимизировать для большого дерева, пока непонятно
//========================================================================

    public int maxLevelSum () 
  {
        if (this.Root == null) {
            return 0;
        }

        int S = 0, Smax = 0;
        Queue<BSTNode> q = new LinkedList<>();

        q.add(this.Root);

        while (q.isEmpty() == false) {

            int cur_level_size = q.size();

            for (int j = 0; j < cur_level_size; j++) {
                BSTNode curnode = q.poll();

                if (curnode.LeftChild != null) {
                    S = S + curnode.LeftChild.NodeKey;
                    q.add(curnode.LeftChild);
                }

                if (curnode.RightChild != null) {
                    S = S + curnode.RightChild.NodeKey;;
                    q.add(curnode.RightChild);
                }
            }
            System.out.print(" S =  " +  S);
               if (S > Smax)
                   Smax = S;
               S = 0;
           }

        System.out.print("\n Smax =  " +  + Smax);
        return Smax;
    }
