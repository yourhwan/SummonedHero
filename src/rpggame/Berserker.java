package rpggame;

public class Berserker extends Hero {

    public Berserker() {

        setHp(200);               // 체력
        setMp(100);               // 마력
        setExp(getExp());         // 경험치
        setLevel(getLevel());     // 레벨
        setMoney(getMoney());     // 돈
        setJob("버서커");           // 직업
        setBasicAttackName("블러딩슬래쉬"); // 기본 공격 이름
        setBasicAttackDamage(40); // 기본 공격 데미지
    }

    @Override
    int useBasicAttack() {

    return 0;
    }

    @Override
    void usePassiveSkill() {

    }
}
