package com.aurel.carlib.code;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.aurel.carlib.helper.StatutCommande;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Commande {

    @NonNull
    private Date date;
    @NonNull
    private Time heure;
    @NonNull
    private Integer numeroTable;
    @NonNull
    private StatutCommande statut = StatutCommande.EN_CREATION;

    private ModePaiement modePaiement;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();
    private Client client;
    private Serveur serveur; 

    public static Set<Commande> listeCommandes = new HashSet<Commande>(); //Variable de l'application

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
        orderItems.add(item);
        item.setCommande(this);
    }
    public void supprimerItem(OrderItem item){
        orderItems.remove(item);
        item.setCommande(null);
    }

    public void enregistrerCommande(){
        this.setStatut(StatutCommande.ENREGISTREE);
        listeCommandes.add(this);
    }

    public void validerCommande(){
        this.setStatut(StatutCommande.VALIDEE);
    }

    public static void ajouterCommande(Commande cmd){
        listeCommandes.add(cmd);
    }

    public static void supprimerCommande(Commande cmd){
        listeCommandes.remove(cmd);
    }

    public static ArrayList<Commande> getListeCommandesByStatus(StatutCommande state){
        ArrayList<Commande> cmds = new ArrayList<Commande>();

        for(Commande c: listeCommandes){
            if(c.getStatut() == state) cmds.add(c);
        }

        return cmds;
    }

}
