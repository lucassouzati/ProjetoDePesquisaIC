package dominio;


public class Conceito {

    private int ID;
    private String descricao;
    private int idTermo;

    public Conceito () {
    }

    public Conceito(int ID, String descricao) {
        this.ID = ID;
        this.descricao = descricao;
    }

    public void cadastrar () {
    }

    public String ListaTermosNaoConceituados () {
        return null;
    }

    public void ignorarTermo () {
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

