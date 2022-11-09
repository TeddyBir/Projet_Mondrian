import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
  public void toImage(Node r){
    if (r == null){
      return;
    }else{
      setRectangle((int)r.getX(),(int) r.getW(), (int)r.getY(), (int)r.getH(), r.getCol());
      toImage(r.getLeft());
      toImage(r.getRight());
    }
  }
  
  public static void main(String[] args) throws IOException {
	 /* Image img = new Image(100,200);
	  img.setRectangle(0, 10, 0, 20, Color.RED);
	  img.save("test1.png");*/
	  
	//private Color[] tabC = {Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK, Color.WHITE};
	  Vector<Color> tabC = new Vector<Color>();
	    tabC.add(Color.RED);
	    tabC.add(Color.BLUE);
	    tabC.add(Color.YELLOW);
	    tabC.add(Color.BLACK);
	    tabC.add(Color.WHITE);
	    
	    Node R = new Node(150,150,0.2);

	    Tree tr = new Tree(R,10,20,0.2,tabC);
      //tr.generateRandomTree(R);
      tr.generateRandomTree(R);
      //System.out.print(tr.getRoot().isChoosenDiv_X());
      Image img = new Image(150, 150);
      img.toImage(R);
      img.save("test.png");
	    //System.out.println();
	   // tr.chooseDivision(R);
	   //tr.getRoot().setLeft(new Node(20,20,0,0,0.6, tr.chooseColor(R)));
	    //tr.getRoot().setRight(new Node(20,20,0,50,0.6, tr.chooseColor(R)));
	    //System.out.println(tr.getRoot().getY());
	    //System.out.println(tr.getRoot().getLeft().getCol());
	  
  }
}
