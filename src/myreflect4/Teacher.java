package myreflect4;

public class Teacher {
    private String teacherName;

    private int teacherAge;

    public Teacher() {
    }

    public Teacher(String teacherName, int teacherAge) {
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
    }

    /**
     * 获取
     * @return teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置
     * @param teacherName
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 获取
     * @return teacherAge
     */
    public int getTeacherAge() {
        return teacherAge;
    }

    /**
     * 设置
     * @param teacherAge
     */
    public void setTeacherAge(int teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String toString() {
        return "Teacher{teacherName = " + teacherName + ", teacherAge = " + teacherAge + "}";
    }
}
