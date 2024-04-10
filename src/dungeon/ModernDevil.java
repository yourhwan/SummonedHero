package dungeon;

import userjob.Hero;

import java.util.Random;

public class ModernDevil extends Devil{ // 현대 악마종

    public ModernDevil() {

        setName("현대악마종");
        setHp(180);
        setExp(30);
        setMoney(1500);
        setBasicAttackName("악마의 속삭임");
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

        System.out.println("현대악마종이 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int devilAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 1.8);
        hero.takeDamage(damage);

        System.out.println("현대악마종이 스킬공격을 합니다. 지옥의 포효!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 30) {
            // 30% 확률로 스킬공격 사용
            return devilAttSkill(hero);
        } else {
            // 70% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }


}
