package net.challenge.currency_converter.signup;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import net.challenge.currency_converter.account.Account;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String street;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String zip;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String city;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
    private String country;
    
    private String birthDate = "01/01/1990";

    public SignupForm() {
		super();
	}

	public SignupForm(String email, String password, String street, String zip, String city, String country,
			String birthDate) {
		super();
		this.email = email;
		this.password = password;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account createAccount() {
        return new Account(getEmail(), getPassword(), "ROLE_USER");
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}
