import java.util.LinkedList;
import java.util.List;

public class LibraryManager {
    private LinkedList<Book> bookList = new LinkedList<>();
    private Integer generalId = 1;
    private List<Student> studentList = new LinkedList<>();
    private List<StudentBook> studentBookList = new LinkedList<>();

    // kitob kiritish
    public void addNewBook(String title, String author, int publishYear) {
        Book alreadyExists = getBookByTitle(title);
        if (alreadyExists != null) {
            return;
        }

        Book book = new Book(title, author, publishYear);
        bookList.add(book);
    }

    // nomi bo'yicha kitobbi qaytaring
    public Book getBookByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    // aftorni barcha kitoblarini qaytaring
    public List<Book> getAuthorBooks(String author) {
        List<Book> authorBookList = new LinkedList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                authorBookList.add(book);
            }
        }
        return authorBookList;
    }

    // bor kitoblar sonini son qo'shish agar kitob bo'lmasa -1 return qiling.
    // agar  bo'lsa  uni sonini ko'paytirib xosil bo'lgan sonni return qiling
    public int addAvailableBook(String title, int count) {
        Book book = getBookByTitle(title);
        if (book == null) {
            return -1;
        }
//        book.setCount(book.getCount() + count);
        book.addCount(count);

        return book.getCount();
    }

    // shu  kitobdan nechta borligini return qiling
    public int getAvailableBook(String title) {
        /*for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book.getCount();
            }
        }
        return 0;*/
        Book book = getBookByTitle(title);
        if (book == null) {
            return -1;
        }
        return book.getCount();
    }

    //  berilgan aftor ni  kitoblar sonini return qiling
    public int getAvailableBookByAuthor(String author) {
        int count = 0;
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                count += book.getCount();
            }
        }
        return count;
    }

    public Student addStudent(String name, String surname, String phone, int level) {
        Student student = new Student(generalId++, name, surname, phone, level);
        studentList.add(student);
        return student;
    }

    public Student getStudentById(Integer id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    // bu method studentga  kitob berish uchun ishlatiladi.
    // agar berilgan student yokiy kitob bo'lmasa false return qiling.
    // agar student da uje olingan kitoblar soni 5 da bo'lsa false ruturn qiling.
    // (Bitta student bir vaqtni o'zida 5ta kitob olga bo'lishi mumkin)
    // agar ok bo'lsa  shu kitobni shu student oldi deb yozib qo'yish kerak (qayerdadir)

    public boolean takeBook(Integer studentId, String title) {
        // 1 Ikki TAKEN - StudentBook
        // 2 Mazgi
        // 1 Java
        // 1 SQL
        Book book = getBookByTitle(title);
        Student student = getStudentById(studentId);
        if (book == null || student == null) {
            return false;
        }

        int count = studentTookBookCount(studentId);
        if (count >= 5) {
            return false;
        }

        StudentBook studentBook = new StudentBook(studentId, title, "TAKEN");
        studentBook.setStudent(student);
        studentBookList.add(studentBook);

        return true;
    }

    private int studentTookBookCount(Integer studentId) {
        int count = 0;
        for (StudentBook sb : studentBookList) {
            if (sb.getsId().equals(studentId) && sb.getStatus().equals("TAKEN")) {
                count++;
            }
        }
        return count;
    }


    // bu metod berilgan kitobni qaytarish uchun ishlatiladi.
    // agar berilgan student yokiy kitob bo'lmasa -1 return qiling
    // agar hammasi ok bo'lsa student kitobni topshirsin.
    // va studentni olib xali qaytarmagan kitoblar sonini qaytaring.
    // returnBook
    public int returnTakenBook(Integer studentId, String title) {
        for (StudentBook sb : studentBookList) {
            if (sb.getsId().equals(studentId) && sb.getBookTitle().equals(title) && sb.getStatus().equals("TAKEN")) {
                sb.setStatus("RETURNED");
                return getStudentTookBookCount(studentId);
            }
        }
        return -1;
    }

    // bu metod studentni qolidagi kitoblar sonini return qiladi (xali qaytarmagan kitoblar sonini)
    // agar student bo'lmasa -1 return qiling
    public int getStudentTookBookCount(Integer studentId) {
        Student student = getStudentById(studentId);
        if (student == null) {
            return -1;
        }
        int count = 0;
        for (StudentBook sb : studentBookList) {
            if (sb.getsId().equals(studentId) && sb.getStatus().equals("TAKEN")) {
                count++;
            }
        }
        return count;
    }

    // bu metod olib xali qaytarilmagan kitoblar sonini return qiladi
    public int booksOnHand() {
        int count = 0;
        for (StudentBook sb : studentBookList) {
            if (sb.getStatus().equals("TAKEN")) {
                count++;
            }
        }
        return count;
    }

    // bu metod barcha kitob olgan studentlar sonini return qiladi.
    // bitta student birmarta xisoblansin
    public int studentCountWhichTookABook() {
        List<Integer> idList = new LinkedList<>();
        for (StudentBook sb : studentBookList) {
            if (!idList.contains(sb.getsId())) {
                idList.add(sb.getsId());
            }
        }
        return idList.size();
    }

    // berilgan kitob nechi marta olingam?
    public int bookTookCount(String title) {
        int count = 0;
        for (StudentBook sb : studentBookList) {
            if (sb.getBookTitle().equals(title)) {
                count++;
            }
        }
        return count;
    }

    // berilgan kursda o'qidigan studentlar ning qolida nechta kitob bor?
    public int bookOnHandCountByLevel(Integer level) {

        /*int count = 0;
        for(Student student : studentList){
            if(student.getLevel() == level){ // 1
                count +=studentTookBookCount(student.getId());
            }
        }
        return count;*/

        int count = 0;
        for (StudentBook sb : studentBookList) {
            if (sb.getStudent().getLevel() == level && sb.getStatus().equals("TAKEN")) {
                count++;
            }
        }
        return count;

    }

}
