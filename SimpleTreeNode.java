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
    {
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
    {
        if (NodeToDelete == null)
            return;

        if (NodeToDelete == this.Root){
            this.Root.Children = null;
            this.Root = null;
            return;
        }

        NodeToDelete.Parent.Children.remove (NodeToDelete);

        if (NodeToDelete.Parent.Children.isEmpty())
            NodeToDelete.Parent.Children = null;

    }

//----------------------------------------------------------------------------------------

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
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
    {
        List<SimpleTreeNode<T>>  resultValList = new LinkedList<SimpleTreeNode<T>>();

        if (this.Root == null)
            return resultValList;

        resultValList =  SubListOfValue (this.Root, val);
        if (this.Root.NodeValue == val)
            resultValList.add(resultValList.size(), this.Root);

        return  resultValList;
    }

 //-------------------------------------
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
    {
        if (NewParent == null || OriginalNode == null)
            return;

        SimpleTreeNode<T> oldParent = OriginalNode.Parent;
        oldParent.Children.remove(OriginalNode);

        if (NewParent.Children == null) {
            NewParent.Children = new LinkedList <SimpleTreeNode<T>> ();
        }
        NewParent.Children.add (OriginalNode);
        OriginalNode.Parent = NewParent;
    }

//---------------------------------------------------------------------
    public int Count()
    {
        List<SimpleTreeNode<T>>  nodeList = GetAllNodes();
        return nodeList.size();
    }
//--------------------------------------------------------------------------

    public int LeafCount()
    {
        int LeafCount = 0;
        List<SimpleTreeNode<T>>  nodeList = GetAllNodes();

        for (int i = 0; i < nodeList.size(); i++) {
            SimpleTreeNode<T> curNode = nodeList.get(i);

            if (curNode.Children == null)
                LeafCount++;
        }
        return LeafCount;
    }

  //-------------------------------------------------------------
    
    public List<SimpleTreeNode<T>> LeafList()
    {
        List<SimpleTreeNode<T>>  nodeList = GetAllNodes();
        List<SimpleTreeNode<T>> leafLst = new ArrayList();
        int len = nodeList.size();

        for (int i = 0; i < len; i++) {
            SimpleTreeNode<T> curNode = nodeList.get(i);

            if (curNode.Children == null)
                leafLst.add(curNode);
        }
        return leafLst;
    }

  

    public ArrayList<T> EvenTrees()
    {
        ArrayList <T> reslist = new ArrayList();
        List<SimpleTreeNode<T>>  leafList = LeafList();

        ArrayList<SimpleTreeNode<T>> v_list = new ArrayList<>();

        int len = leafList.size();
        for (int i = 0; i < len; i++)  {
            SimpleTreeNode<T> nod = leafList.get(i);
            if (i+1 >= len || (i+1 < len && nod.Parent != leafList.get(i+1).Parent)) {
                reslist.addAll (this.PassParentChild (nod.Parent, v_list));
                }
            }
        
        return reslist;
    }

 
    
    public ArrayList <T>  PassParentChild ( SimpleTreeNode<T> parent,  ArrayList<SimpleTreeNode<T>> v_list)
    {
        int count = 0;
        ArrayList <T> reslist = new ArrayList();

        while (parent != this.Root && parent != null) {
             List subList = this.realchildSubList (parent, v_list);

             count = 1 + subList.size();  //count of all childs and parent

             if (count % 2 == 0) {
                 reslist.add (parent.Parent.NodeValue);
                 reslist.add (parent.NodeValue);
                 v_list.addAll (subList);
                 v_list.add (parent);
             }

            parent = parent.Parent;
         }
        return reslist;
    }


    public List<SimpleTreeNode<T>> realchildSubList (SimpleTreeNode<T> firstNode, ArrayList<SimpleTreeNode<T>> v_list)
    {
        List<SimpleTreeNode<T>> resList  = new LinkedList<SimpleTreeNode<T>>();

        resList = this.childSubList(firstNode);
        List <SimpleTreeNode<T>> realResList = new ArrayList<>();
        realResList.addAll(resList);

        if (realResList != null) { 
            for (int j = 0; j < v_list.size(); j++) {
                realResList.remove (v_list.get(j));
            }
        }
        return realResList; // without v_list - ceparated childs list
    }
    


} 




