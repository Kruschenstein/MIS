package fr.team12.mis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;

import java.util.Map;
import java.util.HashMap;

/**
 * Create new graph.
 */
public class GraphFactory
{
    /**
     * Generate a graph from file.
     *
     * @param path The file were the graph is stored (in .graphe format).
     * @return either the graph either an error list.
     */
    public static Either<Graph, String> generateFromFile(String path)
    {
        StringBuilder err = new StringBuilder();
        Either<Graph, String> ret = new Either<Graph, String>();
        Map<String, List<String>> graph = new HashMap<String, List<String>>();
        int lineCounter = 1;
        try
        {
            Scanner scanner = new Scanner(new File(path));
            int verticesNumber = 0;
            if (scanner.hasNext())
                verticesNumber = scanner.nextInt();

            while (verticesNumber > 0)
            {
                String line = scanner.nextLine();
                ++lineCounter;
                if (!line.isEmpty() && !line.equals("\n"))
                {
                    String[] lineWithoutColon = line.split(":");
                    if (lineWithoutColon.length == 2)
                    {
                        graph.put(lineWithoutColon[0].trim(),
                                  getList(lineWithoutColon[1]));
                        --verticesNumber;
                    }
                    else
                        throw new Exception("malformed line " + lineCounter +
                            " -- \"" + line + "\"");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            err.append("[err]: File " + path + " not found.\n");
        }
        catch (Exception error)
        {
            err.append("[err]: " + error.getMessage() + " -- LINE=" +
                       lineCounter + ".\n");
        }

        String finalErrorMessages = err.toString();
        if (!finalErrorMessages.isEmpty())
            ret.setLeft(finalErrorMessages);
        else
            ret.setRight(new Graph(graph));
        return ret;
    }

    /**
     * Return a list of neighbors from a line in .graphe file.
     *
     * @param fullLine a line of a .graphe file.
     * @return the list of neighbors of a vertex.
     */
    private static List<String> getList(String fullLine) throws Exception
    {
        List<String> ret = new LinkedList<String>();
        String line = fullLine.trim();
        if (line.charAt(0) != '[')
            throw new Exception("Malformed line `[' isn't set -- \"" +
                                fullLine + "\"");

        if (line.charAt(line.length() -1) != ']')
            throw new Exception("Malformed line `]' isn't set -- \"" +
                                fullLine + "\"");

        Scanner scanner = new Scanner(line.substring(1, line.length() -1));
        scanner.useDelimiter(",");
        while (scanner.hasNext())
        {
            String vertexValue = scanner.next().trim();
            if (vertexValue.isEmpty())
                throw new Exception("Vertex value is empty -- \"" +
                                    fullLine + "\"");
            ret.add(vertexValue);
        }
        return ret;
    }
}
