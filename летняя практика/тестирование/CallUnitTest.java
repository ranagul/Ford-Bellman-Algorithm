package com.company;

public class CallUnitTest {
    public void CallUnitTest(){
        UnitTest test = new UnitTest();
        if (test.UnitTestMinusCycle1() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }

        test = new UnitTest();
        if (test.UnitTestMinusCycle2() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }

        test = new UnitTest();
        if (test.UnitTestPlusCycle1() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
        test = new UnitTest();
        if (test.UnitTestPlusCycle2() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
        test = new UnitTest();
        if (test.UnitTestAllEdges() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
        test = new UnitTest();
        if (test.UnitTestNoCycle1() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
        test = new UnitTest();
        if (test.UnitTestNoCycle2() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
        test = new UnitTest();
        if (test.UnitTestNoCycle3() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
        test = new UnitTest();
        if (test.UnitTestLargeGraphs() == false) {
            System.out.println("No correct");
        } else {
            System.out.println("Correct");
        }
    }
}
