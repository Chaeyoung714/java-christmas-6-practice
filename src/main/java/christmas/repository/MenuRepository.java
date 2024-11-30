package christmas.repository;

import christmas.model.reservation.Menu;

public interface MenuRepository {

    Menu findByName(String name);
}
