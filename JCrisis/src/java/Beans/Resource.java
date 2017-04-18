package Beans;

/**
 * Java Bean for the Resource.
 *
 * @author Christian Lopez
 *
 * Updated: 2017/04/18 By: Alissa Duffy Standardized Commenting.
 */
public class Resource {

    /**
     * The id of the resource
     */
    private int resourceId;

    private boolean isValid = true;

    /**
     * A CSV list of categories
     */
    private String categories;

    /**
     * The name of the resource
     */
    private String name;

    /**
     * The phone number of the resource
     */
    private String phone;

    /**
     * The first line of the address of the resource
     */
    private String addressOne;

    /**
     * The second line of the address of the resource
     */
    private String addressTwo;

    /**
     * The city of the resource
     */
    private String city;

    /**
     * The territory(state) of the resource
     */
    private String territory;

    /**
     * The country of the resource
     */
    private String country;

    /**
     * The postal code of the resource
     */
    private String postalCode;

    /**
     * The email of the resource
     */
    private String email;

    /**
     * The description of the resource
     */
    private String description;

    private String error = "";

    /**
     * Get Resource ID.
     *
     * @return resourceId
     */
    public int getResourceId() {
        return resourceId;
    }

    /**
     * Set Resource ID
     *
     * @param resourceId
     */
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Get Categories.
     *
     * @return categories
     */
    public String getCategories() {
        return categories;
    }

    /**
     * Set Categories.
     *
     * @param categories
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }

    /**
     * Get Resource Name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Resource Name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Resource Phone Number
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set Resource Phone Number
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get Address One of A Resource.
     *
     * @return addressOne
     */
    public String getAddressOne() {
        return addressOne;
    }

    /**
     * Set Address One of a Resource.
     *
     * @param addressOne
     */
    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    /**
     * Get Address Two of a Resource.
     *
     * @return addressTwo
     */
    public String getAddressTwo() {
        return addressTwo;
    }

    /**
     * Set Address Two of a Resource.
     *
     * @param addressTwo
     */
    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    /**
     * Get the City of a Resource.
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set City of a Resource.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the Territory of a Resource.
     *
     * @return territory
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * Set Territory of a Resource
     *
     * @param territory
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    /**
     * Get the Country of a Resource.
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the Country of a Resource.
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * +
     * Get the Postal Code of a Resource.
     *
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set the Postal Code of a Resource.
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Get the Email of o Resource.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the Email of a Resource.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the Description of a Resource.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the Description of a Resource.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get Error message.
     *
     * @return error
     */
    public String getError() {
        return error;
    }

    /**
     * Set Error message.
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Empty Resource Constructor.
     */
    public Resource() {
        this.resourceId = 0;
        this.categories = "";
        this.name = "";
        this.phone = "";
        this.addressOne = "";
        this.addressTwo = "";
        this.city = "";
        this.territory = "";
        this.country = "";
        this.postalCode = "";
        this.email = "";
        this.description = "";
    }

    /**
     * Full Resource Constructor.
     *
     * @param resourceId
     * @param categories
     * @param name
     * @param phone
     * @param addressOne
     * @param addressTwo
     * @param city
     * @param territory
     * @param country
     * @param postalCode
     * @param email
     * @param description
     */
    public Resource(int resourceId, String categories, String name, String phone, String addressOne, String addressTwo, String city, String territory, String country, String postalCode, String email, String description) {
        this.resourceId = resourceId;
        if (null != categories) {
            this.categories = categories;
        } else {
            this.categories = "";
        }
        this.name = name;
        this.phone = phone;
        this.addressOne = addressOne;
        if (null != addressTwo) {
            this.addressTwo = addressTwo;
        } else {
            this.addressTwo = "";
        }
        this.city = city;
        this.territory = territory;
        this.country = country;
        this.postalCode = postalCode;
        this.email = email;
        this.description = description;
        this.validateInputs(name, phone, addressOne, city, territory, country, postalCode, email, description);
    }

    /**
     * Input validation.
     *
     * @param name
     * @param phone
     * @param addressOne
     * @param city
     * @param territory
     * @param country
     * @param postalCode
     * @param email
     * @param description
     */
    public void validateInputs(String name, String phone, String addressOne, String city, String territory, String country, String postalCode, String email, String description) {
        if (name.equals("") || phone.equals("") || addressOne.equals("")
                || city.equals("") || territory.equals("") || country.equals("")
                || postalCode.equals("") || email.equals("") || description.equals("")) {
            this.isValid = false;
            this.error = "Invalid Inputs";
        } else {
            this.isValid = true;
        }
    }

    /**
     * @return isValid
     */
    public boolean isValid() {
        return this.isValid;
    }

}
