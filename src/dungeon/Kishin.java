package dungeon;

import userjob.Hero;

import java.util.Random;

public class Kishin extends Ogre{

    public Kishin() {

        setName("키신");
        setHp(150);
        setExp(25);
        setMoney(1000);
        setBasicAttackName("정신지배술");
        setBasicDamage(15);
    }

    @Override
    int dropMoney() {
        return getMoney();
    }

    @Override
    int dropExp() {
        return getExp();
    }

    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("키신이 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int ogreAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 1.5);
        hero.takeDamage(damage);

        System.out.println("키신이 스킬공격을 합니다. 키신의 정신지배술!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 25) {
            // 25% 확률로 스킬공격 사용
            return ogreAttSkill(hero);
        } else {
            // 75% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }
}
