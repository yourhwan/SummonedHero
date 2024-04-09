package dungeon;

import userjob.Hero;

public class Kishin extends Ogre{


    @Override
    int dropMoney() {
        return 0;
    }

    @Override
    int dropExp() {
        return 0;
    }

    @Override
    int useBasicAttack(Hero hero) {
        return 0;
    }

    @Override
    int randomAttack(Hero hero) {
        return 0;
    }

    @Override
    int ogreAttSkill(Hero hero) {
        return 0;
    }
}
