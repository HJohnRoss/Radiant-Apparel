package com.radiantapparel.project.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="first name is required")
	@Size(min = 3, max = 30, message="Username must be between 3 and 30 characters")
	private String firstName;
	
	@NotEmpty(message="last name is required")
	@Size(min=3, max=30, message="lsat name must be between 3 and 30 characters")
	private String lastName;
	
	@NotEmpty(message="Email is required")
	@Email(message="Please enter a valid email")
	private String email;
	
	@NotEmpty(message="password is required")
	@Size(min=8, max=128, message="password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotEmpty
	@Size(min=8, max=128, message="Confirm password must be between 8 and 128 characters")
	private String confirm;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    @Column(updatable=false)
    @OneToMany(mappedBy="reviewer", fetch = FetchType.LAZY)
    private List<Review> reviewsMade;

    @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
            name="wishlist",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
        )
    private List<ProductDatabase> products; 



    public List<Review> getReviewsMade() {
        return reviewsMade;
    }
    public void setReviewsMade(List<Review> reviewsMade) {
        this.reviewsMade = reviewsMade;
    }
    public List<ProductDatabase> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDatabase> products) {
        this.products = products;
    }
    public User() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getConfirm() {
        return confirm;
    }
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}