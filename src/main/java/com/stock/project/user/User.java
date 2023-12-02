package com.stock.project.user;

import com.stock.project.transaction.Transaction;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    private String name;
    @Column(unique = true)
    private String email;
    private Boolean isEmailVerified;
    private String password;
    @Column(unique = true)
    private Long phoneNumber;
    private Boolean isPhoneNumberVerified;
    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "transactions")
    private List<Transaction> transactions;
    private LocalDate lastBonus;
    private Integer workDays;
    private Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getPhoneNumberVerified() {
        return isPhoneNumberVerified;
    }

    public void setPhoneNumberVerified(Boolean phoneNumberVerified) {
        isPhoneNumberVerified = phoneNumberVerified;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public LocalDate getLastBonus() {
        return lastBonus;
    }

    public void setLastBonus(LocalDate lastBonus) {
        this.lastBonus = lastBonus;
    }

    public Integer getWorkDays() {
        return workDays;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setWorkDays(Integer workDays) {
        this.workDays = workDays;
    }
}
