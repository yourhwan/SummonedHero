package dungeon;

public enum OgreType {
    ONI,
    KISHIN,
    KIJIN;

    public static OgreType getRandomOgreType() {
        return values()[(int) (Math.random() * values().length)];
    }

    public Monster createInstance() {
        switch (this) {
            case ONI:
                return new Oni();
            case KISHIN:
                return new Kishin();
            case KIJIN:
                return new Kijin();
            default:
                throw new IllegalArgumentException("Unknown ogre type: " + this);
        }
    }
}
