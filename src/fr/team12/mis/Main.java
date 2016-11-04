package fr.team12.mis;

/**
 * Main program.<br>
 * Compute the maximum stable of graph given in parameter.<br>
 * By default the file where the graph is taken is named "graphe.graphe".<br>
 */
public class Main
{
    public static void main(String... args)
    {
        String filename = "graphe.graphe";
        if (!args[0].equals("${arg0}"))
            filename = args[0];

        Either<Graph, String> result = GraphFactory.generateFromFile(filename);

        if (result.getLeft() != null)
        {
            System.out.println("Error:");
            System.out.println(result.getLeft());
            return;
        }

        Graph graph = result.getRight();
        System.out.println(graph.MIS());
    }
}
