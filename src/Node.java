
import java.awt.*;

public class Node {
	private double w,h,x,y,weight;
	private Node left;
	private Node right;
	private Color col;
	private float memeCouleurProb;
	
	public Node(double _w, double _h, double _x, double _y, double _memeCouleurProb) {
		this.w = _w;
		this.h = _h;
		this.x = _x;
		this.y = _y;
		this.weight = (w*h)/Math.pow((w+h),1.5);
		this.col = Color.WHITE;
	}
}