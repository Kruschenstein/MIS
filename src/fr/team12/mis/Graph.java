package fr.team12.mis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Set;
import java.util.HashSet;

public class Graph
{
    private Map<String, List<String>> graph;

    public Graph(Map<String, List<String>> graph)
    {
        this.graph = graph;
    }

    /**
     * Get a connected componenent of the graph, if the graph extracted is equal
     * to this, return null.
     * @return a connected graph or null if the graph dont have connected
     *         componenet or is empty.
     */
    public Graph getConnexe()
    {

        List<String> Explored = new LinkedList<String>();
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
                    Map <String, List<String>> component =
                        new HashMap<String, List<String>>();
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
    private List<String> ExploreNeighboors(String vertex, List<String> Explored)
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
     * Create a new graph by removing all the vertex contained in removeGraph of
     * this.graph
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

    // N[v]
    public Set<String> getNeighborsWithVertex(String vertex)
    {
        Set<String> ret = new HashSet<String>();
        if (graph.containsKey(vertex))
        {
            ret = new HashSet<String>(graph.get(vertex));
            ret.add(vertex);
        }
        return ret;
    }

    public Graph fold(String vertex)
    {

        return null;
    }

    /**
    ** Return first vertex that have exactly 2 neighbors. If such vertex doesn't
    ** exist, return null.
    ** @return vertex that have exactly 2 neighbors, null otherwise.
    */
    public String get2degreeVertex()
    {
        for (Map.Entry<String, List<String>> vertex: graph.entrySet())
        {
            if (vertex.getValue().size() == 2)
                return vertex.getKey();
        }
        return null;
    }

    public Set<String> mirror(String vertex)
    {

        return null;
    }

    /**
    ** Return the vertex which have the greatest degree in the vertices set.
    **
    ** @return The vertex which have the greatest degree in the vertices set.
    */
    public String findMaxDegreeVertex()
    {
        String maxDegreeVertex = "";
        int maxValue = -1;
        for (Map.Entry<String, List<String>> vertex: graph.entrySet())
        {
            if (vertex.getValue().size() > maxValue)
            {
                maxValue = vertex.getValue().size();
                maxDegreeVertex = vertex.getKey();
            }
        }

        return maxDegreeVertex;
    }

    // T
    public int getNeighbourEdgeNumber(String vertex)
    {

        return 0;
    }

    public int MIS()
    {

        return 0;
    }

    @Override
    public String toString()
    {
        StringBuilder ret = new StringBuilder(graph.size() + "\n");
        for (Map.Entry<String, List<String>> entry: graph.entrySet())
        {
            ret.append(entry.getKey() + ": [ ");
            for (String vertex: entry.getValue())
            {
                ret.append(vertex + " ");
            }
            ret.append("]\n");
        }
        return ret.toString();
    }
}
