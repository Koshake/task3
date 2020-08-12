package lesson7.tests;

public class TestClass {
    @BeforeSuite(description = "start test class")
    public static void before() {
        System.out.println("before");
    }

    //@BeforeSuite(description = "start test class")
    //public static void before2() {
    //    System.out.println("before");
    //}

    @Test(priority = 1)
    public static void test1() {
        System.out.println("test1");
    }

    @Test(priority = 3)
    public static void test2() {
        System.out.println("test2");
    }

    @Test(priority = 3)
    public static void test3() {
        System.out.println("test3");
    }

    @Test
    public static void test4() {
        System.out.println("test4");
    }

    @Test(priority = 10)
    public static void test5() {
        System.out.println("test5");
    }

    @AfterSuite(description = "stop test class")
    public static void after() {
        System.out.println("after");
    }
    //@AfterSuite(description = "stop test class")
    //public static void after2() {
    //    System.out.println("after");
    //}
}
