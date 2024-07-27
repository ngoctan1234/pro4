package com.pro.woo.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="tokens")
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="token",length=255)
    private String name;


    @Column(name="token_type",length=50)
    private String tokenType;

    @Column(name="expiration_data")
    private String expirationData;

    private boolean revoked;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
