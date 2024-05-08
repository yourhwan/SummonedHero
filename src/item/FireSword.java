package item;

import dungeon.Monster;

public class FireSword extends Weapon {
    public FireSword() {
        super("화염 검", 5000, 10);
    }

    @Override
    public void attack(Monster monster, boolean battleOver) {
        new Thread(() -> {
            while (!battleOver) {
                try {
                    Thread.sleep(1000); // 1초마다 데미지 입히기
                    System.out.println("불의 검의 효과로 몬스터에게 화상 데미지 10을 입혔습니다.");
                    monster.takeDamage(10); // 몬스터에게 데미지 입히기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
