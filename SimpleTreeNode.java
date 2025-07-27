
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

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }
//--------------------------------------------------------------------------------------
    public void AddChild (SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    { // ваш код добавления нового дочернего узла существующему ParentNode
        if (ParentNode == null || NewChild == null) {
            throw new NullPointerException  ("\n Обращение к нулевому параметру \n");
        }
         if (ParentNode.Children == null)
             ParentNode.Children = new LinkedList<>();

         ParentNode.Children.add(NewChild);
         NewChild.Parent = ParentNode;
    }
//----------------------------------------------------------------------------------------
    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    { // ваш код удаления существующего узла NodeToDelete
        if (NodeToDelete == null)
            return;

        if (NodeToDelete == this.Root){
            this.Root.Children = null;
            this.Root = null;
            return;
        }

        if (NodeToDelete.Parent == null) {
            throw new NullPointerException  ("\n Обращение к нулевому параметру \n");
        }

        if  (NodeToDelete.Children == null) {
            SimpleTreeNode<T> nodeToDelParent = NodeToDelete.Parent;
            nodeToDelParent.Children.remove (NodeToDelete);

            if (nodeToDelParent.Children.isEmpty())
                 nodeToDelParent.Children = null;
            return;
        }

        for (int i = 0; i < NodeToDelete.Children.size(); i++){
            SimpleTreeNode<T> childNode = NodeToDelete.Children.get(i);
            childNode.Parent = NodeToDelete.Parent;
        }
        NodeToDelete.Parent.Children.addAll(NodeToDelete.Children);
        NodeToDelete.Parent.Children.remove(NodeToDelete);

        //List nodes = this.GetAllNodes();
          //  nodes.remove(NodeToDelete);
    }

//----------------------------------------------------------------------------------------
    public List<SimpleTreeNode<T>> GetAllNodes()
    {           // ваш код выдачи всех узлов дерева в определённом порядке
        List<SimpleTreeNode<T>> nodeList = new LinkedList<SimpleTreeNode<T>>();
        if (this.Root == null)
            return nodeList;

        nodeList = childSubList (this.Root);
        if (nodeList != null) {
            nodeList.add (nodeList.size(), this.Root);
        }
        return nodeList;
    }

//--
    public List<SimpleTreeNode<T>> childSubList (SimpleTreeNode<T> firstNode) {
        List<SimpleTreeNode<T>> resList  = new LinkedList<SimpleTreeNode<T>>();

        if (firstNode == null || firstNode.Children == null)
            return resList;

        for (int i = 0; i < firstNode.Children.size(); i++) {
            SimpleTreeNode<T> curnode = firstNode.Children.get(i);
            if(curnode.Children != null)
                resList.addAll (childSubList(curnode));

            resList.addLast(curnode);
        }
        return resList;
    }

//--------------------------------------------------------------------------------------
    public List<SimpleTreeNode<T>> FindNodesByValue (T val)
    {       // ваш код поиска узлов по значению
        List<SimpleTreeNode<T>>  resultValList = new LinkedList<SimpleTreeNode<T>>();

        if (this.Root == null)
            return resultValList;

        resultValList =  SubListOfValue (this.Root, val);
        if (this.Root.NodeValue == val)
            resultValList.add(resultValList.size(), this.Root);

         return  resultValList;
    }

 //------
    public List<SimpleTreeNode<T>>  SubListOfValue (SimpleTreeNode<T> node, T val ) {
        List<SimpleTreeNode<T>> resList  = new LinkedList<SimpleTreeNode<T>>();

        if (node == null || node.Children == null)
            return resList;

        for (int i = 0; i < node.Children.size(); i++) {
            SimpleTreeNode<T> curnode = node.Children.get(i);
            if(curnode.Children != null) {
                resList.addAll (SubListOfValue (curnode, val));
            }

            if(curnode.NodeValue == val) {
                resList.addLast (curnode);
            }
        }
        return resList;
    }

    //--------------------------------------------------------------------------------
    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {  // ваш код перемещения узла вместе с его поддеревом в качестве дочернего для узла NewParent
        if (NewParent == null || OriginalNode == null)
            return;

        if (NewParent.Children == null) {
            NewParent.Children = new LinkedList <SimpleTreeNode<T>> ();
        }
        NewParent.Children.add (OriginalNode);
        OriginalNode.Parent = NewParent;
    }
//---------------------------------------------------------------------

    public int Count()
    { // количество всех узлов в дереве
        List<SimpleTreeNode<T>>  nodeList = GetAllNodes();
        return nodeList.size();
    }
//--------------------------------------------------------------------------

    public int LeafCount()
    { // количество листьев в дереве
        if (this.Root.Children == null)
            return 0;

        int LeafCount = 0;
        List<SimpleTreeNode<T>>  nodeList = GetAllNodes();

        for (int i = 0; i < nodeList.size(); i++) {
            SimpleTreeNode<T> curNode = nodeList.get(i);
            if (curNode.Children == null)
                LeafCount++;
        }
        return LeafCount;
    }
//---------------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
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

        myTree.AddChild(myRoot, ch2);
        myTree.AddChild(myRoot, ch3);

        myTree.AddChild(ch2, ch4);
        myTree.AddChild(ch2, ch5);

        myTree.AddChild(ch4, ch8);
        myTree.AddChild(ch4, ch9);

        myTree.AddChild(ch3, ch6);
        myTree.AddChild(ch3, ch7);

        myTree.AddChild(ch5, ch10);
        myTree.AddChild(ch10, ch11);

        myTree.DeleteNode(ch11);
        myTree.DeleteNode(ch4);

        List<SimpleTreeNode<Integer>> allNodes = myTree.GetAllNodes();
        List<SimpleTreeNode<Integer>> allVal = myTree.FindNodesByValue(11);
        int nodeCount = myTree.Count();
        int leafCount = myTree.LeafCount();

        myTree.DeleteNode(myTree.Root);

        for (int i = 0; i < allVal.size(); i++) {
            SimpleTreeNode ch = allVal.get(i);

            System.out.printf("\n val =  %d", ch.NodeValue);
        }

        for (int i = 0; i < allNodes.size(); i++) {
                SimpleTreeNode ch = allNodes.get(i);

                System.out.printf("\n val =  %d", ch.NodeValue);
        }
    }
}
