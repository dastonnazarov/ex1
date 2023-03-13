import java.util.List;

public class Main {

    public static void main(String[] args) {

        LibraryManager manager = new LibraryManager();
        manager.addNewBook("Math", "Ali", 2000);
        manager.addNewBook("Kimyo", "Kimyohon", 2001);
        manager.addNewBook("Fizika", "Ali", 2011);
        manager.addNewBook("Tarix", "Tarixjon", 2014);


        Book book = manager.getBookByTitle("Math");
        if (book == null) {
            System.out.println("Error in getBookByTitle : Math book not found");
            return;
        }

        List<Book> list = manager.getAuthorBooks("Ali");
        if (list.size() != 2) {
            System.out.println("Error in getAuthorBooks . author Ali has 2 books but get: " + list.size());
            return;
        }

        int n = manager.addAvailableBook("Kimyo", 4);
        if (n != 5) {
            System.out.println("Error in addAvailableBook: Book cound on Kimyo must be 5. but get " + n);
            return;
        }

        n = manager.getAvailableBook("Kimyo");

        if (n != 5) {
            System.out.println("Error in addAvailableBook: Book cound on Kimyo must be 5. but get " + n);
            return;
        }


        n = manager.getAvailableBookByAuthor("Ali");
        if (n != 2) {
            System.out.println("Error in getAvailableBookByAuthor: Author Ali ha 2 books. but get " + n);
            return;
        }


        Student studentALi = manager.addStudent("Ali", "ali", "7777", 4);
        Student studentSamandar = manager.addStudent("Samandar", "samandar", "4131", 2);
        Student studentVali = manager.addStudent("Vali", "Valiyev", "1717", 2);


        if (studentALi == null) {
            System.out.println("Error in addStudent: Added Student must not be null.");
            return;
        }


        /* Take Book */

        boolean bool = manager.takeBook(studentALi.getId(), "Math");
        if (!bool) {
            System.out.println("Error in takeBook: Ali must take book Math.");
            return;
        }


        bool = manager.takeBook(studentALi.getId(), "Kimyo");
        if (!bool) {
            System.out.println("Error in takeBook: Ali must take book Math.");
            return;
        }


        bool = manager.takeBook(studentSamandar.getId(), "Kimyo");
        if (!bool) {
            System.out.println("Error in takeBook: Samandar must take book Kimyo.");
            return;
        }

        bool = manager.takeBook(studentVali.getId(), "Fizika");
        if (!bool) {
            System.out.println("Error in takeBook: Valie must take book Fizika.");
            return;
        }

        /* Return Book */

        n = manager.returnTakenBook(studentALi.getId(), "Math");
        if (n != 1) {
            System.out.println("Error in returnTakenBook: Ali returned only 1 book and 1 not returned yet. So  n must be 1 but get :" + n);
            return;
        }


        n = manager.returnTakenBook(studentSamandar.getId(), "Kimyo");
        if (n != 0) {
            System.out.println("Error in returnTakenBook: Samandar returned all books  n must be 0 but get :" + n);
            return;
        }

        /* Took Book Count */
        n = manager.getStudentTookBookCount(studentALi.getId());
        if (n != 1) {
            System.out.println("Error in getStudentTookBookCount: Ali not returned 1 book yet. So n must be 1 but get :" + n);
            return;
        }

        n = manager.getStudentTookBookCount(studentSamandar.getId());
        if (n != 0) {
            System.out.println("Error in getStudentTookBookCount: Samandar  returned all taken books. So n must be 0 but get :" + n);
            return;
        }

        n = manager.getStudentTookBookCount(studentVali.getId());
        if (n != 1) {
            System.out.println("Error in getStudentTookBookCount: Vali  not returned  1 book yet. So n must be 1 but get :" + n);
            return;
        }


        /* booksOnHand */
        n = manager.booksOnHand();
        if (n != 2) {
            System.out.println("Error in booksOnHand: 2 books not returned yet. So n must be 2 but get :" + n);
            return;
        }

        n = manager.studentCountWhichTookABook();
        if (n != 3) {
            System.out.println("Error in studentCountWhichTookABook: 3 students took book. So n must be 3 but get :" + n);
            return;
        }

        n = manager.bookTookCount("Kimyo");
        if (n != 2) {
            System.out.println("Error in bookTookCount: Kimyo book taken 2 times. So n must be 2 but get :" + n);
            return;
        }

        n = manager.bookTookCount("Fizika");
        if (n != 1) {
            System.out.println("Error in bookTookCount: Kimyo book taken 1 time. So n must be 1 but get :" + n);
            return;
        }


        n = manager.bookOnHandCountByLevel(2);
        if (n != 1) {
            System.out.println("Error in bookOnHandCountByLevel: Vali and Samanda in 2 level. Samanda returned book but Vali not returned yet" +
                    "So n must be 1 but get :" + n);
            return;
        }

        System.out.println("Success");

    }
}
