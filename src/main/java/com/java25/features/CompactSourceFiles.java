//Run java code snippets without declaring class or packages for quick testing
//Compiler will create it as a FINAL class
//Only one default invisible no-args constructor

int i = 10;
void main() {
    System.out.println("CompactSourceFiles: "+i);
    doSomething();
}
public void doSomething(){
    System.out.println("Do something");
}