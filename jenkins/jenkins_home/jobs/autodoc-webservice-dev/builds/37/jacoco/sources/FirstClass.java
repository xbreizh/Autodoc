public class FirstClass {


    public FirstClass() {
    }

    public static void main(String[] args) {
        new FirstClass().sayHello("Bob");
    }

    public String sayHello(String person){
        return "Hello "+person+"!";
    }

}
