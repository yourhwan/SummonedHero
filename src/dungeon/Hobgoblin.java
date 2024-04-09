package dungeon;

import userjob.Hero;

import java.util.Random;

public class Hobgoblin extends Goblin{

    public Hobgoblin() {

        setName("홉고블린");
        setHp(60); // 홉고블린 체력
        setExp(15); // 홉고블린 떨굴 경험치
        setMoney(400); // 홉고블린 떨굴 돈
        setBasicAttackName("몽둥이 휘두르기"); // 홉고블린 공격 이름
        setBasicDamage(8); // 홉고블린 공격력
    }



    // 떨굴 돈
    @Override
    int dropMoney() { return getMoney(); }

    // 떨굴 경험치
    @Override
    int dropExp() {
        return getExp();
    }

    // 홉고블린 공격
    @Override
    int useBasicAttack(Hero hero) {

        int damage = getBasicDamage();
        hero.takeDamage(damage);

        System.out.println("홉고블린이 공격을 합니다. " + getBasicAttackName() + "!");

        return damage;
    }

    // 홉고블린 고유 스킬

    @Override
    int goblinAttSkill(Hero hero) {

        int damage = (int) (getBasicDamage() * 1.5);
        hero.takeDamage(damage);

        System.out.println("홉고블린이 스킬공격을 합니다. 홉고블린의 분노!");

        return damage;
    }

    // 홉고블린의 랜덤 공격(기본공격과 스킬공격이 확률적으로 발동)
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
