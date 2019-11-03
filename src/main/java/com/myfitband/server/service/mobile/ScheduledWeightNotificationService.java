package com.myfitband.server.service.mobile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

interface ScheduledWeightNotificationService {

    void pushNotifications() throws UnirestException, JsonProcessingException;

    class FirebaseMessage {
        String to;
        Data data;

        public FirebaseMessage(String to, String title, String body) {
            this.to = to;
            this.data = new Data(title, body);
        }

        FirebaseMessage()  {
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    class Data {
        String title;
        String body;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        String message;

        Data(String title, String body) {
            this.title = title;
            this.body = body;
            this.message = body;
        }

        Data() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
