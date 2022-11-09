import java.awt.*;
import java.util.Vector;

public class Tree {
	private int nbFeuille;
	private double minDimensionCoupe;
	private double memeCouleurProb;
	private double proportionCoupe;
	//private Color[] tabC = {Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.WHITE};
	Vector<Color> TabC;
	Node r;
	
	public Tree(Node _r,int _nbFeuille, double _minDC,  double _proportionCoupe, Vector<Color> _TabC) {
		nbFeuille = _nbFeuille;
		minDimensionCoupe = _minDC;
		TabC = _TabC;
		r = _r;
		proportionCoupe = _proportionCoupe;
	}
	
	
	/*Choose a color by evaluating the odd of getMemeCouleurProb
	 * 
	 */
	public Color chooseColor(Node r) {
		if(Math.random() <= r.getMemeCouleurProb()) {
			return r.getCol();
		}else {
			int i = (int) (Math.random() * (TabC.size()-1));
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
		if (isEmpty(r)) {
			return null;
		}else {
			Node right = chooseLeaf(r.getRight());
			Node left = chooseLeaf(r.getLeft());
			
			//If both of the son node are null then return the current node or return the heaviest
			if( right == null && left == null) {
				return r;
			}else if(left.getWeight() < right.getWeight()) {
				return right;
			}else {
				return left;
			}
		}
	}
	
	public double chooseDivision(Node r)
	{
		double prob = Math.random();
		double div = 0;
		if(r.getCol() == Color.WHITE && r.getLeft() == null && r.getRight() == null) {
			//If Y is out of the autorized cut zone then re-set Y
			div = r.getH()/(Math.random() * (r.getH() - 1));
			while((div < r.getH() * proportionCoupe) || (div > r.getH() *(1-  proportionCoupe))) {
				div = r.getH()/(Math.random() * (r.getH() - 1));
				r.setChoosenDiv_X(false);
				//System.out.print("oui");
			}
			
		}else {
			//Choose X or Y
			div = r.getH()/(Math.random() * (r.getH() - 1));
			if(prob <= r.getW()/(r.getH()+r.getW())) {

				while(div < r.getW() * proportionCoupe || div > r.getW() *(1-  proportionCoupe)) {
					//System.out.print("oui");
					div = r.getX()/(Math.random() * (r.getW() - 1));
					r.setChoosenDiv_X(true);
				}
			
			}else {
				while(div < r.getH() * proportionCoupe || div > r.getH() *(1-  proportionCoupe)) {
					div = r.getH()/(Math.random() * (r.getH() - 1));
					r.setChoosenDiv_X(false);
				}
				
				
			}
		}
		return div;
	}
	


	  int nbFeuilleCourant = 0;
	  public void generateRandomTree(Node r) {
		

		// numberOfLeaves reach the treshold or the dimension of the region is not big enough
	    if(nbFeuilleCourant == nbFeuille|| minDimensionCoupe > r.getH()*r.getW()) {
			return;
	      
	    } else {
		
	      Node l = chooseLeaf(r); 
	      double div = chooseDivision(l);
	      // TODO: Probleme de boucle infini qui bloque 1 fois sur 2 le code
	      if (!l.isChoosenDiv_X()) {
			
	    	  l.setLeft(new Node(l.getW(), l.getY() + div, l.getX(), div, l.getMemeCouleurProb(), chooseColor(l)));
		      l.setRight(new Node(l.getW(),l.getH() - div, l.getX(), div ,l.getMemeCouleurProb() , chooseColor(l)));
	      }else {
	    	  l.setLeft(new Node(l.getX() + div, l.getH(),div, l.getY(), l.getMemeCouleurProb(), chooseColor(l) ));
			  l.setRight(new Node(l.getW() - div, l.getH(), div, l.getY(), l.getMemeCouleurProb(), chooseColor(l)));
	      }
	     // r.setLeft(new Node(r.getX(), h, x, y, memeCouleurProb, chooseColor(r.getLeft())));
	     // r.setRight(new Node(memeCouleurProb, memeCouleurProb, memeCouleurProb, memeCouleurProb, memeCouleurProb, chooseColor(r.getRight()))); 
	      nbFeuilleCourant += 2;
		  if (nbFeuilleCourant > nbFeuille){
			l.setRight(null);
			nbFeuilleCourant-=1;
		  }else{
			generateRandomTree(r.getLeft());
	      	generateRandomTree(r.getRight());
			
		  }
		  
	      
	    }
		System.out.print(nbFeuilleCourant);
	  }
	
	public Node addLeaf(Node r, char c) {
		if(r == null) {
			return null;
		}else {
			if (c == 'y') {
				
			}else {
				
			}
		}
		return r;
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
		// TODO Auto-generated method stub
		return r;
	}
}
