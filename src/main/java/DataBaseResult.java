public class DataBaseResult {

    private int id;
    private String name;
    private String description;

    public DataBaseResult(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produtos {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                '}';
    }
}
