package dungeon;

import userjob.Hero;

import java.util.Random;

public class JusticeAngel extends Angel{ // 정의의 천사

    public JusticeAngel() {

        setName("정의의 대천사");
        setHp(400);
        setExp(50);
        setMoney(5000);
        setBasicAttackName("정의의 심판");
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

        System.out.println("정의의 대천사가 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int angelAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 3.0);
        hero.takeDamage(damage);

        System.out.println("정의의 대천사가 스킬공격을 합니다. 정의의 검, 엘드루인!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 50) {
            // 50% 확률로 스킬공격 사용
            return angelAttSkill(hero);
        } else {
            // 50% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }

}
