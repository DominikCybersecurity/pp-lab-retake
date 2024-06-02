import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static BookManager bookManager;

    public static void main(String[] args) {
        List<Book> initialBooks = new ArrayList<>();
        initialBooks.add(new Book("Pan Tadeusz", "Adam Mickiewicz", "ISBN1", 2001));
        initialBooks.add(new Book("Inny Swiat", "Gustaw Herling-Grudzinski", "ISBN2", 2002));
        initialBooks.add(new Book("Tango", "Slawomir Mrozek", "ISBN3", 2003));
        initialBooks.add(new Book("Lalka", "Boleslaw Prus", "ISBN4", 2004));
        initialBooks.add(new Book("Iliada", "Homer", "ISBN5", 2005));

        bookManager = new BookManager(initialBooks);
        runMenu();
    }

    private static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("Menu:");
            System.out.println("[1] Add book");
            System.out.println("[2] Remove book");
            System.out.println("[3] Update book");
            System.out.println("[4] List books");
            System.out.println("[5] Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();  

        Book newBook = new Book(title, author, isbn, year);
        bookManager.addBook(newBook);
        System.out.println("Book added successfully.");
    }

    private static void removeBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();
        Book bookToRemove = null;
        for (Book book : bookManager.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            bookManager.removeBook(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine();
        Book oldBook = null;
        for (Book book : bookManager.getBooks()) {
            if (book.getIsbn().equals(isbn)) {
                oldBook = book;
                break;
            }
        }

        if (oldBook != null) {
            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new author: ");
            String newAuthor = scanner.nextLine();
            System.out.print("Enter new ISBN: ");
            String newIsbn = scanner.nextLine();
            System.out.print("Enter new year: ");
            int newYear = scanner.nextInt();
            scanner.nextLine();  

            Book newBook = new Book(newTitle, newAuthor, newIsbn, newYear);
            bookManager.updateBook(oldBook, newBook);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void listBooks() {
        List<Book> books = bookManager.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}

