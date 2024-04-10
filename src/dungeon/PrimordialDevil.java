package dungeon;

import userjob.Hero;

import java.util.Random;

public class PrimordialDevil extends Devil{ // 원시 악마종(악마종 보스)

    public PrimordialDevil() {

        setName("원시 악마종");
        setHp(250);
        setExp(45);
        setMoney(3000);
        setBasicAttackName("악마의 날갯짓");
        setBasicDamage(30);
    }



    @Override
    int dropMoney() { return getMoney(); }

    @Override
    int dropExp() { return getExp(); }

    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("고대 악마종이 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int devilAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 2.5);
        hero.takeDamage(damage);

        System.out.println("고대 악마종이 스킬공격을 합니다. 고대 악마종의 지옥문 개방!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 45) {
            // 45% 확률로 스킬공격 사용
            return devilAttSkill(hero);
        } else {
            // 55% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }


}
