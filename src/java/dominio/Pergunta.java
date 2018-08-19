package dominio;


public class Pergunta {

    private int ID;
    private String texto;
    private int idDisciplina;

    public Pergunta () {
    }

    public Pergunta(int ID, String texto) {
        this.ID = ID;
        this.texto = texto;
    }

    public int getID () {
        return ID;
    }

    public void setID (int val) {
        this.ID = val;
    }

    public String getTexto () {
        return texto;
    }

    public void setTexto (String val) {
        this.texto = val;
    }

    public void cadastrar () {
    }

    public String toString() {
        return "ID: " + ID + "\n" +
               "Texto: " + texto + "\n";
    }

    /**
     * @return the idDisciplina
     */
    public int getidDisciplina() {
        return idDisciplina;
    }

    /**
     * @param idDisciplina the idDisciplina to set
     */
    public void setidDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

}

