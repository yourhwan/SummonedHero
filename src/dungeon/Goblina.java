package dungeon;

import userjob.Hero;

public class Goblina extends Goblin{

    public Goblina() {

        setName("고블리나");
        setHp(50); // 고블리나 체력
        setExp(7); // 고블리나 떨굴 경험치
        setMoney(300); // 고블리나 떨굴 돈
        setBasicAttackName("몽둥이 휘두르기"); // 고블리나 공격 이름
        setBasicDamage(7); // 고블리나 공격력
    }



    // 떨굴 돈
    @Override
    int dropMoney() {
        return getMoney();
    }

    // 떨굴 경험치
    @Override
    int dropExp() {
        return getExp();
    }

    // 고블리나 공격
    @Override
    int useBasicAttack(Hero hero) {

        System.out.println("고블리나가 공격을 합니다. " + getBasicAttackName() + "!");

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        return damage;
    }

    // 고블리나 고유 스킬
    @Override
    void goblinSkill() {

    }
}
