package dungeon;

public enum GoblinType {
    GOBLINA,
    HOBGOBLIN;

    public static GoblinType getRandomGoblinType() {
        return values()[(int) (Math.random() * values().length)];
    }

    public Monster createInstance() {
        switch (this) {
            case GOBLINA:
                return new Goblina();
            case HOBGOBLIN:
                return new Hobgoblin();
            default:
                throw new IllegalArgumentException("Unknown gobiln type: " + this);
        }
    }
}
