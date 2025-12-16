import java.io.*;
import java.util.*;

public class CSVDataset implements Dataset {

    private Map<String, List<Double>> data = new LinkedHashMap<>();

    public CSVDataset(String file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] names = br.readLine().split(",");

        for (String n : names)
            data.put(n.trim(), new ArrayList<>());

        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            for (int i = 0; i < values.length; i++) {
                data.get(names[i].trim())
                    .add(Double.parseDouble(values[i]));
            }
        }
        br.close();
    }

    public List<String> getStudentNames() {
        return new ArrayList<>(data.keySet());
    }

    public List<Double> getMarks(String name) {
        return data.get(name);
    }
}
