package dominio;


public class TermoIgnorado {

    private int ID;
    private String nome;
    private int IdDisciplina;

    public TermoIgnorado () {
    }

    public TermoIgnorado(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public int getID () {
        return ID;
    }

    public void setID (int val) {
        this.ID = val;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String val) {
        this.nome = val;
    }

    public void cadastrar () {
    }

    public String toString() {
       return "ID: " + ID + "\n" +
              "Nome: " + nome + "\n";
    }

    /**
     * @return the IdDisciplina
     */
    public int getIdDisciplina() {
        return IdDisciplina;
    }

    /**
     * @param IdDisciplina the IdDisciplina to set
     */
    public void setIdDisciplina(int IdDisciplina) {
        this.IdDisciplina = IdDisciplina;
    }
}

