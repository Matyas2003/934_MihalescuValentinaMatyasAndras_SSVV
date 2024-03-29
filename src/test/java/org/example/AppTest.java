package org.example;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void Test() {
        // Create an instance of TestClass
        TestClass testClass = new TestClass();

        // Call the setup method to initialize the service
        TestClass.setup();

        // Call each test method individually
        testClass.addStudent_ID_Zero();
        testClass.addStudent_ID_MaxInt();
        testClass.addStudent_ID_MinusOne();
        testClass.addStudent_ID_One();
        testClass.addStudent_ID_MaxIntMinusOne();
        testClass.addStudent_ID_MaxIntPlusOne();
        testClass.addStudent_Name_AaA();
        testClass.addStudent_Name_AaA_A();
        testClass.addStudent_Name_A();
        testClass.addStudent_Name_Empty();
        testClass.addStudent_Name_1a();
        testClass.addStudent_Name_1();
        testClass.addStudent_Group_100();
        testClass.addStudent_Group_101();
        testClass.addStudent_Group_999();
        testClass.addStudent_Group_998();
        testClass.addStudent_Group_99();
        testClass.addStudent_Group_1000();
        testClass.addStudent_Group_Zero();
        testClass.addStudent_Email_A_Valid();
        testClass.addStudent_Email_AtA_Invalid();
        testClass.addStudent_Email_AAt_Invalid();
        testClass.addStudent_Email_A_Invalid();
        testClass.addStudent_Email_Empty();
    }
}