package dungeon;

import userjob.Hero;

import java.util.Random;

public class Oni extends Ogre{

    public Oni() {

        setName("오니");
        setHp(120);
        setExp(18);
        setMoney(800);
        setBasicAttackName("정신지배술");
        setBasicDamage(12);
    }


    // 떨굴 돈
    @Override
    int dropMoney() { return getMoney(); }

    @Override
    int dropExp() { return getExp(); }

    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("오니가 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int ogreAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 1.5);
        hero.takeDamage(damage);

        System.out.println("오니가 스킬공격을 합니다. 오니의 정신지배술!");

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
