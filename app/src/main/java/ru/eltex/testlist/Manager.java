/**
 * Класс менеджера
 * @author Vadim Chernyavsky
 * @version v1.0
 */
package ru.eltex.testlist;

/**
 * The type Manager.
 */
public class Manager extends User implements CSV{
    /**
     * Instantiates a new Manager.
     *
     * @param name  the name
     * @param phone the phone
     */
    public Manager(String name, String phone) {
        super(name, phone);
    }

    @Override
    public String toString() {
        return "Manager: " + this.getName() + " " + this.getPhone();
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
