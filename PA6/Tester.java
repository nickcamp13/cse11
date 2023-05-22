public class Tester {
    public static void main(String[] args) {
        Post p1 = new Post("first", "first post", new User());
        Post p2 = new Post("reply to p1", p1, new User(), "second");
        Post p3 = new Post("reply to p2", p2, new User(), "third");

        p3.printThread();
    }
}