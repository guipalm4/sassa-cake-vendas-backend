package com.sassacakes.sales.core.dto;


public class InfoMessage {

    private MessageType type;
    private String text;


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public static final class Builder {
        private MessageType type;
        private String text;

        private Builder() {
        }

        public static Builder anInfoMessage() {
            return new Builder();
        }

        public Builder type(MessageType type) {
            this.type = type;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public InfoMessage build() {
            InfoMessage infoMessage = new InfoMessage();
            infoMessage.setType(type);
            infoMessage.setText(text);
            return infoMessage;
        }
    }
}
