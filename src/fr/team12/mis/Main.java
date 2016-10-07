package fr.team12.mis;

public class Main
{
    public static void main(String... args)
    {
        Either<Graph, String> result = GraphFactory.generateFromFile("graphe.graphe");
        if (result.getLeft() != null)
            System.out.println(result.getLeft());

        else
            System.out.println(result.getRight());
    }
}
