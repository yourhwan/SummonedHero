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
    int goblinAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 1.5);
        hero.takeDamage(damage);

        System.out.println("홉고블린이 스킬공격을 합니다. 홉고블린의 분노!" +
                "\n"+damage+"만큼의 피해를 입었습니다.");

        return damage;
    }

    // 홉고블린의 랜덤 공격
    @Override
    int randomAttack(Hero hero) {
        return 0;
    }




}
