package dungeon;

import userjob.Hero;

import java.util.Random;

public class Kijin extends Ogre{

    public  Kijin() { // 오거 중 최상위

        setName("키진");
        setHp(300);
        setExp(30);
        setMoney(1500);
        setBasicAttackName("영역전개");
        setBasicDamage(20);
    }



    @Override
    int dropMoney() { return getMoney(); }

    @Override
    int dropExp() { return getExp(); }

    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("키진이 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int ogreAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 1.5);
        hero.takeDamage(damage);

        System.out.println("키진이 스킬공격을 합니다. 오거의 왕!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 30) {
            // 30% 확률로 스킬공격 사용
            return ogreAttSkill(hero);
        } else {
            // 70% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }
}
