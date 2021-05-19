/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author HP
 */
public class Enfant {
     private int ide ;
    private String nomenfant ;
    private String prenomenfant ;
    private int nbr_point ;
    private int age ;
    private int idparent ;
    private String password ;
    private String image;
    private String block ;

    public Enfant(String nomenfant, String prenomenfant, int age, int idparent, String password, String image) {
        this.nomenfant = nomenfant;
        this.prenomenfant = prenomenfant;
        this.age = age;
        this.idparent = idparent;
        this.password = password;
        this.image = image;
    }

    public Enfant() {
       
    }

   

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getNomenfant() {
        return nomenfant;
    }

    public void setNomenfant(String nomenfant) {
        this.nomenfant = nomenfant;
    }

    public String getPrenomenfant() {
        return prenomenfant;
    }

    public void setPrenomenfant(String prenomenfant) {
        this.prenomenfant = prenomenfant;
    }

    public int getNbr_point() {
        return nbr_point;
    }

    public void setNbr_point(int nbr_point) {
        this.nbr_point = nbr_point;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    
  
    
}
