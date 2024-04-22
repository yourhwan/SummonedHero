package dungeon;

import userjob.*;

public enum DevilType {
    MEDIEVAL_DEVIL,
    MODERN_DEVIL,
    ANCIENT_DEVIL,
    PRIMORDIAL_DEVIL;

    public static DevilType getRandomDevilType() {
        return values()[(int) (Math.random() * values().length)];
    }

    public Monster createInstance() {
        switch (this) {
            case MEDIEVAL_DEVIL:
                return new MedievalDevil();
            case MODERN_DEVIL:
                return new ModernDevil();
            case ANCIENT_DEVIL:
                return new AncientDevil();
            case PRIMORDIAL_DEVIL:
                return new PrimordialDevil();
            default:
                throw new IllegalArgumentException("Unknown devil type: " + this);
        }
    }
}
