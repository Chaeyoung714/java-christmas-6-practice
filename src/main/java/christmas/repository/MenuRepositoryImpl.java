package christmas.repository;

import static christmas.model.reservation.MenuType.APPETIZER;
import static christmas.model.reservation.MenuType.BEVERAGE;
import static christmas.model.reservation.MenuType.DESSERT;
import static christmas.model.reservation.MenuType.MAIN;

import christmas.model.reservation.Menu;
import java.util.ArrayList;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository{
    private final List<Menu> menus;

    public MenuRepositoryImpl() {
        this.menus = saveMenus();
    }

    private List<Menu> saveMenus() {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("양송이수프", 6000, APPETIZER));
        menus.add(new Menu("타파스", 5500, APPETIZER));
        menus.add(new Menu("시저샐러드", 8000, APPETIZER));
        menus.add(new Menu("티본스테이크", 55000, MAIN));
        menus.add(new Menu("바비큐립", 54000, MAIN));
        menus.add(new Menu("해산물파스타", 35000, MAIN));
        menus.add(new Menu("크리스마스파스타", 25000, MAIN));
        menus.add(new Menu("초코케이크", 15000, DESSERT));
        menus.add(new Menu("아이스크림", 5000, DESSERT));
        menus.add(new Menu("제로콜라", 3000, BEVERAGE));
        menus.add(new Menu("레드와인", 60000, BEVERAGE));
        menus.add(new Menu("샴페인", 25000, BEVERAGE));
        return menus;
    }

    @Override
    public Menu findByName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[SYSTEM] 해당하는 이름의 메뉴가 없습니다."));
    }


}
