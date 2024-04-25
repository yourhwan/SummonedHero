package item;

public class FireSword extends Weapon {
    public FireSword() {
        super("화염 검", 10000); // 가격을 10,000원으로 가정합니다.
    }

    @Override
    public void attack() {
        System.out.println("화염 검이 불길을 내뿜어 몬스터에게 10의 피해를 입힙니다!");
        if (getTarget() != null && getTarget().isAlive()) {
            getTarget().takeDamage(10); // 몬스터에게 10의 피해를 입힙니다.
        }
    }
}
