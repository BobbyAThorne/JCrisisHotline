package Beans;


/**
 *
 * @author Christian Lopez
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

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    
    public String getCategories() {
        return categories;
    }
    
    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    
    
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
    
    public void validateInputs(String name, String phone, String addressOne, String city, String territory, String country, String postalCode, String email, String description) {
        if(name.equals("") || phone.equals("") || addressOne.equals("") || 
                city.equals("") || territory.equals("") || country.equals("") ||
                postalCode.equals("") || email.equals("") || description.equals("")) {
            this.isValid = false;
            this.error = "Invalid Inputs";
        } else {
            this.isValid = true;
        }
    }
    
    public boolean isValid() {
        return this.isValid;
    }
    
}
