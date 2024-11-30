package christmas.repository;

import christmas.model.reservation.Menu;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private final List<Menu> menus;

    public MenuRepository() {
        this.menus = new ArrayList<>();
    }

    public void save(Menu menu) {
        menus.add(menu);
    }

    public Menu findByName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[SYSTEM] 해당하는 이름의 메뉴가 없습니다."));
    }


}
