class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 0;

        for (int diff : diffs) {
            right = Math.max(right, diff);
        }

        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canSolve(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long total = times[0];

        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                total += times[i];
            } else {
                total += (long) (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            }

            if (total > limit) return false;
        }

        return true;
    }
}