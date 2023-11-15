package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @DisplayName("Menu 클래스의 모든 상수의 menuName 리스트 구하기")
    @Test
    void getMenuNameSetTest() {
        List<String> test = new ArrayList<>(Menu.getMenuNameSet());
        List<String> expected = new ArrayList<>(Arrays.asList("양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타",
                "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"));
        assertThat(test).isEqualTo(expected);
    }

    @DisplayName("Menu 클래스에서 type이 beverage인 상수의 menuName 리스트 구하기")
    @Test
    void getBeverageNameSetTest() {
        List<String> test = new ArrayList<>(Menu.getBeverageNameSet());
        List<String> expected = new ArrayList<>(Arrays.asList("제로콜라", "레드와인", "샴페인"));
        assertThat(test).isEqualTo(expected);
    }

    @DisplayName("Menu 클래스에서 type이 main인 상수들의 리스트 구하기")
    @Test
    void getMainMenuTest() {
        List<Menu> test = new ArrayList<>(Menu.getMainMenu());
        List<Menu> expected = new ArrayList<>(Arrays.asList(Menu.T_BONE_STAKE, Menu.BARBECUE_RIB,
                Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA));
        assertThat(test).isEqualTo(expected);
    }

    @DisplayName("Menu 클래스에서 type이 dessert 상수들의 리스트 구하기")
    @Test
    void getDessertMenuTest() {
        List<Menu> test = new ArrayList<>(Menu.getDessertMenu());
        List<Menu> expected = new ArrayList<>(Arrays.asList(Menu.CHOCO_CAKE, Menu.ICE_CREAM));
        assertThat(test).isEqualTo(expected);
    }
}