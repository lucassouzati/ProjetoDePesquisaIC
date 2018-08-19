package dominio;


public class Professor {

    private int ID;
    private String nome;
    private String login;
    private String senha;

    public Professor () {
    }

    public Professor(int ID, String nome, String login, String senha) {
        this.ID = ID;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public int getID () {
        return ID;
    }

    public void setID (int val) {
        this.ID = val;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String val) {
        this.login = val;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String val) {
        this.nome = val;
    }

    public String getSenha () {
        return senha;
    }

    public void setSenha (String val) {
        this.senha = val;
    }

    public void cadastrar () {
    }

    public String toString() {
        return "ID: " + ID + "\n" +
               "Nome: " + nome + "\n" +
               "Login: " + login + "\n" +
               "Senha: " + senha + "\n";
    }

}

