package com.tabs.assignment1.model;

public enum PackageStatus {
    BOOKED {
        @Override
        public String toString() {
            return "BOOKED";
        }
    },
    NOT_BOOKED {
        @Override
        public String toString() {
            return "NOT_BOOKED";
        }
    },
    IN_PROGRESS {
        @Override
        public String toString() {
            return "IN_PROGRESS";
        }
    };

    abstract public String toString();
}
