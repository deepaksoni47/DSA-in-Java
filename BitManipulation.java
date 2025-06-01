public class BitManipulation {
    public static void oddEven(int n) {
        if ((n & 1) == 0) {
            System.out.println("even number");
        } else {
            System.out.println("odd number");
        }
    }

    public static int getIBit(int n, int i) {
        int bitmask = 1 << i;
        if ((n & bitmask) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int setIBit(int n, int i) {
        int bitmask = 1 << i;
        return n | bitmask;
    }

    public static int clearIBit(int n, int i) {
        int bitmask = ~(1 << i);
        return n & bitmask;
    }

    public static int updateIBit(int n, int i, int newBit) {
        if (newBit == 0) {
            return clearIBit(n, newBit);
        } else {
            return setIBit(n, newBit);
        }
    }

    public static int updateIBitv2(int n, int i, int newBit) {
        int num = clearIBit(n, newBit);
        int bitmask = newBit << i;
        return num | bitmask;
    }

    public static int clearLastIBits(int n, int i) {
        int bitmask = (~0) << i;
        return n & bitmask;
    }

    public static int clearBitRange(int n, int i, int j) {
        int a = (~0) << (j + 1);
        int b = (1 << i) - 1;
        int bitmask = a | b;
        return n & bitmask;
    }

    public static boolean checkPowerTwo(int n) {
        return ((n & (n - 1)) == 0);
    }

    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1) != 0) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // System.out.println(6 & 1);
        oddEven(57);
    }
}
