package stm.demo.model.enums;

public enum Status {
    NEW("NEW"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");
    private  final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
