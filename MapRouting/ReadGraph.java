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

		int numVertices = scan.nextInt();
		int numEdges = scan.nextInt();

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
		
		GraphNode source = graph.get(0);
		GraphNode destination = graph.get(5000);
		
		//MinHeap<GraphNode> heap = new MinHeap<GraphNode>();
		
		long startTime = System.currentTimeMillis();
		System.out.println(MaybeDijkstras(source, destination, graph));
		long endTime = System.currentTimeMillis();
		System.out.println("Dijkstras computed in "+(endTime-startTime) +" milisecs");
		

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
	
	private static double MaybeDijkstras(GraphNode source, GraphNode destination, HashMap<Integer, GraphNode> graph2) {
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
				//if(e==destination) return e.getDistance();
			}
		}
		
		return destination.getDistance();
	}
	
	private static double BetterDijkstras(GraphNode source, GraphNode destination, HashMap<Integer, GraphNode> graph2) {
		PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>(graph2.values());
	
		graph2.get(source.getName()).setDistance(0);

		while(!queue.isEmpty()){
			GraphNode u = queue.poll();
			for(GraphNode e: u.getEdges()){
				if((u.distanceTo(e)+u.getDistance())<e.getDistance()){
					e.setDistance(u.getDistance()+u.distanceTo(e));
					e.setParent(u);
					if(e==destination) return e.getDistance();
				}
				if(e==destination) return e.getDistance();
			}
		}
		
		return destination.getDistance();
	}
	
	
	
//	private static double BetterDijkstras(GraphNode source, GraphNode destination, HashMap<Integer, GraphNode> graph2) {
//		//PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>(graph2.values());
//		FibonacciHeap<GraphNode> heap = new FibonacciHeap<GraphNode>();
//
//		graph2.get(source.getName()).setDistance(0);
//		for(int e: graph2.keySet()){
//			heap.enqueue(graph2.get(e), graph2.get(e).getDistance());
//		}
//
//		while(!heap.isEmpty()){
//			GraphNode u = heap.dequeueMin().getValue();
//			for(GraphNode e: u.getEdges()){
//				if((u.distanceTo(e)+u.getDistance())<e.getDistance()){
//					e.setDistance(u.getDistance()+u.distanceTo(e));
//					e.setParent(u);
//				}
//			}
//		}
//		
//		return destination.getDistance();
//	}
}
