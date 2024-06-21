package org.lab05;

public class BookManagerTest {
    public static void main(String[] args) {
        testBookManager();
    }

    public static void testBookManager() {
        BookManager bookManager = new BookManager();

        // Додавання книг
        Book book1 = new Book("Java Programming", "John Doe", 250);
        Book book2 = new Book("Python Basics", "Jane Smith", 300);

        bookManager.addBook(book1);
        bookManager.addBook(book2);

        // Перевірка наявності книг
        System.out.println("Is 'Java Programming' available? " + bookManager.isBookAvailable("Java Programming"));
        System.out.println("Is 'Python Advanced' available? " + bookManager.isBookAvailable("Python Advanced"));

        // Отримання всіх книг
        System.out.println("\nAll books:");
        for (Book book : bookManager.getAllBooks()) {
            System.out.println(book);
        }
    }
}