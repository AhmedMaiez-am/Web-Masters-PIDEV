    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author maiez
 */
public class Inventairecours {
    private int idcc;
    private String nomc;
    private String typecc;
    private String descriptioncc;

    public Inventairecours() {
    }

    public Inventairecours(int idcc, String nomc, String typecc, String descriptioncc) {
        this.idcc = idcc;
        this.nomc = nomc;
        this.typecc = typecc;
        this.descriptioncc = descriptioncc;
    }

    public Inventairecours(String nomc, String typecc, String descriptioncc) {
        this.nomc = nomc;
        this.typecc = typecc;
        this.descriptioncc = descriptioncc;
    }

    public int getIdcc() {
        return idcc;
    }

    public void setIdcc(int idcc) {
        this.idcc = idcc;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public String getTypecc() {
        return typecc;
    }

    public void setTypecc(String typecc) {
        this.typecc = typecc;
    }

    public String getDescriptioncc() {
        return descriptioncc;
    }

    public void setDescriptioncc(String descriptioncc) {
        this.descriptioncc = descriptioncc;
    }
    
    
}
