package fr.team12.mis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph
{
    private Map<String, List<String>> graph;

    public Graph(Map<String, List<String>> graph)
    {
        this.graph = graph;
    }

    public Graph getConnexe()
    {

        return null;
    }

    public Graph deprivateOf(Graph graph)
    {

        return null;
    }

    // N[v]
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
