package com.dh.digitalCar.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Politics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "delivery must not be blank")
    @Column(columnDefinition = "TEXT")
    private String delivery;
    @NotBlank(message = "healthAndSecurity must not be blank")
    @Column(columnDefinition = "TEXT")
    private String healthAndSecurity;
    @NotBlank(message = "cancellation must not be blank")
    @Column(columnDefinition = "TEXT")
    private String cancellation;

    public Politics() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getHealthAndSecurity() {
        return healthAndSecurity;
    }

    public void setHealthAndSecurity(String healthAndSecurity) {
        this.healthAndSecurity = healthAndSecurity;
    }

    public String getCancellation() {
        return cancellation;
    }

    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    @Override
    public String toString() {
        return "Politics{" +
                "id=" + id +
                ", delivery='" + delivery + '\'' +
                ", healthAndSecurity='" + healthAndSecurity + '\'' +
                ", cancellation='" + cancellation + '\'' +
                '}';
    }
}
