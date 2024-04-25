package dungeon;

public enum AngelType {
    COURAGE_ANGEL,
    HOPE_ANGEL,
    FATE_ANGEL,
    JUSTICE_ANGEL,
    WISDOM_ANGEL;

    public static AngelType getRandomAngelType() {
        return values()[(int) (Math.random() * values().length)];
    }

    public Monster createInstance() {
        switch (this) {
            case COURAGE_ANGEL:
                return new CourageAngel();
            case HOPE_ANGEL:
                return new HopeAngel();
            case FATE_ANGEL:
                return new FateAngel();
            case JUSTICE_ANGEL:
                return new JusticeAngel();
            case WISDOM_ANGEL:
                return new WisdomAngel();
            default:
                throw new IllegalArgumentException("Unknown angel type: " + this);
        }
    }
}
