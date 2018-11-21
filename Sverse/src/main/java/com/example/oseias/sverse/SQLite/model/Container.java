package com.example.oseias.sverse.SQLite.model;

public class Container {
    private Integer _id;
    private String name;
    private String type;
    private String decricao;
    private Integer nParticipantes;
    private boolean haveNotifications;
    private Integer nNotifications;
    private Integer imageBg;
    private Integer imageContainer;
    private String dificuldade;
    private String importancia;
    private Integer idProfessor;
    private String codeParticipacao;
    private String data_de_criacao;
    private String hora_de_criacao;

    public Container(String name,
                     String type,
                     String descricao,
                     Integer nParticipantes,
                     Integer nNotifications,
                     Integer imageBg,
                     Integer imageContainer,
                     String dificuldade,
                     String importancia,
                     Integer idProfessor,
                     String codeParticipacao,
                     String data_de_criacao) {
        this.name = name;
        this.type = type;
        this.decricao = descricao;
        this.nParticipantes = nParticipantes;
        this.nNotifications = nNotifications;
        this.imageBg = imageBg;
        this.imageContainer = imageContainer;
        this.dificuldade = dificuldade;
        this.importancia = importancia;
        this.idProfessor = idProfessor;
        this.codeParticipacao = codeParticipacao;
        this.data_de_criacao = data_de_criacao;;
    }

    public Container(Integer _id,
                     String name,
                     String type,
                     String descricao,
                     Integer nParticipantes,
                     Integer nNotifications,
                     Integer imageBg,
                     Integer imageContainer,
                     String dificuldade,
                     String importancia,
                     Integer idProfessor,
                     String codeParticipacao,
                     String data_de_criacao) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.decricao = descricao;
        this.nParticipantes = nParticipantes;
        this.nNotifications = nNotifications;
        this.imageBg = imageBg;
        this.imageContainer = imageContainer;
        this.dificuldade = dificuldade;
        this.importancia = importancia;
        this.idProfessor = idProfessor;
        this.codeParticipacao = codeParticipacao;
        this.data_de_criacao = data_de_criacao;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public Integer getnParticipantes() {
        return nParticipantes;
    }

    public void setnParticipantes(Integer nParticipantes) {
        this.nParticipantes = nParticipantes;
    }

    public boolean isHaveNotifications() {
        return haveNotifications;
    }

    public void setHaveNotifications(boolean haveNotifications) {
        this.haveNotifications = haveNotifications;
    }

    public Integer getnNotifications() {
        return nNotifications;
    }

    public void setnNotifications(Integer nNotifications) {
        this.nNotifications = nNotifications;
    }

    public Integer getImageBg() {
        return imageBg;
    }

    public void setImageBg(Integer imageBg) {
        this.imageBg = imageBg;
    }

    public Integer getImageContainer() {
        return imageContainer;
    }

    public void setImageContainer(Integer imageContainer) {
        this.imageContainer = imageContainer;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getCodeParticipacao() {
        return codeParticipacao;
    }

    public void setCodeParticipacao(String codeParticipacao) {
        this.codeParticipacao = codeParticipacao;
    }

    public String getData_de_criacao() {
        return data_de_criacao;
    }

    public void setData_de_criacao(String data_de_criacao) {
        this.data_de_criacao = data_de_criacao;
    }

    public String getHora_de_criacao() {
        return hora_de_criacao;
    }

    public void setHora_de_criacao(String hora_de_criacao) {
        this.hora_de_criacao = hora_de_criacao;
    }
}
