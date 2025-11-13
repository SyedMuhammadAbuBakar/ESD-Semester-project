package com.banking.banking_app.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@Table(name="notifications")
public class Notifications {
 
@Id
@GeneratedValue(strategy =GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
private Long notificationId;
    
}
