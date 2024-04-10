package dungeon;

import userjob.Hero;

import java.util.Random;

public class MedievalDevil extends Devil{ // 중세 악마종
    public MedievalDevil() {

        setName("중세악마종");
        setHp(190);
        setExp(35);
        setMoney(1700);
        setBasicAttackName("악마의 저주");
        setBasicDamage(23);
    }



    @Override
    int dropMoney() { return getMoney(); }

    @Override
    int dropExp() { return getExp(); }

    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("중세 악마종이 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    @Override
    int devilAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 2.0);
        hero.takeDamage(damage);

        System.out.println("중세 악마종이 스킬공격을 합니다. 중세 악마종의 영혼 계약!");

        return damage;
    }

    @Override
    int randomAttack(Hero hero) {

        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 35) {
            // 35% 확률로 스킬공격 사용
            return devilAttSkill(hero);
        } else {
            // 65% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }


}
