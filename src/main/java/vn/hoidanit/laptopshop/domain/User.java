package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;//JPA 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import vn.hoidanit.laptopshop.service.validator.StrongPassword;

@Entity // Dùng để biến class -> table
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @NotNull
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    // @NotNull
    @NotEmpty(message = "Password cannot be empty")
    @StrongPassword
    // @Size(message = "Password must be at least 6 characters and maximum 15
    // characters", min = 6, max = 15)
    private String password;

    // @NotNull
    @NotEmpty(message = "Full Name cannot be empty")
    @Size(message = "Full Name must be at least 2 characters", min = 2)
    private String fullName;

    // @NotNull
    // @NotEmpty(message = "Address cannot be empty")
    // @Size(message = "Address must be at least 6 characters and maximum 100 characters", min = 6, max = 100)
    private String address;

    // @NotNull
    // @NotEmpty(message = "Phone Number cannot be empty")
    // @Size(message = "Phone Number must be at least 6 characters and maximum 15 characters", min = 6, max = 15)
    private String phone;

    private String avatar;

    // FK Role -> User
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // User -> Order
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    //User -> Cart
    @OneToOne(mappedBy = "user")
     private Cart cart;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
                + ", address=" + address + ", phone=" + phone + ", avatar=" + avatar + "]";
    }

}
