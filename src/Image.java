import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Vector;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * Helper class to create, modify and save images.
 * @author Mathieu Vavrille
 * For example:
 * ```java
 * Image img = new Image (100, 200);
 * img.setRectangle(10, 20, 50, 30, Color.YELLOW); // Color should be imported with `import java.awt.Color`
 * img.save("test.png");
 * ```
 */
public class Image
{
  private final BufferedImage image; // The image

  /** Constructs an empty image (initially black) of width `width` and height `height` */
  public Image(int width, int height) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Sets one pixel of the image.
   * WARNING : NO CHECK IS DONE. IF YOU WRITE OUTSIDE THE IMAGE IT WILL RAISE AN ERROR
   */
  public void setPixel(int x, int y, Color col) {
    image.setRGB(x,y,col.getRGB());
  }
  /**
   * Sets all the pixels in the given region to the given color.
   * WARNING : END COORDINATES EXCLUDED.
   * WARNING : NO CHECK IS DONE. IF YOU WRITE OUTSIDE THE IMAGE IT WILL RAISE AN ERROR
   */
  public void setRectangle(int startX, int endX, int startY, int endY, Color color) {
    for(int x = startX; x < endX; x++) {
      for(int y = startY; y < endY; y++) {
        setPixel(x,y,color);
      }
    }
  }

  /**
   * Saves the image to a file, in PNG format
   */
  public void save(String filename) throws IOException {
    File fic = new File(filename);
    fic = new File(fic.getAbsolutePath());
    ImageIO.write(image,"png",fic);
  }

  /**
   * Number of pixels in X dimension
   */
  public int width() {
    return image.getWidth();
  }

  /**
   * Number of pixels in Y dimension
   */
  public int height() {
    return image.getHeight();
  }
  
  
  public void toImage(Node r, Tree a) {
    
  if(r.getLeft() == null && r.getRight() == null){
    setRectangle((int)r.getX(),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()),r.getCol());
    /////////////////////////GRAY///////////////////////////////
    //Upper side
    setRectangle((int)r.getX(),(int)(r.getX()+r.getW()), (int)(r.getY()),(int)(r.getY()+(a.getlargeurLigne()/2)), Color.GRAY);
    //Left side
    setRectangle((int)r.getX(),(int)(r.getX()+((a.getlargeurLigne()/2))), (int)r.getY(),(int)(r.getY()+r.getH()), Color.GRAY);
    //Right side
    setRectangle((int)((r.getX()+r.getW())-(a.getlargeurLigne()/2)),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()), Color.GRAY);
    //Lower side
    setRectangle((int)((r.getX()+r.getW())-(a.getlargeurLigne()/2)),(int)(r.getX()+r.getW()), (int)r.getY(),(int)(r.getY()+r.getH()),Color.GRAY);
    
  }else{
    toImage(r.getLeft(), a);
    toImage(r.getRight(),a);
  }
  
}
  
  @SuppressWarnings("resource")
public static void main(String[] args) throws IOException {
	    /*
	     * Image img = new Image(100,200);
	     * img.setRectangle(0, 10, 0, 20, Color.RED);
	     * img.save("test1.png");
	     */

	    // private Color[] tabC = {Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK,
	    // Color.WHITE};
	    Vector<Color> tabC = new Vector<Color>();
	    tabC.add(Color.RED);
	    tabC.add(Color.BLUE);
	    tabC.add(Color.YELLOW);
	    tabC.add(Color.BLACK);
	    tabC.add(Color.WHITE);
	    int seed;
    
	    Node R = new Node(1400, 1400);
    
	    System.out.println("Voulez vous utilisez un seed ? (oui/non)");
	    Scanner useSeed = new Scanner(System.in);
	    String b = useSeed.nextLine();
	    switch(b) {
	    case "oui":
	    	System.out.print("Donnez un seed :");
	 	    Scanner s = new Scanner(System.in);
	 	    seed = s.nextInt();
	 	    Random r = new Random(seed);
	 	    Tree tr = new Tree(R, 50, 0.1, 50, 0.5, 15,tabC, r);
	 	    NodeAvl na = new NodeAvl(R);
	 	    AVL Tavl = new AVL(na);
	 	    System.out.print("Double random : " + r + "\n");
	 	    tr.generateRandomTree(tr.getRoot(), Tavl);
	 	    Image img = new Image((int)tr.getRoot().getW(), (int)tr.getRoot().getH());
		    img.toImage(R, tr);
		    img.save("test.png");
	 	    break;
	    case "non":
	 	    int seed2 = (int)(Math.random() * 100000);
	 	    Random rr = new Random(seed2);
	 	    Tree tr2 = new Tree(R, 50, 0.1, 50, 0.5, 15,tabC, rr);
	 	    NodeAvl naa = new NodeAvl(R);
	 	    AVL Tavll = new AVL(naa);
	 	    tr2.generateRandomTree(tr2.getRoot(), Tavll);
	 	    Image img2 = new Image((int)tr2.getRoot().getW(), (int)tr2.getRoot().getH());
		    img2.toImage(R, tr2);
		    img2.save("test.png");
		    break;
	    }
}
}
