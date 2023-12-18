package algorithms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ca.reidmoffat.Searching;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchingTests {

    public record SearchTestCase<T, K>(String name, T input1, K input2, int expected) {}

    private static final ArrayList<SearchTestCase<Integer[], Integer>> testCases = new ArrayList<>();

    @BeforeAll
    public static void setupTestCases() throws IOException {

        JSONArray a = (JSONArray) JSONValue.parse(Files.readString(Paths.get("./src/test/java/data/Searching.json")));

        System.out.println("Size: " + a.size() + " First element: " + ((JSONObject) a.get(0)).keySet());

        var tc = new SearchTestCase<>("Empty array & ", new Integer[] { }, 1, -1);
        testCases.add(tc);

        tc = new SearchTestCase<>("Empty array", new Integer[] { }, -1, -1);
        testCases.add(tc);

        tc = new SearchTestCase<>("Empty array", new Integer[] { }, 0, -1);
        testCases.add(tc);
    }

    @Test
    public void BinarySearch() {

        System.out.println("\n====================================================");
        System.out.println("Running all searching test cases for BinarySearch...");
        System.out.println("====================================================");

        for (int i = 0; i < testCases.size(); ++i) {
            var testCase = testCases.get(i);
            System.out.println("\nRunning TC #" + i + ": " + testCase.name());

            int result = Searching.binarySearch(testCase.input1(), testCase.input2());

            if (testCase.expected() != result) {
                String message = "Expected " + testCase.input2() + " to be in position " + testCase.expected() +
                        " in array " + Arrays.toString(testCase.input1()) + ", but BinarySearch() returned " + result;
                throw new AssertionError(message);
            }
            System.out.println("Test case passed successfully");
        }
    }
}
