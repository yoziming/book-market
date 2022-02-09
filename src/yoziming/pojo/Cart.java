package yoziming.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 購物車物件
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;

    /**
     * key是商品編號，
     * value，是商品信息
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    /**
     * 添加商品項
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // 先查看購物車中是否已經添加過此商品，如果已添加，則數量累加，總金額更新，如果沒有添加過，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            // 之前沒添加過此商品
            items.put(cartItem.getId(), cartItem);
        } else {
            // 已經 添加過的情況
            item.setCount( item.getCount() + 1 ); // 數量 累加
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal( item.getCount() )) ); // 更新總金額
        }

    }

    /**
     * 刪除商品項
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }


    /**
     * 清空購物車
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品數量
     */
    public void updateCount(Integer id,Integer count) {
        // 先查看購物車中是否有此商品。如果有，修改商品數量，更新總金額
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);// 修改商品數量
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal( cartItem.getCount() )) ); // 更新總金額
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
