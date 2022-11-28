import java.awt.*;

public class AVL {
  private NodeAvl r;

  public AVL(NodeAvl r) {
    this.r = r;
  }

  public NodeAvl getRoot(){
    return r;
  }

  public NodeAvl searchMax(NodeAvl na){
    if (na.getRight() == null)
      return na;
    return searchMax(na.getRight());
  }

  public int getBalanced(NodeAvl n) {
    if (n == null)
      return 0;
    return NodeAvl.getHeight(n.getLeft()) - NodeAvl.getHeight(n.getRight());
  }

  public int max(int g, int d) {
    if (g < d)
      return d;
    return g;
  }

  public NodeAvl rightRotate(NodeAvl y) {
    NodeAvl x = y.getLeft();
    NodeAvl T2 = x.getRight();
    x.setRight(y);
    y.setLeft(T2);
    y.setHeight(max(NodeAvl.getHeight(y.getLeft()), NodeAvl.getHeight(y.getRight())) + 1);
    x.setHeight(max(NodeAvl.getHeight(x.getLeft()), NodeAvl.getHeight(x.getRight())) + 1);
    return x;
  }

  public NodeAvl leftRotate(NodeAvl x) {
    NodeAvl y = x.getRight();
    NodeAvl T2 = y.getLeft();
    y.setLeft(x);
    x.setRight(T2);
    x.setHeight(max(NodeAvl.getHeight(x.getLeft()), NodeAvl.getHeight(x.getRight())) + 1);
    y.setHeight(max(NodeAvl.getHeight(y.getLeft()), NodeAvl.getHeight(y.getRight())) + 1);
    return y;
  }


  public NodeAvl insertNode(Node node, NodeAvl na) {

    // Find the position and insert the node
    if (na == null)
      return new NodeAvl(node);
    if (na.getWeightN() < node.getWeight())
    	na.setRight(insertNode(node, na.getRight()));
    else if (na.getWeightN() > node.getWeight())
    	na.setLeft(insertNode(node, na.getLeft()));
    else
    	return na;

    // Update the balance factor of each node
    // And, balance the tree
    na.setHeight(1 + max(NodeAvl.getHeight(na.getLeft()), NodeAvl.getHeight(na.getRight())));
    int balanceFactor = getBalanced(na);
    if (balanceFactor > 1) {
      if (node.getWeight() < na.getLeft().getWeightN()) {
        return rightRotate(na);
      } else if (node.getWeight() > na.getLeft().getWeightN()) {
        na.setLeft(leftRotate(na.getLeft()));
        return rightRotate(na);
      }
    }
    if (balanceFactor < -1) {
      if (node.getWeight() > na.getRight().getWeightN()) {
        return leftRotate(na);
      } else if (node.getWeight() < na.getRight().getWeightN()) {
        na.setRight(rightRotate(na.getRight()));
        return leftRotate(na);
      }
    }
    return na;
  }

NodeAvl nodeWithMimumValue(NodeAvl node) {
    NodeAvl current = node;
    while (current.getLeft() != null)
      current = current.getLeft();
    return current;
}

 NodeAvl deleteNode(Node node, NodeAvl na) {

    // Find the node to be deleted and remove it
    if (na == null)
      return na;
    if (node.getWeight() < na.getWeightN())
      na.setLeft(deleteNode(node, na.getLeft()));
    else if (node.getWeight() > na.getWeightN())
      na.setRight(deleteNode(node, na.getRight()));
    else {
      if ((na.getLeft() == null) || (na.getRight() == null)) {
        NodeAvl temp = null;
        if (temp == na.getLeft())
          temp = na.getRight();
        else
          temp = na.getLeft();
        if (temp == null) {
          temp = na;
          na = null;
        } else
          na = temp;
      } else {
        NodeAvl temp = nodeWithMimumValue(na.getRight());
        na.setN(temp.getN());
        na.setRight(deleteNode(temp.getN(), na.getRight()));
      }
    }
    if (na == null)
      return na;

    // Update the balance factor of each node and balance the tree
    na.setHeight(max(NodeAvl.getHeight(na.getLeft()), NodeAvl.getHeight(na.getRight())) + 1);
    int balanceFactor = getBalanced(na);
    if (balanceFactor > 1) {
      if (getBalanced(na.getLeft()) >= 0) {
        return rightRotate(na);
      } else {
        na.setLeft(leftRotate(na.getLeft()));
        return rightRotate(na);
      }
    }
    if (balanceFactor < -1) {
      if (getBalanced(na.getRight()) <= 0) {
        return leftRotate(na);
      } else {
        na.setRight(rightRotate(na.getRight()));
        return leftRotate(na);
      }
    }
    return na;
  }

public void setRoot(NodeAvl insertNode) {
	r = insertNode;
}
}

  