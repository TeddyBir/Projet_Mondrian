import java.awt.*;
import java.util.Vector;

public class Tree {
	private int nbFeuille;
	private double minDimensionCoupe;
	private double memeCouleurProb;
	private double proportionCoupe;
	//private Color[] tabC = {Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.WHITE};
	Vector<Color> TabC;
	
	public Tree(int _nbFeuille, double _minDC, double _proportionCoupe, Vector<Color> _TabC) {
		nbFeuille = _nbFeuille;
		minDimensionCoupe = _minDC;
		TabC = _TabC;
		proportionCoupe = _proportionCoupe;
	}
	
	
	/*Choose a color by evaluating the odd of getMemeCouleurProb
	 * 
	 */
	public Color chooseColor(Node r) {
		if(Math.random() <= r.getMemeCouleurProb()) {
			return r.getCol();
		}else {
			double i = Math.random() * TabC.capacity();
			return TabC.get((int) i);
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
	
	public void chooseDivision(Node r)
	{
		double prob = Math.random();
		if(r.getCol() == Color.WHITE && r.getLeft() == null && r.getRight() == null) {
			//If Y is out of the autorized cut zone then re-set Y
			while(r.getY() < r.getH() * proportionCoupe || r.getY() > r.getH() *(1-  proportionCoupe)) {
				r.setY(r.getH()/(Math.random() * r.getH() - 1));
			}
		}else {
			//Choose X or Y
			if(prob <= r.getW()/(r.getH()+r.getW())) {
				while(r.getX() < r.getW() * proportionCoupe || r.getX() > r.getW() *(1-  proportionCoupe)) {
					r.setX(r.getX()/(Math.random() * r.getW() - 1));
				}
			}else {
				while(r.getY() < r.getH() * proportionCoupe || r.getY() > r.getH() *(1-  proportionCoupe)) {
					r.setY(r.getH()/(Math.random() * r.getH() - 1));
				}
				
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
}
