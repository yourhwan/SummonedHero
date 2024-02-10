package rpggame;

public class Worrier extends Hero{

    // 워리어 생성자
    public Worrier() {
        setHp(100);
        setMp(100);
        setExp(getExp());
        setLevel(getLevel());
        setMoney(getMoney());
        setJob("워리어");
    }

    @Override
    void useBasicAttack() {

    }

    @Override
    void usePassiveSkill() {

    }
}
