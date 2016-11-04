package fr.team12.mis;

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
            System.out.println("result.getLeft()");
            System.out.println(result.getLeft());
            return;
        }

        Graph graph = result.getRight();
        
        System.out.println("graph.MIS():");
        System.out.println(graph.MIS());
    }
}
