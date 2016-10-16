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
    public Graph(String vortex)
    {
        Map<String, List<String>> m = new HashMap<String, List<String>>();
        m.put(vortex, new LinkedList<String>());
        this.graph = m;
    }

    public Graph(Graph graphToCopy)
    {
        this.graph = new HashMap<String, List<String>>();
        for (Map.Entry<String, List<String>> item: graphToCopy.graph.entrySet())
        {
            this.graph.put(new String(item.getKey()),
                           new LinkedList<String>(item.getValue()));
        }
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
            if (entry.getValue().isEmpty())
            {
                Map <String, List<String>> component =
                        new HashMap<String, List<String>>();
                component.put(Vertex,graph.get(Vertex));
                return new Graph(component);
            }
            else
            {
                Explored = ExploreNeighbors(Vertex, Explored);
                if (Explored.size() == graph.size())
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
    private List<String> ExploreNeighbors(String vertex, List<String> Explored)
    {
        List<String> neighbors = graph.get(vertex);
        Explored.add(vertex);
        for (String item : neighbors)
        {
            if (!Explored.contains(item))
            {
                ExploreNeighbors(item, Explored);
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
        Graph result = new Graph(this);
        Set<String> keysRm = removeGraph.graph.keySet();
        for (String item : keysRm)
        {
            result.graph.remove(item);
        }
        Set<String> keysRes = result.graph.keySet();
        for (String item : keysRes)
        {
            for (String remove : keysRm)
            {
                result.getNeighbors(item).remove(remove);
            }
        }
        return result;
    }


    /**
     * Return N(vertex)
     * @return N(vertex)
     */
    public List<String> getNeighbors(String vertex)
    {
        return graph.get(vertex);
    }

    /**
     * Return N[vertex]
     * @return N[vertex]
     */
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

    /**
     * Make the two neighbors of vertex to "fold" into one new vertex that will
     * replace vertex and his two neighbors
     * @param vertex vertex to fold
     * @return a new graph with the new folded vertex.
     */
    public Graph fold2(String vertex)
    {
        List<String> neighbors = getNeighbors(vertex);
        List<String> newNeighbors = new LinkedList<String>();
        String newKey = "";
        Graph result = new Graph(this);
        for (String item : neighbors)
        {
            for (String toadd : getNeighbors(item))
            {
                if (toadd.compareTo(vertex) != 0 && !newNeighbors
                    .contains(toadd))
                {
                    newNeighbors.add(toadd);
                }
            }
            newKey = newKey.concat(item);
            newKey = newKey.concat(",");
            result.graph.remove(item);
        }
        result.graph.remove(vertex);

        for (String item : newNeighbors)
        {
            for (String rm : neighbors)
            {
                if (result.graph.get(item).contains(rm))
                {
                    result.graph.get(item).remove(rm);
                }
            }
            result.graph.get(item).add(newKey);
        }
        result.graph.put(newKey, newNeighbors);
        return result;
    }

    /**
     * Return first vertex that have exactly 2 neighbors. If such vertex doesn't
     * exist, return null.
     * @return vertex that have exactly 2 neighbors, null otherwise.
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

    /**
     * Check if a list of vertex is a clique, in other words, if all the vertex
     * of the list have for neighbors all the other vertex of the list
     * @param vertexs list of vertexs to check
     * @return true if vertexs is a clique, false if not
     */
    private boolean isComplete(List<String> vertexs)
    {
        for (String item : vertexs)
        {
            for (String toTest : vertexs)
            {
                if (item.compareTo(toTest) != 0 && !getNeighbors(item)
                    .contains(toTest))
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Return the neighbors of neighbors of the vertex in parameters
     * ( N²(vertex) )
     * @param vertex
     * @return the list equals to N²(vertex)
     */
    private List<String> getNeighborsOfNeighbors(String vertex)
    {
        List<String> neighbors2 = new LinkedList<String>();
        List<String> neighbors = getNeighbors(vertex);
        for (String item : neighbors)
        {
            for (String toadd : getNeighbors(item))
            {
                if (toadd.compareTo(vertex) != 0 && !neighbors.contains(toadd)
                    && !neighbors2.contains(toadd))
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
        List<String> result = new LinkedList<String>();
        for (String item : getNeighborsOfNeighbors(vertex))
        {
            List<String> toCheck = new LinkedList<String>(getNeighbors(vertex));
            toCheck.removeAll(getNeighbors(item));
            if (isComplete(toCheck))
            {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Return the vertex which have the greatest degree in the vertices set.
     *
     * @return The vertex which have the greatest degree in the vertices set.
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

    public String findVertexContainer()
    {
        for (String item : this.graph.keySet())
        {
            for (String neighbors : getNeighbors(item))
            {
                if (getNeighborsWithVertex(item).containsAll(
                        getNeighborsWithVertex(neighbors)))
                {
                    return item;
                }
            }

        }
        return null;
    }

    /**
     * Return the number of edge between two vertices of N(vertex).
     * @return The number of edge between two vertices of N(vertex).
     */
    public int getNeighbourEdgeNumber(String vertex)
    {
        Set<UnorderedTuple<String>> visitedNode =
            new HashSet<UnorderedTuple<String>>();

        for (String vertexNeighbour: getNeighbors(vertex))
        {
            if (vertex.equals(vertexNeighbour))
                continue;

            for (String v: getNeighbors(vertexNeighbour))
            {
                if (!v.equals(vertex) &&
                    getNeighbors(vertex).contains(v) &&
                    !visitedNode.contains(new UnorderedTuple<String>(
                                              vertexNeighbour, v)))
                {
                    visitedNode.add(new UnorderedTuple<String>(
                                              vertexNeighbour, v));
                }
            }
        }
        return visitedNode.size();
    }

    public String findFoldable()
    {
        for (Map.Entry<String, List<String>> vertex: graph.entrySet())
        {
            if (vertex.getValue().size() == 2 &&
                    getNeighbourEdgeNumber(vertex.getKey()) == 0)
                return vertex.getKey();
        }
        return null;
    }

    public int MIS()
    {
        Graph C ;
        String S;
        if (this.graph.size() <= 1)
        {
            return this.graph.size();
        }
        else if ((C = getConnexe()) != null)
        {
            return C.MIS() + ((this.deprivateOf(C)).MIS());
        }
        else if ((S = this.findVertexContainer()) != null)
        {
            Graph a = new Graph(this);
            a = a.deprivateOf(new Graph(S));
            return a.MIS();
        }
        else if ((S = this.findFoldable()) != null)
        {
            return 1+(this.fold2(S).MIS());
        }
        else
        {
            S = this.findMaxDegreeMaxEdgesVertex();
            Graph a = new Graph(this);
            a = a.deprivateOf(new Graph(S));
            for (String item : this.mirror(S))
            {
                a = a.deprivateOf(new Graph(item));
            }
            Graph b = new Graph(this);
            for (String item : b.getNeighborsWithVertex(S))
            {
                b = b.deprivateOf(new Graph(item));
            }
            return Math.max(a.MIS(), b.MIS());
        }
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


    /**
     * Returns a vertex with N(vertex) max for which the number of edges between
     * two vertices is maximum.
     * @param
     * @return The number of edge between two vertices of N(vertex).
     */
    public String findMaxDegreeMaxEdgesVertex()
    {
        // Find the vertices with N(vertex) max.
        String MDMEVertex = "";
        int maxValue = -1;
        Set<String> maxDegreeVertices = new HashSet<String>();
        for (Map.Entry<String, List<String>> vertex: graph.entrySet())
        {
            if (vertex.getValue().size() > maxValue)
            {
                maxValue = vertex.getValue().size();
                maxDegreeVertices.clear();
                maxDegreeVertices.add(vertex.getKey());
            }
            else if (vertex.getValue().size() == maxValue)
            {
                maxDegreeVertices.add(vertex.getKey());
            }
        }

        // Find which vertex has the highest number of edges between two
        // vertices.
        maxValue = -1;
        int tmp = -1;
        for (String vertex: maxDegreeVertices)
        {
            tmp = getNeighbourEdgeNumber(vertex);
            if (tmp > maxValue)
            {
                maxValue = tmp;
                MDMEVertex = vertex;
            }
        }
        return MDMEVertex;
    }
}
