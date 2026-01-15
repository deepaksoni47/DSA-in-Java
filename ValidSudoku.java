import java.util.HashSet;

class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];

                if (val == '.')
                    continue;

                int boxIndex = (i / 3) * 3 + (j / 3);

                if (!rows[i].add(val))
                    return false;
                if (!cols[j].add(val))
                    return false;
                if (!boxes[boxIndex].add(val))
                    return false;
            }
        }

        return true;
    }

    public boolean isValidSudokubest(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;

                int num = c - '1'; // 0 to 8
                int mask = 1 << num;
                int boxIndex = (i / 3) * 3 + (j / 3);

                if ((rows[i] & mask) != 0)
                    return false;
                if ((cols[j] & mask) != 0)
                    return false;
                if ((boxes[boxIndex] & mask) != 0)
                    return false;

                rows[i] |= mask;
                cols[j] |= mask;
                boxes[boxIndex] |= mask;
            }
        }
        return true;
    }
}
