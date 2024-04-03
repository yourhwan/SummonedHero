package dungeon;

import userjob.Hero;

public class Hobgoblin extends Goblin{

    // 떨굴 돈
    @Override
    int dropMoney() {
        return 400;
    }

    // 떨굴 경험치
    @Override
    int dropExp() {
        return 10;
    }

    // 홉고블린 공격
    @Override
    int useBasicAttack(Hero hero) {
        return 10;
    }

    // 홉고블린 고유 스킬
    @Override
    void goblinSkill() {

    }
}
