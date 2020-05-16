package ru.otus.lesaoni;

public class ModelTest {

    @Before
    public static void before() {
        System.out.println("Before start");
    }

    @Test
    public static void throwSomething() throws Exception {
        throw new Exception();
    }

    @Test
    public static void testSum() {
        Model model = new Model(1, 2);
        System.out.println(model.getSum());
    }

    @Test
    public static void testMultiply() {
        Model model = new Model(5, 2);
        System.out.println(model.getMultiply());
    }

    @After
    public static void after() {
        System.out.println("After start");
    }

}
