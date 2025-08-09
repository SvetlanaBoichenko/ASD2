
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


//=========================

    public ArrayList<BSTNode> walk_nodes (BSTNode curnode)
    {
        ArrayList<BSTNode> outList = new ArrayList();
        if (curnode == null) //null
            return outList;

        outList.add(curnode);

        outList.addAll(walk_nodes(curnode.LeftChild));
        outList.addAll(walk_nodes(curnode.RightChild));
        return outList;
    }

    public ArrayList<BSTNode> DeepAllNodes (int walk_type)
    {
        ArrayList<BSTNode> outList = new ArrayList();
        if (walk_type != 1 && walk_type != 2 && walk_type != 0){
            return outList;
        }

        BSTNode firstnode = this.Root;
        if (firstnode == null)
            return outList;

        if (walk_type == 2) {       // pre
            outList.add(this.Root);
        }

        if (firstnode.LeftChild != null) {
            outList.addAll(walk_nodes(firstnode.LeftChild));
        }

        if (walk_type == 0) {       // in
            outList.add(this.Root);
        }

        if (firstnode.RightChild != null) {
            outList.addAll(walk_nodes(firstnode.RightChild));
        }

        if (walk_type == 1) {       // post
            outList.add(this.Root);
        }

        return outList;
    }



//================================================

    ArrayList<BSTNode> WideAllNodes()
    {
        ArrayList<BSTNode> nodeList = new ArrayList<>();
        Queue<BSTNode> q = new LinkedList<>();

        if (this.Root == null) {
            return nodeList;
        }

        q.add (this.Root);

        while(q.isEmpty() == false) {
            BSTNode curnode = q.poll();

            if (curnode.LeftChild != null)
                q.add(curnode.LeftChild);

            if (curnode.RightChild != null)
                q.add(curnode.RightChild);

            nodeList.add(curnode);
        }

        return nodeList;


    }

 
  
} 








