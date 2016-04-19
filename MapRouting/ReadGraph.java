import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ReadGraph {

	private static HashMap<Integer, GraphNode> graph = new HashMap<Integer, GraphNode>();

	public static void main(String[] args) throws FileNotFoundException{
		
		File file = new File("/Users/ltbaum/Documents/workspace/Optimize Dijkstra/src/usa.txt");

		Scanner scan = new Scanner(file);
		//scan in num vertices and edges
		int numVertices = scan.nextInt();
		int numEdges = scan.nextInt();
		//for each indicated vertex and edge, read in next
		for(int i=0; i<numVertices; i++){
			GraphNode vertex = new GraphNode(scan.nextInt(),scan.nextInt(), scan.nextInt());
			graph.put(vertex.getName(), vertex);
		}

		for(int j=0;j<numEdges;j++){
			int v1=scan.nextInt();
			int v2=scan.nextInt();
			graph.get(v1).addEdge(graph.get(v2));
			graph.get(v2).addEdge(graph.get(v1));
		}
		
		//determine source and destination
		GraphNode source = graph.get(0);
		GraphNode destination = graph.get(5000);

		Scanner user = new Scanner(System.in);
		
		System.out.println("Type \"Dijkstras\" for basic, or \"better\" for the improved one.");
		String next = user.nextLine();
		if(next.equals("Dijkstras")){
			long startTime = System.currentTimeMillis();
			System.out.println(Dijkstras(source, destination, graph));
			long endTime = System.currentTimeMillis();
			System.out.println("Dijkstras computed in "+(endTime-startTime) +" milisecs");
		}		
		else if (next.equals("better")){
			long startTime = System.currentTimeMillis();
			System.out.println(BetterDijkstras(source, destination, graph));
			long endTime = System.currentTimeMillis();
			System.out.println("Modified/Improved Dijkstras computed in "+(endTime-startTime) +" milisecs");
		}
		else System.out.println("Retry");
		

	}

	private static double Dijkstras(GraphNode source, GraphNode destination, HashMap<Integer, GraphNode> graph2) {
		PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>();
	
		graph2.get(source.getName()).setDistance(0);
		queue.add(source);

		while(!queue.isEmpty()){
			GraphNode u = queue.poll();
			for(GraphNode e: u.getEdges()){
				if((u.distanceTo(e)+u.getDistance())<e.getDistance()){
					e.setDistance(u.getDistance()+u.distanceTo(e));
					e.setParent(u);
					queue.add(e);

				}

			}
		}
		
		return destination.getDistance();
	}
	
	private static double BetterDijkstras(GraphNode source, GraphNode destination, HashMap<Integer, GraphNode> graph2) {
		//PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>();
		MinHeap<GraphNode> heap = new MinHeap<GraphNode>();
	
		graph2.get(source.getName()).setDistance(0);
		heap.add(source);

		while(heap.size()!=0){
			GraphNode u = heap.minValue();
			heap.remove();
			for(GraphNode e: u.getEdges()){
				if((u.distanceTo(e)+u.getDistance())<e.getDistance()){
					e.setDistance(u.getDistance()+u.distanceTo(e));
					e.setParent(u);
					heap.add(e);
				}
				if(e==destination) return e.getDistance();
			}
		}
		
		return destination.getDistance();
	}
	
}
