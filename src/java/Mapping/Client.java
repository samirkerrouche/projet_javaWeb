package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Client generated by hbm2java
 */
public class Client implements java.io.Serializable {

    private Integer codecli;
    private Profil profil;
    private User user;
    private String nomcli;
    private String prenomcli;
    private Date datenaisscli;
    private String taillecli;
    private String poidscli;
    private String mailcli;
    private String telcli;
    private Date dateinscriptioncli;
    private String commadmincli;
    private String statutcli;
    private Character sexecli;
    private byte[] photocli;
    private Set executerCircuits = new HashSet(0);
    private Set affecters = new HashSet(0);
    private Set evaluers = new HashSet(0);
    private Set executerExos = new HashSet(0);

    public Client() {
    }

    public Client(Profil profil, User user, String nomcli, String prenomcli, Date datenaisscli, String taillecli, String poidscli, String mailcli, String telcli, Date dateinscriptioncli, String commadmincli, String statutcli, Character sexecli, byte[] photocli, Set executerCircuits, Set affecters, Set evaluers, Set executerExos) {
        this.profil = profil;
        this.user = user;
        this.nomcli = nomcli;
        this.prenomcli = prenomcli;
        this.datenaisscli = datenaisscli;
        this.taillecli = taillecli;
        this.poidscli = poidscli;
        this.mailcli = mailcli;
        this.telcli = telcli;
        this.dateinscriptioncli = dateinscriptioncli;
        this.commadmincli = commadmincli;
        this.statutcli = statutcli;
        this.sexecli = sexecli;
        this.photocli = photocli;
        this.executerCircuits = executerCircuits;
        this.affecters = affecters;
        this.evaluers = evaluers;
        this.executerExos = executerExos;
    }

    public Integer getCodecli() {
        return this.codecli;
    }

    public void setCodecli(Integer codecli) {
        this.codecli = codecli;
    }

    public Profil getProfil() {
        return this.profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNomcli() {
        return this.nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getPrenomcli() {
        return this.prenomcli;
    }

    public void setPrenomcli(String prenomcli) {
        this.prenomcli = prenomcli;
    }

    public Date getDatenaisscli() {
        return this.datenaisscli;
    }

    public void setDatenaisscli(Date datenaisscli) {
        this.datenaisscli = datenaisscli;
    }

    public String getTaillecli() {
        return this.taillecli;
    }

    public void setTaillecli(String taillecli) {
        this.taillecli = taillecli;
    }

    public String getPoidscli() {
        return this.poidscli;
    }

    public void setPoidscli(String poidscli) {
        this.poidscli = poidscli;
    }

    public String getMailcli() {
        return this.mailcli;
    }

    public void setMailcli(String mailcli) {
        this.mailcli = mailcli;
    }

    public String getTelcli() {
        return this.telcli;
    }

    public void setTelcli(String telcli) {
        this.telcli = telcli;
    }

    public Date getDateinscriptioncli() {
        return this.dateinscriptioncli;
    }

    public void setDateinscriptioncli(Date dateinscriptioncli) {
        this.dateinscriptioncli = dateinscriptioncli;
    }

    public String getCommadmincli() {
        return this.commadmincli;
    }

    public void setCommadmincli(String commadmincli) {
        this.commadmincli = commadmincli;
    }

    public String getStatutcli() {
        return this.statutcli;
    }

    public void setStatutcli(String statutcli) {
        this.statutcli = statutcli;
    }

    public Character getSexecli() {
        return this.sexecli;
    }

    public void setSexecli(Character sexecli) {
        this.sexecli = sexecli;
    }

    public byte[] getPhotocli() {
        return this.photocli;
    }

    public void setPhotocli(byte[] photocli) {
        this.photocli = photocli;
    }

    public Set getExecuterCircuits() {
        return this.executerCircuits;
    }

    public void setExecuterCircuits(Set executerCircuits) {
        this.executerCircuits = executerCircuits;
    }

    public Set getAffecters() {
        return this.affecters;
    }

    public void setAffecters(Set affecters) {
        this.affecters = affecters;
    }

    public Set getEvaluers() {
        return this.evaluers;
    }

    public void setEvaluers(Set evaluers) {
        this.evaluers = evaluers;
    }

    public Set getExecuterExos() {
        return this.executerExos;
    }

    public void setExecuterExos(Set executerExos) {
        this.executerExos = executerExos;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    public int getAgeClient() {
        return 100;
        // todo
    }

}
