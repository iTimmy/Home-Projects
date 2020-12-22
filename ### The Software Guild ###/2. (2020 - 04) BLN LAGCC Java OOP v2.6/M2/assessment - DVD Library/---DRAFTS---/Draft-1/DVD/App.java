import controller.*;

public class App {
    public static void main(String[] args) throws Exception {
        //AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        //appContext.scan("com.sg.booktracker");
        //appContext.refresh();

       // ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        //DVDController controller = appContext.getBean("controller", DVDController.class);
        DVDController controller = new DVDController();
        controller.run();
    }
}