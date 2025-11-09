package com.example.form.model;

import jakarta.validation.constraints.*;

public class Student {

    private Long id; // optional if you add persistence later

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    private String lastName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    @Min(value = 1, message = "Year must be between 1 and 4")
    @Max(value = 4, message = "Year must be between 1 and 4")
    private Integer year;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}
