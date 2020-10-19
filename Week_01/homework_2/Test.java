public class Test {

    public static void main(String[] args) throws Exception {
        XlassLoader xlassLoader = new XlassLoader();
        Class<?> helloClass =  xlassLoader.loadClass("Hello");
        helloClass.getMethod("hello").invoke(helloClass.newInstance());
    }

}