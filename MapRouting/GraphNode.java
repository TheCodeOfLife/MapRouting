import java.util.ArrayList;
import java.util.List;

public class GraphNode implements Comparable<GraphNode>{

	private int x,y;
	private int name;
	private GraphNode parent;
	private List<GraphNode> edges = new ArrayList<GraphNode>();
	private double currentDistance = Double.MAX_VALUE;
	
	public GraphNode(int name, int x, int y){
		this.name=name;
		this.x=x;
		this.y=y;
	}
	
	public int getName(){
		return name;
	}
	
	public void setName(int n){
		name = n;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public List<GraphNode> getEdges(){
		return edges;
	}
	
	public double getDistance(){
		return currentDistance;
	}
	
	public void setDistance(double dist){
		currentDistance = dist;
	}
	
	public void addEdge(GraphNode node){
		edges.add(node);
	}

	public int compareTo(GraphNode node) {
		return (int)(currentDistance-node.currentDistance);
	}
	
	public double distanceTo(GraphNode node){
		int x1= x;
		int x2=node.getX();
		int y1=y;
		int y2=node.getY();
		return Math.abs(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}
	
	public GraphNode getParent(){
		return parent;
	}
	
	public void setParent(GraphNode node){
		parent = node;
	}
	
}
