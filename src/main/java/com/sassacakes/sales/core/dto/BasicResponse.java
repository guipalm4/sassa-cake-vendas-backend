package com.sassacakes.sales.core.dto;

import com.sassacakes.sales.core.model.AbstractEntity;

public class BasicResponse {

    private AbstractEntity data;
    private InfoMessage message;


    public AbstractEntity getData() {
        return data;
    }

    public void setData(AbstractEntity data) {
        this.data = data;
    }

    public InfoMessage getMessage() {
        return message;
    }

    public void setMessage(InfoMessage message) {
        this.message = message;
    }


    public static final class Builder {
        private AbstractEntity data;
        private InfoMessage message;

        private Builder() {
        }

        public static Builder aBasicResponse() {
            return new Builder();
        }

        public Builder data(AbstractEntity object) {
            this.data = object;
            return this;
        }

        public Builder message(InfoMessage message) {
            this.message = message;
            return this;
        }

        public BasicResponse build() {
            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setData(data);
            basicResponse.setMessage(message);
            return basicResponse;
        }
    }
}
