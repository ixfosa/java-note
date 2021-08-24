package bean;

/**
 * Created by ixfosa on 2021/3/26 12:54
 */
public class User {
    private int tid;
    private String uname;
    private int age;
    private char sex;

    @Override
    public String toString() {
        return "User{" +
                "tid=" + tid +
                ", uname='" + uname + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
