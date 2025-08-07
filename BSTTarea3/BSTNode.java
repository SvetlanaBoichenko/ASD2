
import java.util.*;

public class BSTNode<T>
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

class BST<T>
{
    BSTNode<T> Root; //

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    //===========В ответ! =================================================================
//  DeepAllNodes(),
//  начиная с корня, которому задаётся один целый параметр,
//   принимающий значения 0 (in-order), 1 (post-order) и 2 (pre-order).
//    В зависимости от этого параметра метод DeepAllNodes()
    // pre in post walk of BST tree

      public ArrayList<BSTNode> walk_nodes (BSTNode curnode)
      {
          ArrayList<BSTNode> outList = new ArrayList();
          if (curnode == null) //null
              return outList;
          outList.add(curnode);
          System.out.print(" \n" + curnode.NodeValue);
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
            //    System.out.print(" \n" + firstnode.LeftChild.NodeValue);
                outList.addAll(walk_nodes(firstnode.LeftChild));
            }

            if (walk_type == 0) {       // in
                outList.add(this.Root);
            }

            if (firstnode.RightChild != null) {
           //     System.out.print(" \n" + firstnode.LeftChild.NodeValue);
                outList.addAll(walk_nodes(firstnode.RightChild));
            }

            if (walk_type == 1) {       // post
                outList.add(this.Root);
            }

                return outList;
           }

      //========Нерекурсивыне обходы пре и пост
        public void traverseWithStack() { //post
            Deque<BSTNode> stack = new ArrayDeque<>();
            stack.push(this.Root);
            while (stack.size() > 0) {
                BSTNode currentNode = stack.pop();
                if (currentNode.RightChild != null) { stack.push(currentNode.RightChild);}
                if (currentNode.LeftChild != null) { stack.push(currentNode.LeftChild);}
            }
        }
        public void traverseWithQueue() {
            Deque<BSTNode> queue = new ArrayDeque<>();
            queue.push(this.Root);
            while (queue.size() > 0) {
                BSTNode currentNode = queue.removeFirst();
                if (currentNode.LeftChild != null ) { queue.push(currentNode.LeftChild); }
                if (currentNode.RightChild != null) { queue.push(currentNode.RightChild); }
            }
        }

//--------------------------------------------
// Обход в глуину-ширину - каждой ветви отдельно Левая -Правая узел = потом
    List<BSTNode>  traverseLevelOrder()
    {
        List<BSTNode> nodeList = new LinkedList<>();
        List<BSTNode> q = new LinkedList<>();
        if (this.Root == null) {
            return nodeList;
        }
        q.add(this.Root);
        while (!q.isEmpty()) {
            BSTNode node = q.getLast();

            if (node.LeftChild != null) {
                nodeList.add(node.LeftChild);
                System.out.print(" \n" + node.LeftChild.NodeValue);
            }

            if (node.RightChild != null) {
                nodeList.add(node.RightChild);
                System.out.print(" \n" + node.RightChild.NodeValue);
            }
        }
        return nodeList;
    }

    //===================================================================
    // Обход по уровням - в ответ!
    ArrayList<BSTNode> WideAllNodes() {

        ArrayList<BSTNode> nodeList = new ArrayList<>();
        Queue<BSTNode> q = new LinkedList<>();

        if (this.Root == null) {
            return nodeList;
        }

        q.add (this.Root);

        while(q.isEmpty() == false) {
            BSTNode curnode = q.poll();

            if(curnode != null ) {
               // System.out.print(" \n" + curnode.NodeValue);
            }

            if (curnode.LeftChild != null)
                q.add(curnode.LeftChild);

            if (curnode.RightChild != null)
                q.add(curnode.RightChild);

            nodeList.add(curnode);
        }
       // nodeList.add(0, this.Root);
        return nodeList;
    }

}




