package fr.team12.mis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Set;

/**
 * Created by Antoine on 07/10/2016.
 */
public class Graph
{

    private Map <String, List<String>> graph;
    
    public Graph(Map graph)
    {
        this.graph = graph;
    }
    /**
     * Get a connected componenent of the graph, if the graph extracted is equal to this, return null.
     * @return a connected graph or null if the graph dont have connected componenet or is empty.
     */
    public Graph getConnexe()
    {
 
        List<String> Explored = new LinkedList();
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) 
        {
            String Vertex = entry.getKey();
            if(entry.getValue().isEmpty())
            {
                Explored.add(Vertex);
            }
            else
            {
                Explored = ExploreNeighbors(Vertex, Explored);
                if(Explored.size() == graph.size())
                {
                    return null;
                }
                else
                {
                    Map <String, List<String>> component = new HashMap();
                    for (String i : Explored)
                    {
                        component.put(i,graph.get(i));
                    }
                    return new Graph(component);
                }
            }
        }
        return null;
    }

    /**
     * Deep First search on a given vertex
     * @param vertex
     * @param Explored
     * @return the list of all explored vertex.
     */
    private List<String> ExploreNeighbors(String vertex, List<String> Explored)
    {
        List<String> neighbors = graph.get(vertex);
        Explored.add(vertex);
        for(String item : neighbors)
        {     
            if(!Explored.contains(item))
            {
                ExploreNeighbors(item, Explored);
            }
        }
        return Explored;
    }

    /**
     * Create a new graph by removing all the vertex contained in removeGraph of this.graph
     * @param removeGraph Graph containing the vortex to remove
     * @return new Graph following the operation graph\removeGraph or null if 
     */
    public Graph deprivateOf(Graph removeGraph)
    {
        Graph result = new Graph(this.graph);
        Set<String> keysRm = removeGraph.graph.keySet();
        for (String item : keysRm)
        {
            result.graph.remove(item);       
        }
        Set<String> keysRes = result.graph.keySet();
        for (String item : keysRes)
        {
            List<String> neighbors = result.graph.get(item);
            for(String remove : keysRm)
            {
                neighbors.remove(remove);           
            }   
            result.graph.replace(item, neighbors);
        }
        return result;
    }
    
    public Set<String> getNeighborsWithVertex(String vertex)
    {
        
        return null;
    }

    /**
     * Make the two neighbors of vertex to "fold" into one new vertex that will replace vertex and his two neighbors
     * @param vertex vertex to fold
     * @return a new graph with the new folded vertex.
     */
    public Graph fold2(String vertex)
    {
        List<String> neighbors = this.graph.get(vertex);
        List<String> newNeighbors = new LinkedList();
        String newKey = "";
        Map<String, List<String>> result = this.graph;
        for (String item : neighbors)
        {
            for(String toadd : this.graph.get(item))
            {
                if(toadd.compareTo(vertex) != 0 && !newNeighbors.contains(toadd))
                {
                    newNeighbors.add(toadd);
                }
            }
            newKey = newKey.concat(item);
            result.remove(item);
        }
        result.remove(vertex);

        for(String item : newNeighbors)
        {
            for(String rm : neighbors)
            {
                if(result.get(item).contains(rm))
                {
                    result.get(item).remove(rm);
                }
            }
            result.get(item).add(newKey);
        }
        result.put(newKey, newNeighbors);
        return new Graph(result);
    }

    public String get2degreeVertex()
    {

        return null;
    }

    /**
     * Check if a list of vertex is a clique, in other words, if all the vertex of the list have for neighbors all the other vertex of the list
     * @param vertexs list of vertexs to check
     * @return true if vertexs is a clique, false if not
     */
    private boolean isComplete(List<String> vertexs)
    {
        for (String item : vertexs)
        {
            for(String toTest : vertexs)
            {
                if(item.compareTo(toTest) != 0 && !this.graph.get(item).contains(toTest))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * Return the neighbors of neighbors of the vertex in parameters ( N²(vertex) )
     * @param vertex 
     * @return the list equals to N²(vertex)
     */
    private List<String> getNeighborsOfNeighbors(String vertex)
    {
        List<String> neighbors2 = new LinkedList();
        for (String item : this.graph.get(vertex))
        {
            for(String toadd : this.graph.get(item))
            {
                if(toadd.compareTo(vertex) != 0 && !neighbors2.contains(toadd))
                {
                    neighbors2.add(toadd);
                }
            }
        }
        return neighbors2;
    }
      
    /**
     * Get the mirrors of a vertex
     * @param vertex
     * @return a list of vertex that are mirrors of the parameter
     */
    public List<String> mirror(String vertex)
    {
        List<String> result = new LinkedList();
        for(String item : getNeighborsOfNeighbors(vertex))
        {
            List<String> toCheck = this.graph.get(vertex);
            toCheck.removeAll(this.graph.get(item));
            if(isComplete(toCheck))
            {
                result.add(item);
            }
        }
        return result;
    }

    public String findMaxDegreeVertex()
    {

        return null;
    }

    public int getNeighbourEdgeNumber(String vertex)
    {

        return 0;
    }

    public int MIS(){

        return 0;
    }
}
