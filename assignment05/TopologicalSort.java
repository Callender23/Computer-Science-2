import java.util.List;
import java.util.Stack;
import java.util.*;


public class TopologicalSort
{
	Stack<Node> stack;

	public TopologicalSort() {
		stack=new Stack<>();
	}
	static class Node
	{
		int data;
		boolean visited;
		List<Node> neighbours;

		Node(int data)
		{
			this.data=data;
			this.neighbours=new ArrayList<>();

		}
		public void addneighbours(Node neighbourNode)
		{
			this.neighbours.add(neighbourNode);
		}
		public List<Node> getNeighbours() {
			return neighbours;
		}
		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
		public String toString()
		{
			return ""+data;
		}
	}

	// Recursive toplogical Sort
	public  void toplogicalSort(Node node)
	{
		List<Node> neighbours=node.getNeighbours();
		for (int i = 0; i < neighbours.size(); i++) {
			Node n=neighbours.get(i);
			if(n!=null && !n.visited)
			{
				toplogicalSort(n);
				n.visited=true;
			}
		}
		stack.push(node);
	}
}
