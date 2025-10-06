//=======================================================================================
// задание 9 
// Задача2.*   метод, который сбалансирует чётное двоичное дерево.
// строка 39
//
//3.* метод, который для любого заданного подузла текущего дерева
//    определит общее количество чётных поддеревьев.
// строка 
//=========================================================================================


import java.util.*;

    public class SimpleTreeNode<T>
    {
        public T NodeValue; // значение в узле
        public SimpleTreeNode<T> Parent; // родитель или null для корня
        public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

        public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
        {
            NodeValue = val;
            Parent = parent;
            Children = null;
        }
    }

    class SimpleTree<T> {
        public SimpleTreeNode<T> Root; // корень, может быть null

        public SimpleTree(SimpleTreeNode<T> root) {
            Root = root;
        }
//================================================================================
// не обязательно число детей у узлов 2. Поэтому обход дерева обычный.
// - GetAllNodes(), как любого дерева. Создание массива значений узлов и
// - его сортировка
// Затем созд дерева из массива: левая часть -рекурсивно для левого поддерва, правая- для правого
// функция makeBEvenTree         строка 58
// сложность O(N)
//==================================================================================

        public  SimpleTreeNode<T>  balanceChetTree() 
        {
            List<SimpleTreeNode<T>> chNodeList = this.GetAllNodes();
            int[] valArray = new int [chNodeList.size()];

            for (int i = 0; i < chNodeList.size(); i++) {
                valArray[i] = (int) chNodeList.get(i).NodeValue;
            }

            Arrays.sort(valArray);

            return  makeBEvenTree(0, valArray.length, valArray, null);
        }
//--------

        public SimpleTreeNode makeBEvenTree(int left, int right, int[] A, SimpleTreeNode parent)
        {
            int mid = (int)((left+right) / 2);
            if (mid < 0 || mid >= A.length)
                return parent;

            int val = A [mid];

            SimpleTreeNode<T> new_nod = new SimpleTreeNode (val, parent);
            if (new_nod.Children == null){
                new_nod.Children = new ArrayList<>();
            }

            if (right > left) {
                SimpleTreeNode<T> ch1= makeBEvenTree (left, mid - 1, A, new_nod);
                 new_nod.Children.add(ch1);

                SimpleTreeNode<T> ch2 =makeBEvenTree (mid + 1, right, A, new_nod);
                new_nod.Children.add(ch2);
            }
            return new_nod;
        }


//--------------------------------------------------------------------
        public List<SimpleTreeNode<T>> childSubList(SimpleTreeNode<T> firstNode) 
        {
            List<SimpleTreeNode<T>> resList = new LinkedList<SimpleTreeNode<T>>();

            if (firstNode == null || firstNode.Children == null)
                return resList;

            for (int i = 0; i < firstNode.Children.size(); i++) {
                SimpleTreeNode<T> curnode = firstNode.Children.get(i);
                if (curnode.Children != null)
                    resList.addAll (childSubList(curnode));

                resList.addLast(curnode);
            }
            return resList;
        }        
        
//=================================================================
//   Использовала функцию получения списка подузлов    
//   Вероятно не самый оптимальный метод childSubList(curnode), строка 80
//   тк этот метод создает списки      
//===============================================================
        
        public int countEvenNodes(SimpleTreeNode<T> firstNode) 
        {
            List<SimpleTreeNode<T>> resList;
            
            if (firstNode == null || firstNode.Children == null)
                return 1;
            int chetCount  = 0;

            for (int i = 0; i < firstNode.Children.size(); i++) {
                SimpleTreeNode<T> curnode = firstNode.Children.get(i);

                int curNodeCount  = 0;
                if (curnode.Children != null) {
                    resList = childSubList(curnode);
                    curNodeCount = resList.size();
                }

                if((curNodeCount +1) % 2 == 0)
                    chetCount ++;
            }
            return chetCount;
        }
//----------------------------------------------------------------------------------------

        public List<SimpleTreeNode<T>> GetAllNodes() 
        {
            List<SimpleTreeNode<T>> nodeList = new LinkedList<SimpleTreeNode<T>>();
            if (this.Root == null)
                return nodeList;

            nodeList = childSubList(this.Root);
            if (nodeList != null) {
                nodeList.add(nodeList.size(), this.Root);
            }
            return nodeList;
        }



//-------------------------------------------------------------------------------------------
public void AddChild (SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
{
    if (ParentNode == null || NewChild == null) {
        throw new NullPointerException  ("\n Обращение к нулевому параметру \n");
    }
    if (ParentNode.Children == null)
        ParentNode.Children = new LinkedList<>();

    ParentNode.Children.add(NewChild);
    NewChild.Parent = ParentNode;
}

    
}
//---------------------------------------------------------------------------
