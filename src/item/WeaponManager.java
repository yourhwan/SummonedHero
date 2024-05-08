package item;

import dungeon.Monster;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeaponManager {
    private static final WeaponManager instance = new WeaponManager();
    private final ExecutorService executorService;

    private WeaponManager() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public static WeaponManager getInstance() {
        return instance;
    }

    public void startWeaponThread(Weapon weapon, List<Monster> monsters, boolean battleOver) {
        if (monsters == null || monsters.isEmpty()) {
            System.out.println("대상이 없습니다.");
            return;
        }

        for (Monster monster : monsters) {
            executorService.execute(() -> {
                while (monster.isAlive()) {
                    try {
                        Thread.sleep(1000); // Deal damage every second
                        weapon.attack(monster, battleOver);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void stopAllWeaponThreads() {
        executorService.shutdownNow();
    }
}
