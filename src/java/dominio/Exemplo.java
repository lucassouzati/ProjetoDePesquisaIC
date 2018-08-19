package dominio;


public class Exemplo {

    private int ID;
    private String descricao;
    private int idTermo;

    public Exemplo () {
    }

    public Exemplo(int ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
    }

    public void cadastrar () {
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

