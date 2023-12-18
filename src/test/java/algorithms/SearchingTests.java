package algorithms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ca.reidmoffat.Searching;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SearchingTests {

    interface SearchAlgo<T, K> {
        public int run(Integer[] array, Integer target);
    }

    private record SearchTestCase<T, K>(String name, T array, K target, int expected) {}

    private static final ArrayList<SearchTestCase<Integer[], Integer>> testCases = new ArrayList<>();

    @BeforeAll
    public static void setupTestCases() throws IOException {

        final HashSet<String> tcKeys = new HashSet<>(Arrays.asList("Name", "Array", "Target", "Expected"));
        final JSONArray tcFile = (JSONArray) JSONValue.parse(Files.readString(Paths.get("./src/test/java/data/Searching.json")));

        for (int i = 0; i < tcFile.size(); ++i) {
            final JSONObject tc = (JSONObject) tcFile.get(i);

            if (!tc.keySet().equals(tcKeys)) {
                throw new IllegalStateException("Test case #" + (i + 1) + " (value: " + tc + ") is invalid; must have " +
                        "the following keys: " + tcKeys + ", but it has " + tc.keySet());
            }

            // Extract data from JSON & create test case with it
            final String name = (String) tc.get("Name");
            final int target = Math.toIntExact((Long) tc.get("Target"));
            final int expected = Math.toIntExact((Long) tc.get("Expected"));

            final JSONArray jsonArray = (JSONArray) tc.get("Array"); // Need loop to convert JSONArray -> array
            final Integer[] array = new Integer[jsonArray.size()];
            for (int j = 0; j < jsonArray.size(); ++j) array[j] = (int) jsonArray.get(j);

            testCases.add(new SearchTestCase<>(name, array, target, expected));
        }
    }

    private void runAllTCs(String searchAlgorithm, SearchAlgo<Integer[], Integer> algo) {

        System.out.println("\n====================================================");
        System.out.println("Running all searching test cases for " + searchAlgorithm + "...");
        System.out.println("====================================================");

        for (int i = 0; i < testCases.size(); ++i) {
            var testCase = testCases.get(i);
            System.out.println("\nRunning TC #" + i + ": " + testCase.name());
            System.out.println("Array: " + Arrays.toString(testCase.array));
            System.out.println("Target value: " + testCase.target);
            System.out.println("Expected position (-1 = not found): " + testCase.expected);

            int result = algo.run(testCase.array, testCase.target);

            if (testCase.expected() != result) {
                String message = "Expected " + testCase.target + " to be in position " + testCase.expected() + " in array "
                        + Arrays.toString(testCase.array) + ", but " + searchAlgorithm + "() returned " + result;
                throw new AssertionError(message);
            }
            System.out.println("Test case passed successfully");
        }
    }

    @Test
    public void LinearSearch() {
        runAllTCs("LinearSearch", Searching::linearSearch);
    }

    @Test
    public void BinarySearch() {
        runAllTCs("BinarySearch", Searching::binarySearch);
    }

    @Test
    public void RecursiveBinarySearch() {
        runAllTCs("RecursiveBinarySearch", Searching::recursiveBinarySearch);
    }
}
