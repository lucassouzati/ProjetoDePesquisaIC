package dominio;

public class AlunoDisci {

    private int ID;
    private int idAluno;
    private int idDisciplina;

    public AlunoDisci(int ID, int idAluno, int idDisciplina) {
        this.ID = ID;
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
    }

    public AlunoDisci() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String toString() {
        return "ID: " + ID + "\n" +
               "idAluno: " + idAluno + "\n" +
               "idDisciplina: " + idDisciplina;
    }
}
