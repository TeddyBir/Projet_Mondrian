import java.awt.*;
import java.util.Random;
import java.util.Vector;


public class Tree {
  private int nbFeuille;
  private double minDimensionCoupe;
  private double memeCouleurProb;
  private double proportionCoupe;
  private double largeurLigne;
  private Vector<Color> TabC;
  private Node r;
  private Random rand;

  public Tree(Node _r, int _nbFeuille, double _proportionCoupe, double _minDC, double _memeCouleurProb, double _largeurLigne, Vector<Color> _TabC, Random _rand) {
    nbFeuille = _nbFeuille;
    minDimensionCoupe = _minDC;
    TabC = _TabC;
    r = _r;
    proportionCoupe = _proportionCoupe;
    largeurLigne = _largeurLigne;
    rand = _rand;
    this.memeCouleurProb = _memeCouleurProb;
  }

  /*
   * Choose a color by evaluating the odd of getMemeCouleurProb
   * 
   */
  public Color chooseColor(Node r) {
    if (rand.nextDouble() <= getMemeCouleurProb()) {
      return r.getCol();
    } else {
      int i = (int) (rand.nextDouble() * (TabC.size() - 1));
      return TabC.get(i);
    }
  }

  public boolean isEmpty(Node n) {
    return n == null;
  }

  /*
   * Comparison of right and left weight leaf to pick the heaviest for division
   */

  public Node chooseLeaf(Node r) {
    if (r.getLeft() == null && r.getRight() == null) {
      return r;
    } else {
      Node ll = chooseLeaf(r.getLeft());
      Node rr = chooseLeaf(r.getRight());
      if (ll.getWeight() < rr.getWeight()) {
        return rr;
      } else {
        return ll;
      }
    }
  }

  public double chooseDivision(Node r) {
    double prob = rand.nextDouble();
    double div = 0;
    if (r.getCol() == Color.WHITE && r.getLeft() == null && r.getRight() == null) {
      // If Y is out of the autorized cut zone then re-set Y
      div = (rand.nextDouble() * (r.getH()));
      r.setChoosenDiv_X(false);
      while ((div < (r.getH() * proportionCoupe)) || (div > (r.getH() * (1 - proportionCoupe)))) {
        div = (rand.nextDouble() * r.getH());
      }

    } else {
      // Choose X or Y

      if (prob <= r.getW() / (r.getH() + r.getW())) {
        div = (rand.nextDouble() * r.getW());
        r.setChoosenDiv_X(true);
        while (div < r.getW() * proportionCoupe || div > r.getW() * (1 - proportionCoupe)) {
          
          div = (rand.nextDouble() * r.getW());
        }

      } else {
        r.setChoosenDiv_X(false);
        while (div < r.getH() * proportionCoupe || div > r.getH() * (1 - proportionCoupe)) {
          div = (rand.nextDouble() * (r.getH()));
        }
      }
    }
    return div;
  }

  /*
   * int i = 0;
   * public int compt(Node r) {
   * if (isEmpty(r)) {
   * return 0;
   * }
   * i = compt(r.getLeft());
   * return i++;
   * }
   */

  // Count the number of leaf
  public int nbOfLeaves(Node r) {
    if (r.getRight() == null && r.getLeft() == null) {
      return 1;
    } else {
      return nbOfLeaves(r.getLeft()) + nbOfLeaves(r.getRight());
    }
  }

  public void generateRandomTree(Node r) {

    // numberOfLeaves reach the treshold or the dimension of the region is not big
    // enough
    Node l = chooseLeaf(r);
    System.out.println(" Parents : x :" + l.getX() + " ,y :" + l.getY());
    if (nbOfLeaves(r) >= nbFeuille || minDimensionCoupe > l.getH() * l.getW()) {
      return;

    } else {

      double div = chooseDivision(l);
    
      if (l.isChoosenDiv_X()) {

        l.setLeft(new Node(div, l.getH(), l.getX(), l.getY(), chooseColor(l) ));
        
        /*System.out.println( " LeftX ( x: " + (int)l.getLeft().getX() + ", y : "+(int)l.getLeft().getY()+ ", W/H : " + l.getLeft().getW() + "/" + l.getLeft().getH() + " Weight : " + l.getLeft().getWeight()) ;*/
        
        l.setRight(
            new Node(l.getW()- div, l.getH(), l.getX()+div, l.getY(), chooseColor(l)) );
        
       /* System.out.println( " RightX ( x: " + (int)l.getRight().getX() + ", y : "+(int)l.getRight().getY() + ", W/H : " + l.getRight().getW() + "/" + l.getRight().getH()+ " Weight : " + l.getRight().getWeight()+"\n");*/
        
      } else {
        
        l.setLeft(new Node(l.getW(), div, l.getX(), l.getY(), chooseColor(l)));
        
       /* System.out.println( " LeftY ( x: " + (int)l.getLeft().getX() + ", y : "+(int)l.getLeft().getY() + ", W/H : " + l.getLeft().getW() + "/" + l.getLeft().getH() + " Weight : " + l.getLeft().getWeight());*/
        
        l.setRight( new Node(l.getW(), l.getH() - div, l.getX(), l.getY()+div, chooseColor(l)) );
        
      /*  System.out.println( " RightY ( x: " + (int)l.getRight().getX() + ", y : "+(int)l.getRight().getY() + ", W/H : " + l.getRight().getW() + "/" + l.getRight().getH()+ " Weight : " + l.getRight().getWeight()+"\n");*/
      }
      
      if (nbOfLeaves(r) > nbFeuille) {
        l.setRight(null);

      } else {
        generateRandomTree(r);
      }

    }
    
  }

  
  public int getNbFeuille() {
    return nbFeuille;
  }

  public double getMinDimensionCoupe() {
    return minDimensionCoupe;
  }

  public double getMemeCouleurProb() {
    return memeCouleurProb;
  }

  public double getProportionCoupe() {
    return proportionCoupe;
  }

  public Vector<Color> getTabC() {
    return TabC;
  }

  public Node getRoot() {
    
    return r;
  }
  public double getlargeurLigne(){
    return largeurLigne;
  }

  
}
