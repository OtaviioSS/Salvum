package Model;

public class Modelo_Curso {

    private String cursoId;
private String cursoTitulo;
private String cursoSobre;
private String cursoTags;
private int cursoParticipante;

public Modelo_Curso(){
}

public Modelo_Curso (String cursoTitulo,String cursoSobre,
                     String cursoTags,
                     int cursoParticipante,String cursoId){
    this.cursoTitulo = cursoTitulo;
    this.cursoSobre = cursoSobre;
    this.cursoTags = cursoTags;
    this.cursoParticipante = cursoParticipante;
    this.cursoId = cursoId;
}

    public String getCursoTitulo() {
        return cursoTitulo;
    }

    public void setCursoTitulo(String cursoTitulo) {
        this.cursoTitulo = cursoTitulo;
    }

    public String getCursoSobre() {
        return cursoSobre;
    }

    public void setCursoSobre(String cursoSobre) {
        this.cursoSobre = cursoSobre;
    }

    public String getCursoTags() {
        return cursoTags;
    }

    public void setCursoTags(String cursoTags) {
        this.cursoTags = cursoTags;
    }

    public int getCursoParticipante() {
        return cursoParticipante;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public void setCursoParticipante(int cursoParticipante) {
        this.cursoParticipante = cursoParticipante;
    }
}
