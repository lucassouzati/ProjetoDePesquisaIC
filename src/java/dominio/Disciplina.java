package dominio;


public class Disciplina {

    private int ID;
    private String nome;
    private int IdProfessor;

    public Disciplina () {
    }

    public Disciplina(int ID, String nome) {
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
     * @return the IdProfessor
     */
    public int getIdProfessor() {
        return IdProfessor;
    }

    /**
     * @param IdProfessor the IdProfessor to set
     */
    public void setIdProfessor(int IdProfessor) {
        this.IdProfessor = IdProfessor;
    }
}

