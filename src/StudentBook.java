public class StudentBook {
    private Integer sId;
    private Student student;
    private String bookTitle;
    private String status; // TAKEN,RETURNED


    public StudentBook(Integer sId, String bookTitle, String status) {
        this.sId = sId;
        this.bookTitle = bookTitle;
        this.status = status;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

