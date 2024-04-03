package dungeon;

import userjob.Hero;

import java.util.Random;

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

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("고블리나가 공격을 합니다. " + getBasicAttackName() + "!" +
                "\n"+damage+"만큼의 피해를 입었습니다.");

        return damage;
    }

    // 고블리나 고유 스킬
    @Override
    int goblinAttSkill(Hero hero) { // 고블리나의 분노

        int damage = (int) (getBasicDamage() * 1.5);
        hero.takeDamage(damage);

        System.out.println("고블리나가 스킬공격을 합니다. 고블리나의 분노!" +
                "\n"+damage+"만큼의 피해를 입었습니다.");

        return damage;
    }

    // 고블리나의 랜덤 공격(기본공격과 스킬공격이 확률적으로 발동)
    @Override
    int randomAttack(Hero hero) {
        Random random = new Random();
        int randomNumber = random.nextInt(100); // 0~99 난수 생성

        if (randomNumber < 30) {
            // 30% 확률로 스킬공격 사용
            return goblinAttSkill(hero);
        } else {
            // 70% 확률로 기본공격 사용
            return useBasicAttack(hero);
        }
    }

}
