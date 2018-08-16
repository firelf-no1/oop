/**
 * FILE : SimpleSetPerformanceAnalyzer.java
 * WRITER : Itai shopen firelf 021982038
 * DESCRIPTION:
 * has a main method that measures the run-times requested in the
 * “Performance Analysis” section. Implement it as you wish.
 */

import java.util.*;

public class SimpleSetPerformanceAnalyzer {
    private String[] names = {"OPEN_HASH_SET", "CLOSE_HASH_SET", "LINKED_LIST", "TREE_SET",  "HASH_SET"};
    private SimpleSet[] setTypes;


    private static final int OPEN_HASH_SET = 0, CHAINED_HASH_SET = 1,
            LINKED_LIST = 2, TREE_SET = 3, HASH_SET = 4, NUM_OF_SETS = 5;
    private long timeBefore, difference;


    public SimpleSetPerformanceAnalyzer() {
        setTypes = buildSetTypes();

    }

    private SimpleSet[] buildSetTypes() {
        SimpleSet[] setTypes = new SimpleSet[5];
        setTypes[0] = new OpenHashSet();
        setTypes[1] = new ClosedHashSet();
        setTypes[2] = new CollectionFacadeSet(new LinkedList<>());
        setTypes[3] = new CollectionFacadeSet(new TreeSet<>());
        setTypes[4] = new CollectionFacadeSet(new HashSet<>());
        return setTypes;
    }

    private void dataloadToSets(String fileName, SimpleSetPerformanceAnalyzer analyzer) {
        String[] arrayString = Ex3Utils.file2array(fileName);
        for (int i = OPEN_HASH_SET; i < NUM_OF_SETS; i++) {
            timeBefore = System.nanoTime();
            analyzer.dataLoadToSet(arrayString, analyzer.setTypes[i]);
            difference = (System.nanoTime() - timeBefore);
            System.out.println("The time it took to load: " + fileName + " to " + names[i] + " was: " +
                    difference / 1000000 + " [ms]");
        }
    }

    private void containsInSets(SimpleSetPerformanceAnalyzer analyzer, String searchVal, String fileName) {
        for (int i = OPEN_HASH_SET; i < NUM_OF_SETS; i++) {
            timeBefore = System.nanoTime();
            if (analyzer.setTypes[i].contains(searchVal)) {
                difference = (System.nanoTime() - timeBefore);
                System.out.println("The search value \"" + searchVal + "\" was found in: " + names[i] +
                        " at hashSet: " + fileName + " and it took: " + difference + " [ns]");
            } else {
                difference = (System.nanoTime() - timeBefore);
                System.out.println("The search value \"" + searchVal + "\" was not found in: " + names[i] +
                        " at hashSet: " + fileName + " and it took: " + difference + " [ns]");
            }
        }
    }

    private void dataLoadToSet(String[] stringsArray, SimpleSet set) {
        for (String str : stringsArray) {
            set.add(str);
        }
    }

    private void warmUp(SimpleSetPerformanceAnalyzer analyzer, String searchVal) {
        for (int i = OPEN_HASH_SET; i < NUM_OF_SETS; i++) {
            for (int j = OPEN_HASH_SET; j < 100000; j++) {
                analyzer.setTypes[i].contains(searchVal);

            }
        }
    }

    public static void main(String[] arg) {
        SimpleSetPerformanceAnalyzer myHashSet1 = new SimpleSetPerformanceAnalyzer();
        SimpleSetPerformanceAnalyzer myHashSet2 = new SimpleSetPerformanceAnalyzer();
        myHashSet1.dataloadToSets("data1.txt", myHashSet1);
        myHashSet1.warmUp(myHashSet1, "hi");
        myHashSet1.containsInSets(myHashSet1, "hi", "data1.txt");
        myHashSet1.warmUp(myHashSet1, "-13170890158");
        myHashSet1.containsInSets(myHashSet1, "-13170890158", "data1.txt");
        myHashSet2.dataloadToSets("data2.txt", myHashSet2);
        myHashSet2.warmUp(myHashSet2, "23");
        myHashSet2.containsInSets(myHashSet2, "23", "data2.txt");
        myHashSet2.warmUp(myHashSet2, "hi");
        myHashSet2.containsInSets(myHashSet2, "hi", "data2.txt");
    }
}
