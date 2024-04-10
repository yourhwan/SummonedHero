package dungeon;

import userjob.Hero;

import java.util.Random;

public class CourageAngel extends Angel{ // 용기의 대천사

    public CourageAngel() {

        setName("용기의 대천사");
        setHp(400);
        setExp(50);
        setMoney(8000);
        setBasicAttackName("용맹한 돌격");
        setBasicDamage(40);
    }


    @Override
    int dropMoney() { return getMoney(); }

    @Override
    int dropExp() { return getExp(); }

    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("용기의 대천사가 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int angelAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 4.0);
        hero.takeDamage(damage);

        System.out.println("용기의 대천사가 스킬공격을 합니다. 천상의 군대 돌격!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 65) {
            // 65% 확률로 스킬공격 사용
            return angelAttSkill(hero);
        } else {
            // 35% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }


}
