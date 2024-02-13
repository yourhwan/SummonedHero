package rpggame;

public class DualBlade extends Hero{

    public DualBlade() {

        setHp(150);               // 체력
        setMp(150);               // 마력
        setExp(getExp());         // 경험치
        setLevel(getLevel());     // 레벨
        setMoney(getMoney());     // 돈
        setJob("듀얼블레이드");       // 직업
        setBasicAttackDamage(30); // 기본 공격 데미지
    }

    @Override
    int useBasicAttack() {

        return 0;
    }

    @Override
    void usePassiveSkill() {

    }
}
