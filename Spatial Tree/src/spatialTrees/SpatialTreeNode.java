/**
 * @author Yuwen Sang
 * CS 2673
 * Assignment05_SpatialTrees
 * May 31, 2019
 */
package spatialTrees;

import java.awt.geom.Point2D;


public class SpatialTreeNode {
	
	private Point2D point;
	private boolean isXNode;
	private SpatialTreeNode parent;
	private SpatialTreeNode left;
	private SpatialTreeNode right;
	
	public SpatialTreeNode(Point2D point, boolean isX, SpatialTreeNode parent) {
		this.point = point;
		isXNode = isX;
		this.parent = parent;
		left = null;
		right = null;
	}
	
	public boolean isXnode() {
		return isXNode;
	}
	
	public void setLeft(Point2D point, boolean isX, SpatialTreeNode parent) {
		left = new SpatialTreeNode(point, isX, parent);
	}
	
	public void setRight(Point2D point, boolean isX, SpatialTreeNode parent) {
		right = new SpatialTreeNode(point, isX, parent);
	}
	
	public SpatialTreeNode getParent() {
		return parent;
	}
	
	public SpatialTreeNode getLeftNode() {
		return left;
	}
	
	public SpatialTreeNode getRightNode() {
		return right;
	}
	
	public double getX() {
		return point.getX();
	}
	
	public double getY() {
		return point.getY();
	}
	
	public Point2D getPoint() {
		return point;
	}
	
	

}
