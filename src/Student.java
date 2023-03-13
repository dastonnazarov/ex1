public class Student {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private int level;

    public Student(Integer id, String name, String surname, String phone, int level) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
