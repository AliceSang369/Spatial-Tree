/**
 * @author Yuwen Sang
 * CS 2673
 * Assignment05_SpatialTrees
 * May 31, 2019
 */
package spatialTrees;

import java.awt.geom.Point2D;
import java.util.Scanner;


public class Driver {


	public static void main(String[] args) {
		
		StdDraw.setCanvasSize(1000,1000);
		StdDraw.setXscale(0,1);
		StdDraw.setYscale(0,1);
		SpatialTree st = new SpatialTree();

		//Create 100 new points && add them into SpatialTree
		for(int i = 0; i < 100; i++) {
			double x = Math.random();
			double y = Math.random();
//			System.out.println(x + ", " + y + "\n");
			Point2D p = new Point2D.Double(x,y);
			st.add(p);
		}
		
		st.draw();
//		
//		System.out.println(st.toString());
		//query test
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter x-coordinate of your circle center ( 0.0 - 1.0): ");
		double x = keyboard.nextDouble();
		System.out.print("Enter y-coordinate of your circle center (0.0 - 1.0): ");
		double y = keyboard.nextDouble();
		System.out.print("Enter radius (Double 0.0 - 1.0): ");
		double radius = keyboard.nextDouble();
		keyboard.close();
		Point2D center = new Point2D.Double(x, y);
		StdDraw.setPenRadius(0.001);
		StdDraw.circle(center.getX(), center.getY(), radius);
		st.query(center, radius);
	}

}
