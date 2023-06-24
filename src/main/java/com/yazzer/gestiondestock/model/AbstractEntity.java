package com.yazzer.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // l'annotation va automatiquement écoutez cette class et à chaque fois qu'elle trouve "@CreatedDate/@LastModifiedDate" va automatiquement mettre à jour ces champs dans la base de données lors de l'enregistrement, elle va assigner lors de la modification une valeur à @LastModifiedDate.
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false) // nullable: il faut pas que cette date soit nul  // updatable: pour dire à Spring je ne veux pas le mettre à jour cette attribut "creationDate"
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Instant lastModifiedDate;

   /* @PrePersist
    void prePersist(){
        creationDate = Instant.now();
    }

    @PreUpdate
    void preUpdate(){
        lastModifiedDate = Instant.now();
    }*/
}
