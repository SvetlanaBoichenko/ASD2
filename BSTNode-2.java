//  Заданиe 1
//- номер задача 1,
//- Присвоение узлу его уровня
//- сложность O(N) и пространственная и временная

//-------------------------------------------------------
  
import java.util.*;

public class SimpleTreeNode2<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode2<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode2<T>> Children; // список дочерних узлов или null
    public int nodeLevel;
    public SimpleTreeNode2(T val, SimpleTreeNode2<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
        nodeLevel = 1;
    }
}

class SimpleTree<T> 
{
  public SimpleTreeNode2<T> Root; // корень, может быть null

  public SimpleTree(SimpleTreeNode2<T> root) 
  {
        Root = root;
  }

 
  public void SetNodesLevels() 
  {
       if (this.Root == null)
          return;
      
       this.Root.nodeLevel = 1;
         NodeLevels(this.Root, 2);
    }

 
  public void NodeLevels (SimpleTreeNode2<T> nextNode, int level ) 
  {
      if (nextNode == null )
           return;

      if ( nextNode.Children == null || nextNode.Children.isEmpty()) {
            nextNode.nodeLevel = level;
            return;
      }

      for (int i = 0; i < nextNode.Children.size(); i++) {
            SimpleTreeNode2<T> curnode = nextNode.Children.get(i);
            curnode.nodeLevel = level;

            if (curnode.Children != null) {
                NodeLevels(curnode, level+1);
            }
        }
    }


}



  


