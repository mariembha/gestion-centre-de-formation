
package gestionformation;

/**
 *
 * @author mariem
 */
public class demande { 
    int id;
   
    String nom;
    String prenom;
    String email;
    String CIN;
    String fonction;
    String tel;
    int codeformation;

    public demande(int id,String nom, String prenom, String email, String CIN, String fonction, String tel, int codeformation) { 
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.CIN = CIN;
        this.fonction = fonction;
        this.tel = tel;
        this.codeformation = codeformation;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getCIN() {
        return CIN;
    }

    public String getFonction() {
        return fonction;
    }

    public String getTel() {
        return tel;
    }

    public int getCodeformation() {
        return codeformation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCodeformation(int codeformation) {
        this.codeformation = codeformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
