import org.example.Week4;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.transform.Result;

public class Week4Test {
    @Test
    public void testMax2Int1(){
        int actualResult = Week4.max2Int(2,5);
        int expectedResult = 5;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMax2Int2(){
        int actualResult = Week4.max2Int(2,-1);
        int expectedResult = 2;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMax2Int3(){
        int actualResult = Week4.max2Int(-2,0);
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMax2Int4(){
        int actualResult = Week4.max2Int(-2,-5);
        int expectedResult = -2;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMax2Int5(){
        int actualResult = Week4.max2Int(0,5);
        int expectedResult = 5;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMinArray1() {
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int actualResult = Week4.minArray(array1);
        int expectedResult = 1;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMinArray2() {
        int[] array2 = {-5, -10, -3, -1};
        int actualResult = Week4.minArray(array2);
        int expectedResult = -10;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMinArray3() {
        int[] array3 = {100, 100, 100, 100};
        int actualResult = Week4.minArray(array3);
        int expectedResult = 100;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMinArray4() {
        int[] array4 = {0};
        int actualResult = Week4.minArray(array4);
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMinArray5() {
        int[] array5 = {7, 2, 8, 4, 1, 6};
        int actualResult = Week4.minArray(array5);
        int expectedResult = 1;
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testCalculateBMI1() {
        String actualResult = Week4.calculateBMI(47, 1.72);
        String expectedResult = "Thiếu cân";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalculateBMI2() {
        String actualResult = Week4.calculateBMI(60, 1.72);
        String expectedResult = "Bình thường";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalculateBMI3() {
        String actualResult = Week4.calculateBMI(80, 1.72);
        String expectedResult = "Thừa cân";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalculateBMI4() {
        String actualResult = Week4.calculateBMI(100, 1.72);
        String expectedResult = "Béo phì";
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalculateBMI5() {
        String actualResult = Week4.calculateBMI(30, 1.72);
        String expectedResult = "Thiếu cân";
        Assert.assertEquals(expectedResult, actualResult);
    }
} 