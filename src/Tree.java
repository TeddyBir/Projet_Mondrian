import java.awt.*;
import java.util.Vector;

public class Tree {
	private int nbFeuille;
	private double minDimensionCoupe;
	private double memeCouleurProb;
	private double proportionCoupe;
	//private Color[] tabC = {Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.WHITE};
	Vector<Color> TabC;
	public Tree(int _nbFeuille, double _minDC, Vector<Color> _TabC) {
		nbFeuille = _nbFeuille;
		minDimensionCoupe = _minDC;
		TabC = _TabC;
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
