/**
 * Класс разработчика
 * @author Vadim Chernyavsky
 * @version v1.0
 */
package ru.eltex.testlist;

/**
 * The type Developer.
 */
public class Developer extends User implements CSV{

    /**
     * Instantiates a new Developer.
     *
     * @param name  the name
     * @param phone the phone
     */
    public Developer(String name, String phone) {
        super(name, phone);
    }

    @Override
    public String toString() {
        return "Dev: " + this.getName() + " " + this.getPhone();
    }

    @Override
    public String toJSONString() {
        return "{\"name:\": " + this.getName() + ", \"phone\":" + this.getPhone() + "}";
    }

    @Override
    public String toCSV() {
        return null;
    }

    @Override
    public void fromCSV(String str) {

    }
}
