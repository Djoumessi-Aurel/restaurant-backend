package com.aurel.resto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.aurel.resto.helper.Functions;
import com.aurel.resto.helper.StatutCommande;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "commande")
@DynamicUpdate
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private int id;

    @NonNull
    private Date date = new Date();
    @NonNull
    private Integer numeroTable;
    @NonNull
    private StatutCommande statut = StatutCommande.EN_CREATION;

    @ManyToOne
    @JoinColumn(name = "id_modePaiement")
    private ModePaiement modePaiement;

    @OneToMany(mappedBy = "commande", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_serveur")
    private Serveur serveur; 


    public void setModePaiement(ModePaiement newModePaiement) {
        if (modePaiement != newModePaiement) {
        ModePaiement oldModePaiement = modePaiement;
        modePaiement = newModePaiement;
        if (newModePaiement != null)        newModePaiement.addCommande(this);
        if (oldModePaiement != null)        oldModePaiement.removeCommande(this);
        }
    }

    public void setClient(Client newClient) {
        if (client != newClient) {
        Client oldClient = client;
        client = newClient;
        if (newClient != null)        newClient.addCommande(this);
        if (oldClient != null)        oldClient.removeCommande(this);
        }
    }

    public void setServeur(Serveur newServeur) {
        if (serveur != newServeur) {
        Serveur oldServeur = serveur;
        serveur = newServeur;
        if (newServeur != null)        newServeur.addCommande(this);
        if (oldServeur != null)        oldServeur.removeCommande(this);
        }
    }

    public void print(){
        int i = 1;
        String texte = "";

        texte += "Contenu de votre commande: \n";
        
        for(OrderItem oItem : orderItems){
            texte += "\tItem numéro " + i + "\n";
            texte += "\t\tNom: " + oItem.getMenuItem().getNom() + "\n";
            texte += "\t\tCoût: " + oItem.getCoutAvecIngredients() + "\n";
            texte += "\t\tDescription: " + oItem.getMenuItem().getDescription() + "\n";
            i++;
        }

        System.out.println(texte);
    }
    public int getMontant(){
        int montant = 0;

        for(OrderItem oItem : orderItems){
            montant += oItem.getCoutAvecIngredients();
        }

        return montant;
    }

    public void ajouterItem(OrderItem item){
        if(Functions.contains(orderItems, item)) return;
        orderItems.add(item);
        item.setCommande(this);
    }
    public void supprimerItem(OrderItem item){
        orderItems.remove(item);
        item.setCommande(null);
    }

    public void validerCommande(){
        this.setStatut(StatutCommande.VALIDEE);
    }

}
