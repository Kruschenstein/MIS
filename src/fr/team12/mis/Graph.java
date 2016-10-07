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
                Explored = ExploreNeighboors(Vertex, Explored);
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
    private List ExploreNeighboors(String vertex, List<String> Explored)
    {
        List<String> neighboors = graph.get(vertex);
        Explored.add(vertex);
        for(String item : neighboors)
        {     
            if(!Explored.contains(item))
            {
                ExploreNeighboors(item, Explored);
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
            List<String> neighboors = result.graph.get(item);
            for(String remove : keysRm)
            {
                neighboors.remove(remove);           
            }   
            result.graph.replace(item, neighboors);
        }
        return result;
    }
    
    public Set<String> getNeighborsWithVertex(String vertex)
    {
        
        return null;
    }

    public Graph fold(String vertex)
    {

        return null;
    }

    public String get2degreeVertex()
    {

        return null;
    }

    public Set<String> mirror(String vertex)
    {

        return null;
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
