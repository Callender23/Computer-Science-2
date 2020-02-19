
import java.io.File;
import java.util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Jeffrey Callender
// NID je10689
// Summer 2019
// making a vertex class right here for our adjacency list

class Vertex
{
	int inEdgeCount;
	int numberOfVertices;
	int adjVertices;
	boolean visited = false;
	ArrayList<Integer> adjacencyVertices;
	public HashSet<Integer> inEdges;
	public HashSet<Integer> outEdges;
	static Integer numbVertex = null;

	// constructor
	Vertex(int totalVertex, int totalVertexNumber)
	{
		numberOfVertices = totalVertex;
		adjVertices = totalVertexNumber;
		inEdgeCount = 0;
		outEdges = new HashSet<>();
		inEdges = new HashSet<>();
		adjacencyVertices = new ArrayList<Integer>();
	}
}
public class ConstrainedTopoSort
{
	// we will declare a adjacency list here
	ArrayList<Vertex> adjList;

	public ConstrainedTopoSort(String filename)
	{
		// we will read the file into the list
		// we have a try catch loop to make sure we handle the exception here
		try
		{
			Scanner reader = new Scanner(new File(filename));

			int numbVertices = Integer.parseInt(reader.nextLine());
			Vertex.numbVertex = numbVertices;
			adjList = new ArrayList<Vertex>();

			// now we loop in
			for(int i = 0; i < Vertex.numbVertex; i++)
			{
				adjList.add(new Vertex(i + 1, reader.nextInt()));
				for(int j = 0; j < adjList.get(i).adjVertices; j++)
				{
					int adjacencyVertex = reader.nextInt();
					adjList.get(i).adjacencyVertices.add(adjacencyVertex);
					adjList.get(i).outEdges.add(adjacencyVertex);
				}
			}

			// another separae for loop that will fill our HashSet with the incoming edges.
			for(int i = 0; i < Vertex.numbVertex; i++)
				for(Integer outGoingEdges: adjList.get(i).outEdges)
					adjList.get(outGoingEdges - 1).inEdges.add(i + 1);
		}

		// we have the exception here to make sure that the files exist
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public boolean hasConstrainedTopoSort(int x, int y)
	{
		// we make sure that out input are vaild so  failing these is an instant return false
		if((x <= 0 || y <= 0) || ( x > Vertex.numbVertex || y > Vertex.numbVertex) || (x == y))
			return false;

		// here is a helper method that will return true if the graph has a vaild topological sort
		if(topologicalSort(x - 1, y - 1))
			return true;
		else
			return false;
	}

	public boolean topologicalSort(int x, int y)
	{
		for(int i = 0; i < Vertex.numbVertex; i++)
		{
			adjList.get(i).inEdgeCount = adjList.get(i).inEdges.size();

			// we have this set as false for the default everytime we get this method used
			adjList.get(i).visited = false;
		}

		// we declare variable cnt to  keep track of cycle and flag to see if x comes becomes y
		int cnt = 0;
		boolean flag = false;

		// we have the an ArrayDeque hold the incoming inEdges
		ArrayDeque<Vertex> q = new ArrayDeque<Vertex>();
		ArrayList<Vertex> TopoHolder = new ArrayList<Vertex>();

		for(int i = 0; i < Vertex.numbVertex; i++)
			if(adjList.get(i).inEdges.size() == 0)
				q.add(adjList.get(i));

		while(!q.isEmpty())
		{
			// this will remove a vertex we have ad then add it to the TopoSort
			Vertex v = q.remove();
			cnt++;

			for(int i = 0; i < Vertex.numbVertex; i++)
			{
				if(adjList.get(i).inEdges.contains(v.numberOfVertices) && --adjList.get(i).inEdgeCount == 0)
					q.add(adjList.get(i));
			}
		}

		// this is a check to see if we have a cycle
		if(cnt != Vertex.numbVertex)
			return false;

		for(int i = 0; i < Vertex.numbVertex; i++)
		{
			adjList.get(i).inEdgeCount = adjList.get(i).inEdges.size();

			if(adjList.get(i).inEdges.size() == 0)
				q.add(adjList.get(i));

			adjList.get(i).visited = false;
		}

		while(!q.isEmpty())
		{
			Vertex v = null;

			if(q.size() > 1 && q.contains(adjList.get(y)))
			{
				boolean removed = q.remove(adjList.get(x));
				if(removed)
					return true;
				else
				{
					if(q.getFirst() == adjList.get(y))
						v = q.removeLast();
					else
					{
						v = q.remove();
						if(v.numberOfVertices == (x + 1))
							return true;
					}
				}
			}
			else
			{
				v = q.remove();
				if(v.numberOfVertices == (x + 1))
					return true;
				if(v.numberOfVertices == (y + 1))
					return false;
			}

			v.visited = true;
			TopoHolder.add(v);
			cnt++;

			for(int  i = 0; i < Vertex.numbVertex; i++)
			{
				if(adjList.get(i).inEdges.contains(v.numberOfVertices) &&
														--adjList.get(i).inEdgeCount == 0)
					q.add(adjList.get(i));
			}
		}

		// this is again a check to see if we find a cycle
		if(cnt != Vertex.numbVertex)
			return false;


		return true;

	}

	public static double difficultyRating()
	{
		return 3.7;
	}

	public static double hoursSpent()
	{
		return 25;
	}
}
