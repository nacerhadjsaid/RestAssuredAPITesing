package utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {

    @DataProvider(name = "userData")
    public static Object[][] getData() throws Exception {

        String line;
        List<Object[]> data = new ArrayList<Object[]>();

        BufferedReader br = new BufferedReader(
                new FileReader("src/test/resources/testdata/ids.csv")
        );

        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            data.add(parts);
        }

        br.close();
        return data.toArray(new Object[0][]);
    }
}
