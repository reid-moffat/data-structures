package ca.reidmoffat;

/**
 * Searching algorithms
 */
public class Searching {
    public static <T> int linearSearch(T[] array, T key) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(T[] array, T key) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid].equals(key)) {
                return mid;
            } else if (array[mid].compareTo(key) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int recursiveBinarySearch(T[] array, T key) {
        return recursiveBinarySearchHelper(array, key, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int recursiveBinarySearchHelper(T[] array, T key, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;
        if (array[mid].equals(key)) {
            return mid;
        } else if (array[mid].compareTo(key) < 0) {
            return recursiveBinarySearchHelper(array, key, mid + 1, high);
        } else {
            return recursiveBinarySearchHelper(array, key, low, mid - 1);
        }
    }
}
