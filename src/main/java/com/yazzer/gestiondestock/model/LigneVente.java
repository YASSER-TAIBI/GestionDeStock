package com.yazzer.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@Entity
@Table(name = "ligneVente")
public class LigneVente extends AbstractEntity {

   /** @ManyToOne
    @JoinColumn(name = "idvente")
    private Ventes vente;**/

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "pixunitaire")
    private BigDecimal prixUnitaire;
}
