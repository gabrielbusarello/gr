package br.univille.gr.util;

public class Resposta<T> {
    private int status;
    private T data;
    private String mensagem;

    public int getStatus() {
        return status;
    }

    /**
     * Os status podem ser:
     * 1 - Sucesso;
     * 2 - Informação (Ex.: Sem registros);
     * 3 - Erro.
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
