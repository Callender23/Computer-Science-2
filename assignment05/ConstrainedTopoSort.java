// Jeffrey Callender
// NID je610689
// Summer 2019

import java.io.File;
import java.util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;


// making a vertex class right here for our adjacency list
class Vertex
{
	int inEdgeCount;
	int numberOfVertices;
	int adjVertices;
	boolean visited = false;
	ArrayList<Integer> adjacencyVertices;

	// a  HashSet that represent both our incomingEdges and out going edges
	public HashSet<Integer> inEdges;
	public HashSet<Integer> outEdges;

	// this is representing the number of vertices that we have in our graph of the file
	static Integer numbVertex = null;

	// this is our constructor
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
			// we will get the file open up and fill it with the number of vertices in our graph
			Scanner reader = new Scanner(new File(filename));

			int numbVertices = Integer.parseInt(reader.nextLine());
			Vertex.numbVertex = numbVertices;
			adjList = new ArrayList<Vertex>();

			// we loop in here and add our vertices to the list
			//  make sure to get the next integer and add to the adjacent vertices.
			for(int i = 0; i < Vertex.numbVertex; i++)
			{
				adjList.add(new Vertex(i + 1, reader.nextInt()));
				for(int j = 0; j < adjList.get(i).adjVertices; j++)
				{
					int adjacencyVertex = reader.nextInt();
					// we get here and make sure that we add the number of adj vertices to our
					// outEdges
					adjList.get(i).adjacencyVertices.add(adjacencyVertex);
					adjList.get(i).outEdges.add(adjacencyVertex);
				}
			}

			// we come into this loop to fill our HashSet of that is contains the incomingEdges
			//
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

	//
	public boolean hasConstrainedTopoSort(int x, int y)
	{
		// we make sure that out input are vaild so  failing these is an instant return false
		if((x <= 0 || y <= 0) || ( x > Vertex.numbVertex || y > Vertex.numbVertex) || (x == y))
			return false;

		// here is a helper method that will return true if the graph has a vaild topological sort
		// if this not then we return true and there is no topological sort
		if(topologicalSort(x - 1, y - 1))
			return true;
		else
			return false;
	}

	// i give credit to Dr. Seanzumanki  for this code which was a topological sort given to us
	// that uses a adjacency matrix. using this i was able to turn this into an adjacency list.
	public boolean topologicalSort(int x, int y)
	{
		// we initiaite the number of incoming vetices each time we are using this method to Ensure
		// we dont miss or have any unwanted values in our adjacency list
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

		// here we proceed to add the number of incoming edges to our queue
		for(int i = 0; i < Vertex.numbVertex; i++)
			if(adjList.get(i).inEdges.size() == 0)
				q.add(adjList.get(i));

		while(!q.isEmpty())
		{
			// this will remove a vertex we have ad then add it to the topologicalSort
			Vertex v = q.remove();
			cnt++;

			// we start to pull out our edges that are coming in and if we see that it equal to zero
			// then we continue to add it to the queue
			for(int i = 0; i < Vertex.numbVertex; i++)
			{
				if(adjList.get(i).inEdges.contains(v.numberOfVertices) && --adjList.get(i).inEdgeCount == 0)
					q.add(adjList.get(i));
			}
		}

		// this is a check to see if we have a cycle
		if(cnt != Vertex.numbVertex)
			return false;

		// we coem into this loop to make sure that our used variables are going to be set back
		// to the default value.
		// we also come here and make sure to add the edges that dont have any incoming edges
		for(int i = 0; i < Vertex.numbVertex; i++)
		{
			adjList.get(i).inEdgeCount = adjList.get(i).inEdges.size();

			if(adjList.get(i).inEdges.size() == 0)
				q.add(adjList.get(i));

			// we come back to set our visted boolean array to false
			adjList.get(i).visited = false;
		}

		// in this iteration we check to see if we have a condition in which x comes before y.
		while(!q.isEmpty())
		{
			// we be sure to set our vertex object v that represent our vertex to null
			Vertex v = null;

			// if we see that y is in the queue and we see its the only one then we dont grab it at
			// all
			if(q.size() > 1 && q.contains(adjList.get(y)))
			{
				boolean removed = q.remove(adjList.get(x));

				// if we see this removed is true then we know that we have grabbed x hence we return
				// true since we have actually grabbed x before y
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
				// we make sure that we remove the vertex from the queue
				v = q.remove();

				// if we get the verties being this case then we know that x comes first
				if(v.numberOfVertices == (x + 1))
					return true;

				// here we know that y comes then comes first hence we will return false for this
				if(v.numberOfVertices == (y + 1))
					return false;
			}

			// we make sure that wee add the vertex from our TopoHolder arrayList
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

		// if we get here then we return true
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
