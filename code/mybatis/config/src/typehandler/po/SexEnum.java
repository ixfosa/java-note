package typehandler.po;

public enum SexEnum {

    MALE(1, "男"),
    FEMALE(0, "女");

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public static SexEnum getSexById(int id) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.getId() == id) {
                return sex;
            }
        }
        return null;
    }
}