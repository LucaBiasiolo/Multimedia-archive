package it.archive.multimedia.web;

import java.util.List;

public class MultimediaArchiveAPIResponse<T> {

    private String status;
    private T response;
    private List<String> messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}