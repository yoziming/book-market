# 柚子書城

> 柚子書城是一個不依賴Spring框架，純粹的JavaWeb項目，主要練習HTTP基本功、Servlet、Session等原理，以及JDBC、資料庫連接池等基礎的SQL操作。

## 專案背景(Background)

- 本項目參考自 尚硅谷java web課程 https://www.bilibili.com/video/BV1Y7411K7zz
- 個人學習過程的詳細筆記(共14篇): https://yoziming.github.io/post/220101-agg-javaweb-01/
- 我修復了一些邏輯並完善功能，並成功將網站部署到雲端，可以透過公開網址訪問

## 展示網站(Website)

- 已將專案部署到heroku，由於dyno長時間沒用會自動休眠，有時候訪問可能需要等待約30秒雲端server啟動時間
- https://my-javaweb-book-market.herokuapp.com/
- 可自行註冊帳號，管理員權限帳號密碼為 `admin`

### 學習技術

#### 前端

- html、css、javascript的基礎認識
  - 例如: 各種標籤`<a href>`、`<input type>`、`<form>`

- jsp、jQuery、JSTL標籤庫、EL運算式
  - 例如: `<c:if test="">`、`${key1}`、綁定點擊事件...等等

- Ajax，非同步請求的基礎構成，例如: `$.getJSON()`

#### 後端

- 不依賴框架的http請求與響應操作
- Servlet、Filter、Listener、Cookie、Session、JSON
- 驗證資訊、權限隔離、防止重複提交
- JDBC、資料庫連接池、DAO操作SQL
- MVC與三層架構、搭建javaweb專案
- web容器的運作與部屬

## 實現功能(Service)

- 柚子書城是簡單的商城項目，依業務劃分可分為五個主要模組


| 模組                     | 需求                                                         |
| ------------------------ | ------------------------------------------------------------ |
| 用戶     | 能驗證資訊(ajax)即時提示的會員註冊、登入(透過cookie)功能 |
| 商品       | 能夠分頁顯示商品(圖書)、可依照價格區間篩選 |
| 購物車    | 以Session實現的購物車，用戶可以快速地瀏覽、增刪購物項目 |
| 訂單 | 提交訂單、查看訂單詳情、確認收貨等等 |
| 管理後台 | 能驗證管理員帳號，增刪改查商品訊息、訂單訊息 |

## 演示(Demo)

- 以下透過gif簡單展示相關功能，若顯示有問題，可以移至個人blog觀看
- https://yoziming.github.io/post/220209-javaweb-book-market/

### 登入與註冊

- 以Ajax請求，即時驗證填入的資訊，並做出提醒(如用戶名重複等等)
- 驗證碼防止機器人與重複註冊

