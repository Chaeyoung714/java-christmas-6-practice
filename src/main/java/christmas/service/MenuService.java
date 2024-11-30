package christmas.service;

import static christmas.model.MenuType.APPETIZER;
import static christmas.model.MenuType.BEVERAGE;
import static christmas.model.MenuType.DESSERT;
import static christmas.model.MenuType.MAIN;

import christmas.model.Menu;
import christmas.repository.MenuRepository;

public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void registerMenus() {
        menuRepository.save(new Menu("양송이수프", 6000, APPETIZER));
        menuRepository.save(new Menu("타파스", 5500, APPETIZER));
        menuRepository.save(new Menu("시저샐러드", 8000, APPETIZER));
        menuRepository.save(new Menu("티본스테이크", 55000, MAIN));
        menuRepository.save(new Menu("바비큐립", 54000, MAIN));
        menuRepository.save(new Menu("해산물파스타", 35000, MAIN));
        menuRepository.save(new Menu("크리스마스파스타", 25000, MAIN));
        menuRepository.save(new Menu("초코케이크", 15000, DESSERT));
        menuRepository.save(new Menu("아이스크림", 5000, DESSERT));
        menuRepository.save(new Menu("제로콜라", 3000, BEVERAGE));
        menuRepository.save(new Menu("레드와인", 60000, BEVERAGE));
        menuRepository.save(new Menu("샴페인", 25000, BEVERAGE));
    }
}
