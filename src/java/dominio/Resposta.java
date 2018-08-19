package dominio;

public class Resposta {
    private int ID;
    private int idPergunta;
    private int idAluno;
    private String texto;

    public Resposta(int ID, int idPergunta, int idAluno, String texto) {
        this.ID = ID;
        this.idPergunta = idPergunta;
        this.idAluno = idAluno;
        this.texto = texto;
    }

    public Resposta() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String toString() {
        return "ID: " + ID + "\n" +
               "idPergunta: " + idPergunta + "\n" +
               "idAluno: " + idAluno + "\n" +
               "Texto: " + texto + "\n";
    }
    
}
