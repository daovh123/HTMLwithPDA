public class Week4 {
    /**
     * solon hon.
     * @param a la so.
     * @param b la so.
     * @return so lon.
     */
    public static int max2Int(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * min mang.
     * @param array la mang.
     * @return so be nhat.
     */
    public static int minArray(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * BMI.
     * @param weight la can nang.
     * @param height la chieu cao.
     * @return BMI.
     */
    public static String calculateBMI(double weight, double height) {
        double BMI = Math.round(weight / (height * height) * 10.0) / 10.0;
        if (BMI < 18.5) {
            return "Thiếu cân";
        }
        if (BMI <= 22.9) {
            return "Bình thường";
        }
        if (BMI <= 24.9) {
            return "Thừa cân";
        }
        return "Béo phì";
    }
}

