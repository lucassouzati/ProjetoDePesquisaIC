package dominio;

public class TermoTemp {

    private int ID;
    private String nome;
    private Conceito conceito;
    private Exemplo exemplo;
    private Sinonimo sinonimo;
    private Pergunta pergunta;
    private int IdDisciplina;

    public TermoTemp() {
    }

    public TermoTemp(int ID, String nome, Conceito conceito, Exemplo exemplo, Sinonimo sinonimo, Pergunta pergunta) {
        this.ID = ID;
        this.nome = nome;
        this.conceito = conceito;
        this.exemplo = exemplo;
        this.sinonimo = sinonimo;
        this.pergunta = pergunta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int val) {
        this.ID = val;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String val) {
        this.nome = val;
    }

    public Conceito getConceito() {
        return conceito;
    }

    public void setConceito(Conceito conceito) {
        this.conceito = conceito;
    }

    public Exemplo getExemplo() {
        return exemplo;
    }

    public void setExemplo(Exemplo exemplo) {
        this.exemplo = exemplo;
    }

    public Sinonimo getSinonimo() {
        return sinonimo;
    }

    public void setSinonimo(Sinonimo sinonimo) {
        this.sinonimo = sinonimo;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public int getIdDisciplina() {
        return IdDisciplina;
    }

    public void setIdDisciplina(int IdDisciplina) {
        this.IdDisciplina = IdDisciplina;
    }

    public void cadastrar() {
    }

    public String toString() {
        return "ID: " + ID + "\n" +
                "Nome: " + nome + "\n" +
                "Conceito: " + conceito + "\n" +
                "Exemplo: " + exemplo + "\n" +
                "Sinônimo: " + sinonimo + "\n" +
                "Pergunta: " + pergunta + "\n";
    }
}

