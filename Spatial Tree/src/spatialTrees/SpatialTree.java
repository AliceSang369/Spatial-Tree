/**
 * @author Yuwen Sang
 * CS 2673
 * Assignment05_SpatialTrees
 * May 31, 2019
 */
package spatialTrees;

import java.awt.geom.Point2D;
import java.util.ArrayList;



public class SpatialTree {
	private int size;
	private SpatialTreeNode root;
//	private SpatialTreeNode currentNode;
	private ArrayList<SpatialTreeNode> pointList;


	public SpatialTree() {
		root = null;
		size = 0;
//		currentNode = null;
		pointList = new ArrayList<SpatialTreeNode>();
	
	}
	
	public void add(Point2D point) {
		if(size == 0) {//empty tree, add root
			root = new SpatialTreeNode (point, true, null);//Suppose the root is x-node
//			currentNode = root;
		}else{//insert after X-Node (this is a Y-Node)
			addHelper(point, root);
		}
//		pointList.add(point);
		size++;
	}
	
	//Private Helper of add() method
	private void addHelper(Point2D point, SpatialTreeNode parent) {
		if(parent.isXnode()) {//This is an x-node, the added node will be a y-node
			
			if(point.getX() < parent.getX()) {//This is a left y-node
				if(parent.getLeftNode() == null) {//The parent's left is empty
					parent.setLeft(point, false, parent);
				}else {//The parent's left has been set
					parent = parent.getLeftNode();
					addHelper(point, parent);
				}
				
			}else {//This is a right y-node
				if(parent.getRightNode() == null) {
					parent.setRight(point, false, parent);
				}else {
					parent = parent.getRightNode();
					addHelper(point, parent);
				}
			}
			
		}else {//This is a y-node, the added node will be an x-node
			
			if(point.getY() < parent.getY()) {//This is a left x-node
				if(parent.getLeftNode() == null) {//The parent's left is empty
					parent.setLeft(point, true, parent);
//					currentNode = parent.getLeftNode();
				}else {//The parent's left has been set
					parent = parent.getLeftNode();
					addHelper(point, parent);
				}
				
			}else {//This is a right x-node
				if(parent.getRightNode() == null) {//The parent's right is empty
					parent.setRight(point, true, parent);
//					currentNode = parent.getRightNode();
				}else {//The parent's right has been set
					parent = parent.getRightNode();
					addHelper(point, parent);
				}
			}
		}
	}
	
//	//Tester (toString())
//	public String toString(SpatialTreeNode node, int level) {
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < level; i++) {
//			sb.append("\t");
//		}
//		sb.append(node.getX() + ", " + node.getY() + "\n");
//		
//		if(node.getLeftNode() != null) {
//			toString(node.getLeftNode(), level+1);
//		}
//		
//		if(node.getRightNode() != null) {
//			toString(node.getRightNode(), level+1);
//		}
//		
//		return sb.toString();
//				
//	}
	
	
	//Draw Spatial Tree
	public void draw() {
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.setPenRadius(0.02);
		StdDraw.point(root.getX(), root.getY());
		StdDraw.setPenRadius(0.003);
		StdDraw.line(root.getX(), 0, root.getX(), 1);
		
		drawHelper(root);
		
	}
	
	//Private Helper of draw() method
	//pink = x-nodes; blue = y-nodes; green = cyan;
	private void drawHelper(SpatialTreeNode node) {
		/*DRAW LEFT NODES*/
		if (node.getLeftNode() != null) { 
			StdDraw.setPenRadius(0.002);
		    StdDraw.setPenColor(StdDraw.BLUE);
		    if (node.isXnode() == true) { //root's left child
		    	if (node == root) {
		    		StdDraw.line(0, node.getLeftNode().getY(), root.getX(), node.getLeftNode().getY());
		    	}
		    }
		    SpatialTreeNode tmp = node;
		    double x = tmp.getLeftNode().getX();
		    double y = tmp.getLeftNode().getY();
		    // y-node, Left, to the left of root
		    if (node.isXnode() == true && node.getX() < root.getX()) {
		    	double lBound = 0;
		    	double rBound = tmp.getX();
		    	while (node != root) {
		    		if (node.getX() < x) {
		    			lBound = Math.max(lBound, node.getX());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(lBound, tmp.getLeftNode().getY(), rBound, tmp.getLeftNode().getY());
		    }
		    // y-node, Left, to the right of root.
		    if (node.isXnode() == true && node.getX() > root.getX()) {
		    	double lBound = root.getX();
		    	double rBound = tmp.getX();
		    	while (node != root) {
		    		if (node.getX() < x) {
		    			lBound = Math.max(lBound, node.getX());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(lBound, tmp.getLeftNode().getY(), rBound, tmp.getLeftNode().getY());
		    }
		    // x-node, Left, below root.getLeft().getY()
		    StdDraw.setPenColor(StdDraw.PINK);
		    if (node.isXnode() == false && node == root.getLeftNode()) {
	    		StdDraw.line(x, 0, x, node.getY());
	    	}
		    if (node.isXnode() == false && node.getY() < root.getLeftNode().getY() && node.getX() < root.getX()) {
		    	
		    	double upBound = node.getY();
		    	double lowBound = 0;
		    	while (node != root.getLeftNode()) {
		    		if (0 < node.getY() && node.getY() < y) {
		    			lowBound = Math.max(lowBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		    
		    //x-node, Left, above root.getLeft.getY()
		    if (node.isXnode() == false && node.getY() > root.getLeftNode().getY() && node.getX() < root.getX()) {
		    	double upBound = node.getY();
		    	double lowBound = root.getLeftNode().getY();
		    	while (node != root.getLeftNode()) {
		    		if (root.getLeftNode().getY() < node.getY() && node.getY() < y) {
		    			lowBound = Math.max(lowBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		    // x-node, Left, below root.getRight.getY()
		    if (node.isXnode() == false && node == root.getRightNode()) {
	    		StdDraw.line(x, 0, x, node.getY());
	    	}
		    if (node.isXnode() == false && node.getY() < root.getRightNode().getY() && node.getX() > root.getX()) {
		    	double upBound = node.getY();
		    	double lowBound = 0;
		    	while (node != root.getRightNode()) {
		    		if (0 < node.getY() && node.getY() < y) {
		    			lowBound = Math.max(lowBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		
		    // x-node, Left, above root.getRight.getY()
		    if (node.isXnode() == false && node.getY() > root.getRightNode().getY() && node.getX() > root.getX()) {
		    	double upBound = node.getY();
		    	double lowBound = root.getRightNode().getY();
		    	while (node != root.getRightNode()) {
		    		if (root.getRightNode().getY() < node.getY() && node.getY() < y) {
		    			lowBound = Math.max(lowBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		    node = tmp;
		    StdDraw.setPenRadius(0.009);
		    StdDraw.setPenColor(StdDraw.BLACK);
		    StdDraw.point(node.getLeftNode().getX(), node.getLeftNode().getY());
		    drawHelper(node.getLeftNode());
		}
		    
		/*DRAW RIGHT NODES*/
		if (node.getRightNode() != null) {
			StdDraw.setPenRadius(0.002);
		    StdDraw.setPenColor(StdDraw.BLUE);
		    if (node.isXnode() == true) {
		    	if (node == root) {
		    		StdDraw.line(root.getX(), node.getRightNode().getY(), 1, node.getRightNode().getY());
		    		}
		    	}
		    SpatialTreeNode tmp = node;
		    double x = tmp.getRightNode().getX();
		    double y = tmp.getRightNode().getY();
		    // y-node, Right, to the left of root.
		    if (node.isXnode() == true && node.getX() < root.getX()) {
		    	double lBound = tmp.getX();
		    	double rBound = root.getX();
		    	while (node != root) {
		    		if (node.getX() > x) {
		    			rBound = Math.min(rBound, node.getX());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(lBound, tmp.getRightNode().getY(), rBound, tmp.getRightNode().getY());
		    }
		    // y-node, Right, to the right of root.
		    if (node.isXnode() == true && node.getX() > root.getX()) {
		    	double lBound = tmp.getX();
		    	double rBound = 1;
		    	while (node != root) {
		    		if (node.getX() > x) {
		    			rBound = Math.min(rBound, node.getX());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(lBound, tmp.getRightNode().getY(), rBound, tmp.getRightNode().getY());
		    }
		    // x-node, Right, above root.getLeft().getY()
		    StdDraw.setPenColor(StdDraw.PINK);
		    if (node.isXnode() == false && node == root.getLeftNode()) {
	    		StdDraw.line(x, node.getY(), x, 1);
	    	}
		    if (node.isXnode() == false && node.getY() > root.getLeftNode().getY() && node.getX() < root.getX()) {
		    		
		    	double upBound = 1;
		    	double lowBound = node.getY();
		    	while (node != root.getLeftNode()) {
		    		if (y < node.getY() && node.getY() < 1) {
		    			upBound = Math.min(upBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		    
		   	// x-node, Right, below root.getLeft.getY()
	    	if (node.isXnode() == false && node.getY() < root.getLeftNode().getY() && node.getX() < root.getX()) {
	    		double upBound = root.getLeftNode().getY();
	    		double lowBound = node.getY();
	    		while (node != root.getLeftNode()) {
	    			if (y < node.getY() && node.getY() < root.getLeftNode().getY()) {
	    				upBound = Math.min(upBound, node.getY());
	    			}
	    			node = node.getParent().getParent();
	    		}
	    		StdDraw.line(x, lowBound, x, upBound);
	    	}
		    
		    // x-node, Right, above root.getRight.getY()
		    if (node.isXnode() == false && node == root.getRightNode()) {
	    		StdDraw.line(x, node.getY(), x, 1);
	    	}
		    if (node.isXnode() == false && node.getY() > root.getRightNode().getY() && node.getX() > root.getX()) {
		    	double upBound = 1;
		    	double lowBound = node.getY();
		    	while (node != root.getRightNode()) {
		    		if (y < node.getY() && node.getY() < 1) {
		    			upBound = Math.min(upBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		
		    // x-node, Right, below root.getRight.getY()
		    if (node.isXnode() == false && node.getY() < root.getRightNode().getY() && node.getX() > root.getX()) {
		    	double upBound = root.getRightNode().getY();
		    	double lowBound = node.getY();
		    	while (node != root.getRightNode()) {
		    		if (y < node.getY() && node.getY() < root.getRightNode().getY()) {
		    			upBound = Math.min(upBound, node.getY());
		    		}
		    		node = node.getParent().getParent();
		    	}
		    	StdDraw.line(x, lowBound, x, upBound);
		    }
		    node = tmp;
		    StdDraw.setPenRadius(0.009);
		    StdDraw.setPenColor(StdDraw.BLACK);
		    StdDraw.point(node.getRightNode().getX(), node.getRightNode().getY());
		    drawHelper(node.getRightNode());
		}
		
		
		if (node.getLeftNode() == null && node.getRightNode() == null) {
		        // leaf node, does not have children
		}

	}

	//query() method
	public void query(Point2D center, double radius){
		queryHelper(root, center, radius);
		while (pointList.isEmpty() == false) {
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.setPenRadius(0.025);
			StdDraw.point(pointList.get(pointList.size() - 1).getX(), pointList.get(pointList.size() - 1).getY());
			pointList.remove(pointList.size() - 1);
		}
		if (root.getPoint().distance(center) < radius) {
			StdDraw.point(root.getX(), root.getY());
		}
	}
	
	//query() method helper
	private ArrayList<SpatialTreeNode> queryHelper (SpatialTreeNode node, Point2D center, double radius){
		SpatialTreeNode beginSearchNode;
		
		//GOT FROM LEFT
		if (node.getLeftNode() != null) {
	    	/* X-line got from left && to the left of the querying circle
			   Y-line got from left && under the querying circle*/
	    	if ((node.getLeftNode().isXnode() && node.getLeftNode().getX() < center.getX() - radius) || 
	    			(node.getLeftNode().isXnode() && node.getLeftNode().getY() < center.getY() - radius)) {
	    		if (node.getLeftNode().getRightNode() != null) {
	    			beginSearchNode = node.getLeftNode().getRightNode();
	    			queryHelper(beginSearchNode, center, radius);
	    		}
	    	}
	    	
	    	/* X-line got from left &&to the right of the querying circle
	    	   Y-line got from left && above the querying circle*/
	    	else if ((node.getLeftNode().isXnode() && node.getLeftNode().getX() > center.getX() + radius) || 
	    			(!node.getLeftNode().isXnode() && node.getLeftNode().getY() > center.getY() + radius)) {
	    		if (node.getLeftNode().getLeftNode() != null) {
	    			beginSearchNode = node.getLeftNode().getLeftNode();
	    			queryHelper(beginSearchNode, center, radius);
	    		}
	    	}
	    	// Add x-node
	    	if (node.getLeftNode().isXnode() && node.getLeftNode().getPoint().distance(center) < radius) {
	    		pointList.add(node.getLeftNode());
	    	}
	    	// Add y-node
	    	if (!node.getLeftNode().isXnode() && node.getLeftNode().getPoint().distance(center) < radius) {
	    		pointList.add(node.getLeftNode());
	    	}
	    	//Recursion
	    	queryHelper(node.getLeftNode(), center, radius);
		}
		
		
		//GOT FROM RIGHT
		if (node.getRightNode() != null) {
	        /* X-line got from right && to the left of the querying circle
	           Y-line got from right &&  under the querying circle*/
	        if ((node.getRightNode().isXnode() && node.getRightNode().getX() < center.getX() - radius) || 
	        		(!node.getRightNode().isXnode() && node.getRightNode().getY() < center.getY() - radius)) {
	    		if (node.getRightNode().getRightNode() != null) {
	    			beginSearchNode = node.getRightNode().getRightNode();
	    			queryHelper(beginSearchNode, center, radius);
	    		}
	    	}
	        /* X-line got from right && right of the querying circle
	           Y-line got from right && above the querying circle*/
	    	if ((node.getRightNode().isXnode() && node.getRightNode().getX() > center.getX() + radius) || 
	    			(!node.getRightNode().isXnode() && node.getRightNode().getY() > center.getY() + radius)) {
	    		if (node.getRightNode().getLeftNode() != null) {
	    			beginSearchNode = node.getRightNode().getLeftNode();
	    			queryHelper(beginSearchNode, center, radius);
	    		}
	    	}
	    	// Add x-node
	    	if (node.getRightNode().isXnode() && node.getRightNode().getPoint().distance(center) < radius) {
	    		pointList.add(node.getRightNode());
	    	}
	    	// Add y-node 
	    	if (!node.getRightNode().isXnode() && node.getRightNode().getPoint().distance(center) < radius) {
	    		pointList.add(node.getRightNode());
	    		
	    	}
	    	queryHelper(node.getRightNode(), center, radius);
	    }
	    if (node.getLeftNode() == null && node.getRightNode() == null) {
	        // leaf node, does not have children
	    }
	    return pointList;
	}
	

}
