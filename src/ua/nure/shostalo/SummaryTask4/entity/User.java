package ua.nure.shostalo.SummaryTask4.entity;

/**
 * The class describes entity user.
 * 
 * @author Mikhail Shostalo
 *
 */
public class User {

    /**
     * A name.
     */
    private String name;

    /**
     * A last name.
     */
    private String lastName;

    /**
     * A login.
     */
    private String login;

    /**
     * A password.
     */
    private String password;

    /**
     * An email.
     */
    private String email;

    /**
     * A mobile phone.
     */
    private String mobilePhone;

    /**
     * User role.
     */
    private String role;

    /**
     * Creates object {@link User}.
     * 
     * @param name
     *            the name
     * @param lastName
     *            the last name
     * @param login
     *            the login
     * @param password
     *            the password
     * @param email
     *            the email
     * @param mobilePhone
     *            the mobile phone
     * @param role
     *            the user role
     */
    public User(final String name, final String lastName, final String login,
	    final String password, final String email, final String mobilePhone,
	    final String role) {
	this.name = name;
	this.lastName = lastName;
	this.login = login;
	this.password = password;
	this.email = email;
	this.mobilePhone = mobilePhone;
	this.role = role;
    }

    /**
     * @return the name
     */
    public final String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(final String name) {
	this.name = name;
    }

    /**
     * @return the lastName
     */
    public final String getLastName() {
	return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public final void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    /**
     * @return the login
     */
    public final String getLogin() {
	return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public final void setLogin(final String login) {
	this.login = login;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public final void setPassword(final String password) {
	this.password = password;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
	return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public final void setEmail(final String email) {
	this.email = email;
    }

    /**
     * @return the mobilePhone
     */
    public final String getMobilePhone() {
	return mobilePhone;
    }

    /**
     * @param mobilePhone
     *            the mobilePhone to set
     */
    public final void setMobilePhone(final String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    /**
     * @return the role
     */
    public final String getRole() {
	return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public final void setRole(final String role) {
	this.role = role;
    }

}
