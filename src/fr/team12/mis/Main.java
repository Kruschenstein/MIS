package fr.team12.mis;

public class Main
{
    private static final String graphResult = "8\n" +
        "0: [ 1 2 3 4 ]\n" +
        "1: [ 0 2 5 6 ]\n" +
        "2: [ 0 1 3 6 ]\n" +
        "3: [ 0 2 4 7 ]\n" +
        "4: [ 0 3 5 7 ]\n" +
        "5: [ 1 4 6 7 ]\n" +
        "6: [ 1 2 5 7 ]\n" +
        "7: [ 3 4 5 6 ]\n";

    public static boolean testGraphFactory()
    {
        boolean ret = false;
        Either<Graph, String> result = GraphFactory.generateFromFile(
            "graphe.graphe");

        if (result.getLeft() != null)
            System.out.println(result.getLeft());

        else
        {
            System.out.println(result.getRight());
            result.toString().equals(graphResult);
        }
        return ret;
    }

    public static void main(String... args)
    {
        testGraphFactory();
        Either<Graph, String> result = GraphFactory.generateFromFile(
            "graphe.graphe");

        if (result.getLeft() != null)
        {
            System.out.println(result.getLeft());
            return;
        }

        Graph graph = result.getRight();
        System.out.println(graph.getNeighbourEdgeNumber("7"));
    }
}
