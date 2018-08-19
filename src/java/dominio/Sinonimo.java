package dominio;


public class Sinonimo {

    private int ID;
    private String descricao;
    private int idTermo;

    public Sinonimo () {
    }

    public Sinonimo(int ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
    }

    public int getID () {
        return ID;
    }

    public void setID (int val) {
        this.ID = val;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String val) {
        this.descricao = val;
    }

    public void cadastrar () {
    }

    public String toString() {
       return "ID: " + ID + "\n" + 
              "Descricao: " + descricao + "\n";
    }

    /**
     * @return the idTermo
     */
    public int getIdTermo() {
        return idTermo;
    }

    /**
     * @param idTermo the idTermo to set
     */
    public void setIdTermo(int idTermo) {
        this.idTermo = idTermo;
    }
}

