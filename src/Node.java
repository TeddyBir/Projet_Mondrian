
import java.awt.*;

public class Node {
	private double w,h,x,y,weight;
	private Node left;
	private Node right;
	private Color col;
	private double memeCouleurProb;
	
	/* Construction of the root node
	 * 
	 * 
	 */
	public Node(double _w, double _h, double _memeCouleurProb) {
		this.w = _w;
		this.h = _h;
		this.x = 0;
		this.y = 0;
		this.memeCouleurProb = _memeCouleurProb;
		this.left = null;
		this.right = null;
		this.weight = (w*h)/Math.pow((w+h),1.5);
		this.col = Color.WHITE;
	}
	
	/*
	 * Construction of a node
	 */
	public Node(double _w, double _h, double _x, double _y, double _memeCouleurProb, Color _col) {
		this.w = _w;
		this.h = _h;
		this.x = _x;
		this.y = _y;
		this.memeCouleurProb = _memeCouleurProb;
		this.left = null;
		this.right = null;
		this.weight = (w*h)/Math.pow((w+h),1.5);
		this.col = _col;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public double getW() {
		return w;
	}

	public double getH() {
		return h;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWeight() {
		return weight;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Color getCol() {
		return col;
	}

	public double getMemeCouleurProb() {
		return memeCouleurProb;
	}
	
	
}