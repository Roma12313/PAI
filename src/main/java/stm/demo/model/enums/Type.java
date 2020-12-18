package stm.demo.model.enums;

public enum Type {
    TASK("TASK"),
    BUG("BUGOWANIE"),
    FEATURE("FEATURE");
    private  final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
