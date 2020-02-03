package com.galaxy.movie.constants;

public enum MessageConstants {

    DEBUG{
        @Override
        public String toString() {
            return "[DEBUG] ";
        }
    }, ERROR{
        @Override
        public String toString() {
            return "[ERROR] ";
        }
    }, INFO{
        @Override
        public String toString() {
            return "[INFO] ";
        }
    }, WARNING{
        @Override
        public String toString() {
            return "[WARNING] ";
        }
    };
}
