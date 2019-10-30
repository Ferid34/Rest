package az.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Adam {
    private long id;
   // @JsonIgnore olsa name cixmiyacaq
    private String name;
    private long age;
    private int status;

    public Adam() {
    }

    public Adam(long id, String name, long yas, int status) {
        this.id = id;
        this.name = name;
        this.age = yas;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Adam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yas=" + age +
                ", status=" + status +
                '}';
    }
}