![login.gif](https://yoziming.github.io/post/220209-javaweb-book-market/login.gif)

### 價格篩選

- 同樣有輸入驗證，即使使用者亂輸入也會在稍後自動校正
- 可指定頁數直接跳轉的分頁導航

![price](https://yoziming.github.io/post/220209-javaweb-book-market/price.gif)

### 購物車

- 提示剛剛加入購物車的商品
- 可修改數量，並且也有輸入驗證與校正
- 刪除項目、清空購物車

![cart](https://yoziming.github.io/post/220209-javaweb-book-market/cart.gif)

### 訂單

- 下單成功後可察看訂單詳情
- 並且會將購物車正確清空

![guest](https://yoziming.github.io/post/220209-javaweb-book-market/guest.gif)

### 用戶資訊保護

- 必須為正確的登入狀態才可以查看訂單相關模組

![filter](https://yoziming.github.io/post/220209-javaweb-book-market/filter.gif)

### 管理後台

- 能驗證是否為具有管理員權限
- 增刪改查商品訊息、訂單訊息

![manager](https://yoziming.github.io/post/220209-javaweb-book-market/manager.gif)

### 用戶收貨

- 當商城出貨後，用戶可以執行確認收貨的動作

![receive](https://yoziming.github.io/post/220209-javaweb-book-market/receive.gif)

### 友善的錯誤提示頁面

![error](https://yoziming.github.io/post/220209-javaweb-book-market/error.gif)


## 版本(Version)

- 本專案使用的環境: JDK8、MySQL5.7、Tomcat8.5.75 
- 專案結構:

```
├─src
│  ├─main
│  │  ├─java
│  │  │  └─yoziming
│  │  │      ├─dao  # 操作SQL用
│  │  │      ├─filter  # 權限過濾
│  │  │      ├─pojo  # entity
│  │  │      ├─service  # 主要業務邏輯
│  │  │      ├─test  # 主要業務邏輯
│  │  │      ├─utils  # 工具類(處理JDBC連線等等)
│  │  │      └─web  # servlet
│  │  ├─resources  # 設定檔
│  │  └─webapp
│  │      ├─pages  # JSP頁面
│  │      ├─static  # 靜態資源
│  │      └─WEB-INF
```

- MySQL建表資料

```sql
create database bookstore;

use bookstore;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sales` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `img_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (1, '高等數學', 10.00, '牛頓', 3, 100, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (3, 'Java入門到放棄', 55.00, 'James Gosling', 6, 99, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (4, '易筋經：中國式瑜珈', 360.00, '譚浩強', 126, 184, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (5, '三體', 50.00, '劉慈欣', 27, 3, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (6, '論文寫作指導', 49.00, '劉慈欣', 65, 35, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (7, '囚狐', 133.05, '雷豐陽', 125, 185, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (8, '自動控制原理', 89.15, '王萬良', 23, 7, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (9, '第七日', 49.00, '余華', 66, 34, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (10, '人生', 133.05, '路遙', 122, 188, 'static/img/default2.jpg');
INSERT INTO `t_book` VALUES (11, '活著', 89.15, '余華', 20, 10, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (12, '禿子跟著月亮走', 999.00, '韓國瑜', 0, 1000, 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('16444160853253', '2022-02-09 22:14:45', 925.00, 1, 3);
INSERT INTO `t_order` VALUES ('16444161578231', '2022-02-09 22:15:57', 346.10, 2, 1);
INSERT INTO `t_order` VALUES ('16444162162311', '2022-02-09 22:16:56', 178.30, 1, 1);
INSERT INTO `t_order` VALUES ('16444165692211', '2022-02-09 22:22:49', 187.15, 0, 1);

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `total_price` decimal(11, 2) NULL DEFAULT NULL,
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES (8, 'Java入門到放棄', 1, 55.00, 55.00, '16444160853253');
INSERT INTO `t_order_item` VALUES (9, '易筋經：中國式瑜珈', 2, 360.00, 720.00, '16444160853253');
INSERT INTO `t_order_item` VALUES (10, '三體', 3, 50.00, 150.00, '16444160853253');
INSERT INTO `t_order_item` VALUES (11, '高等數學', 3, 10.00, 30.00, '16444161578231');
INSERT INTO `t_order_item` VALUES (12, '三體', 1, 50.00, 50.00, '16444161578231');
INSERT INTO `t_order_item` VALUES (13, '囚狐', 2, 133.05, 266.10, '16444161578231');
INSERT INTO `t_order_item` VALUES (14, '自動控制原理', 2, 89.15, 178.30, '16444162162311');
INSERT INTO `t_order_item` VALUES (15, '第七日', 2, 49.00, 98.00, '16444165692211');
INSERT INTO `t_order_item` VALUES (16, '自動控制原理', 1, 89.15, 89.15, '16444165692211');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'admin@example.com');
INSERT INTO `t_user` VALUES (2, 'aaa', 'aaa', 'aaa@a.com');
INSERT INTO `t_user` VALUES (3, 'ccc', 'ccc', 'ccc@c.com');
INSERT INTO `t_user` VALUES (4, 'guest', 'guest', 'xxx@x.com');

SET FOREIGN_KEY_CHECKS = 1;

```

## 部署(deploy)

- 使用Tomcat Server運行，並確認Artifacts無誤即可啟動

![image-20220210000251435](https://yoziming.github.io/post/220209-javaweb-book-market/image-20220210000251435.png)

- 需要確認 `jdbc.properties`中MySQL的帳號與密碼
- 將專案部署到heroku的踩坑紀錄，請參閱heroku部署篇
