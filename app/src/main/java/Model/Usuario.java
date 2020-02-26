package Model;

public class Usuario {
    private String Id;
    private String email;
    private String senha;
    private String telefone;
    private String nome;
    private String matricula;

    public Usuario(){
    }

    public  Usuario(String email,String senha,String telefone,String nome,String matricula,String Id){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.matricula = matricula;
        this.Id = Id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
