package model.task;

public class Task {
    private String name;
    private String description;
    private String durability;
    private String complexity;
    private boolean condition;

    public Task(String name, String description, String durability, String complexity, boolean condition) {
        this.name = name;
        this.description = description;
        this.durability = durability;
        this.complexity = complexity;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDurability() {
        return durability;
    }

    public String getComplexity() {
        return complexity;
    }

    public boolean getCondition() {
        return condition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(name);
        str.append("\n");
        str.append(description);
        str.append("\n");
        str.append(durability);
        str.append("\n");
        str.append(complexity);
        str.append("\n");
        if (condition) {
            str.append("done");
        } else {
            str.append("undone");
        }
        return str.toString();
    }
}