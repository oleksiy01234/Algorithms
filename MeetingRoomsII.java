import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsII {

    // WRONG -- NEEDS TO BE FIXED
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        int lastEnd = 0;
        int count = 0;
        for (int[] interval : intervals) {
            if (interval[0] < lastEnd) {
                count++;
                lastEnd = interval[1];
            }
        }

        return count;
    }
}
