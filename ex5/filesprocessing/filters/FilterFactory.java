package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneErrors;
import java.util.Arrays;

/**
 * factory for the filters according to the factory design.
 */
public class FilterFactory {

    private static final String HASH = "#",	NOT = "NOT";

    /**
     * @param filterString string we need to convert to Filter.
     * @return the Filter the string represent.
     * @throws TypeOneErrors
     */
    public static FilterInterface createFilter(String filterString) throws TypeOneErrors {
        boolean isNegative = false;
        FilterInterface filter = null;
        String[] filterArray;
        int hashIndex = filterString.indexOf(HASH);
        filterArray = filterString.split(HASH);
        if (filterArray[0].equals("file") || filterArray[0].equals("contains") || filterArray[0].equals("prefix")
                || filterArray[0].equals("suffix")) {
            String[] testArray = new String[2];
            testArray[0] = filterArray[0];
            testArray[1] = filterString.substring(hashIndex + 1, filterString.length());
            if (filterArray.length > 2) {
                int endHashIndex = filterString.lastIndexOf(HASH);
                if (filterArray[filterArray.length - 1].equals(NOT)) {
                    String[] notTestArray = Arrays.copyOf(testArray, 3);
                    notTestArray[1] = filterString.substring(hashIndex + 1, endHashIndex);
                    notTestArray[2] = filterArray[filterArray.length - 1];
                    testArray = notTestArray;
                }
            }
            filterArray = testArray;
        }
        //check if there is a NOT in the end of the string
        int arrLength = filterArray.length;
        if (arrLength > 1){
            if(filterArray[arrLength - 1].equals(NOT)) {
                isNegative = true;
                arrLength -= 1;
            }
        }
        //check all cases given and send object according to what needed.
        switch (filterArray[0]) {
            case "all":
                if (arrLength != AllFilter.argumentNumber) { throw new TypeOneErrors(); }
                filter = new AllFilter();
                break;
            case "between":
                if (arrLength != BetweenFilter.argumentNumber) { throw new TypeOneErrors(); }
                //check if the first value is smaller. if not filter remain null and if the val is not negative
                try { filter = new BetweenFilter(Double.valueOf(filterArray[1]), Double.valueOf(filterArray[2])); }
                catch (TypeOneErrors e) { throw new TypeOneErrors(); }
                break;
            case "contains":
                if (arrLength != ContainsFilter.argumentNumber) { throw new TypeOneErrors(); }
                filter = new ContainsFilter(filterArray[1]);
                break;
            case "executable":
                if (arrLength != ExecutableFilter.argumentNumber) { throw new TypeOneErrors(); }
                //checks if the val yes or no is spelled correctly
                try { filter = new ExecutableFilter(filterArray[1]); }
                catch (TypeOneErrors e) { throw new TypeOneErrors(); }
                break;
            case "greater_than":
                if (arrLength != GraterThanFilter.argumentNumber) { throw new TypeOneErrors(); }
                //checks if the val is not negative
                try{ filter = new GraterThanFilter(Double.valueOf(filterArray[1])); }
                catch (TypeOneErrors e){ throw new TypeOneErrors(); }
                break;
            case "hidden":
                if (arrLength != HiddenFilter.argumentNumber) { throw new TypeOneErrors(); }
                //checks if the val yes or no is spelled correctly
                try { filter = new HiddenFilter(filterArray[1]); }
                catch (TypeOneErrors e) { throw new TypeOneErrors(); }
                break;
            case "file":
                if (arrLength != NameFilter.argumentNumber) { throw new TypeOneErrors(); }
                filter = new NameFilter(filterArray[1]);
                break;
            case "prefix":
                if (arrLength != PrefixFilter.argumentNumber) { throw new TypeOneErrors(); }
                filter = new PrefixFilter(filterArray[1]);
                break;
            case "smaller_than":
                if (arrLength != SmallerThenFilter.argumentNumber) { throw new TypeOneErrors(); }
                //checks if the val is not negative
                try{ filter = new SmallerThenFilter(Double.valueOf(filterArray[1])); }
                catch (TypeOneErrors e) { throw new TypeOneErrors(); }
                break;
            case "suffix":
                if (arrLength != SuffixFilter.argumentNumber) { throw new TypeOneErrors(); }
                filter = new SuffixFilter(filterArray[1]);
                break;
            case "writable":
                if (arrLength != WritableFilter.argumentNumber) { throw new TypeOneErrors(); }
                //checks if the val yes or no is spelled correctly
                try { filter = new WritableFilter(filterArray[1]); }
                catch (TypeOneErrors e) { throw new TypeOneErrors(); }
                break;
            default:
                throw new TypeOneErrors();
        }
        if(isNegative && filter != null) { filter = new NotFilter(filter); }
        return filter;
    }
}