package com.gottlieb.sample.service.adapter;

import java.util.List;

public class WaveappsErrorResponse {

    private List<ErrorDetail> errors;

    // Getters and Setters
    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WaveAppsErrorResponse {");
        if (errors != null) {
            sb.append("errors=").append(errors);
        }
        sb.append('}');
        return sb.toString();
    }

    public static class ErrorDetail {

        private Extensions extensions;
        private String message;
        private List<Location> locations;

        // Getters and Setters
        public Extensions getExtensions() {
            return extensions;
        }

        public void setExtensions(Extensions extensions) {
            this.extensions = extensions;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Location> getLocations() {
            return locations;
        }

        public void setLocations(List<Location> locations) {
            this.locations = locations;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("ErrorDetail {");
            sb.append("message='").append(message).append('\'');
            if (extensions != null) {
                sb.append(", extensions=").append(extensions);
            }
            if (locations != null) {
                sb.append(", locations=").append(locations);
            }
            sb.append('}');
            return sb.toString();
        }
    }

    public static class Extensions {

        private String code;
        private String id;

        // Getters and Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Extensions {" + "code='" + code + '\'' + ", id='" + id + '\'' + '}';
        }
    }

    public static class Location {

        private int line;
        private int column;

        // Getters and Setters
        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        @Override
        public String toString() {
            return "Location {" + "line=" + line + ", column=" + column + '}';
        }
    }
}
