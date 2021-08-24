package com.ixfosa.pojo;

/**
 * Created by ixfosa on 2021/4/22 16:36
 */
public class MyStudent {

    private Integer stuid;
    private String stuname;
    private String stuemail;
    private Integer age;

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuemail() {
        return stuemail;
    }

    public void setStuemail(String stuemail) {
        this.stuemail = stuemail;
    }

    public Integer getStuage() {
        return age;
    }

    public void setStuage(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyStudent{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stuemail='" + stuemail + '\'' +
                ", stuage=" + age +
                '}';
    }
}
