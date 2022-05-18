package MergeKSortedLists;

import java.util.ArrayList;
import java.util.List;
import static MergeKSortedLists.Solution.mergeKLists;

public class Main{
    public static void main(String[] args) {
        List<Integer[]> l = new ArrayList<Integer[]>();

        l.add(new Integer[] { 1, 2, 3 });
        l.add(new Integer[] { 4, 5, 6 });
        l.add(new Integer[] { 7, 8, 9 });

        System.out.println(mergeKLists(l));
    }
}
