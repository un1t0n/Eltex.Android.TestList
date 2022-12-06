/**
 * Класс пользователя
 * @author Vadim Chernyavsky
 * @version v1.0
 */

package ru.eltex.testlist;

/**
 * The type User.
 */
public abstract class User{
    private String name;
    private String phone;

    /**
     * Instantiates a new User.
     *
     * @param name  the name
     * @param phone the phone
     */
    public User(String name, String phone) {
        this.phone = phone;
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public abstract String toString();

    public abstract String toJSONString();
}
